package cn.gaocy.ktpractice.chapter03_function.ext

import java.lang.StringBuilder

var StringBuilder.lastChar: Char
    get() = if (length > 0) {
        get(length - 1)
    } else {
        ' '
    }
    set(value) {
        setCharAt(length-1,value)
    }