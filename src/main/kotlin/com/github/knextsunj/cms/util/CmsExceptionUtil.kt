package com.github.knextsunj.cms.util

open class CmsExceptionUtil {
companion object {

    @JvmStatic
    fun composeExceptionMessage(message: String?, id: Long?): String? {
        var stringBuilder: StringBuilder = StringBuilder()
        stringBuilder.append(message)
        stringBuilder.append("$id")
        return stringBuilder.toString()
    }
}
}