@file:JvmName("SerialNumberCalculator")

package com.github.knextsunj.cms.util

@JvmName("calculateAndGiveSerialNo")
fun calculateAndGiveSerialNo(serialNumberThreadLocal: ThreadLocal<Long>): ThreadLocal<Long> {
    println(serialNumberThreadLocal.get()?:12)
    var newSerialNo = serialNumberThreadLocal.get() + 1;
    serialNumberThreadLocal.set(newSerialNo);
    return serialNumberThreadLocal;
}