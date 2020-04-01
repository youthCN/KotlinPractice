package cn.gaocy.ktpractice.chapter12_network

import cn.gaocy.ktpractice.chapter12_network.net.RetrofitFactory
import cn.gaocy.ktpractice.chapter12_network.utils.TimeUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import cn.gaocy.ktpractice.chapter12_network.domain.SmsRest
import cn.gaocy.ktpractice.chapter12_network.utils.IOUtil
import com.google.gson.Gson
import java.io.*

fun main() {
    val lock = Any();
    val file = File("allCode.txt")
    if (!file.exists()) {
        if (!file.createNewFile()) {
            println("创建文件失败....")
            Thread.sleep(1000)
            return;
        }
    }
    while (true) {
        val sendTime = TimeUtils.getCurrentDayMillis().toString();
        val httpApi = RetrofitFactory.getInstance().httpApi
        val map = mapOf("func" to "querySmsAuthCode", "sendTime" to sendTime)
        val querySmsAuthCode = httpApi.querySmsAuthCode(map)
        querySmsAuthCode.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                lateinit var resultJson: String;
                try {
                    resultJson = response.body()!!.string()
                    val gson = Gson()
                    val smsRest = gson.fromJson(resultJson, SmsRest::class.java)
                    val code = smsRest.code
                    if (code == 0) {
                        synchronized(lock) {
                            val fileWriter = FileWriter(file)
                            val bw = BufferedWriter(fileWriter)
                            val currentDayPretty = TimeUtils.getCurrentDayPretty()
                            bw.write("--------------------------$currentDayPretty----------------------------------")
                            bw.newLine()

                            val result = smsRest.result
                            for (smsAuthCode in result) {
                                val sendCodeNo = smsAuthCode.sendCodeNo
                                val receiveCodeNo = smsAuthCode.receiveCodeNo
                                val sendTime = smsAuthCode.sendTime
                                val authCode = smsAuthCode.authCode
                                bw.write(
                                    """
发送方:    ${sendCodeNo}
接收方:    ${receiveCodeNo}
发送时间:  ${sendTime}
验证码:    ${authCode}
                        """
                                )
                                bw.newLine()
                            }
                            bw.flush()
                            IOUtil.closeQuietly(bw)
                            IOUtil.closeQuietly(fileWriter)
                            println("请求成功:)")
                        }

                        return
                    }
                    println("请求失败, 参数错误...${smsRest.msg}")
                } catch (e: Exception) {
                    e.printStackTrace()
                    println("请求失败, 网络故障...${e.toString()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println("请求失败, 网络故障...${t.message}")
            }
        })

        Thread.sleep(10000)
    }
}