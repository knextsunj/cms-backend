package com.github.knextsunj.cms.util

open class CmsUtil {

    /**
     * Seen by java as a static equivalent(ie) .class object
     */
    companion object {

        /**
         * Seen by java as a static method
         */
        @JvmStatic
        fun isNull(obj: Any?): Boolean {
            /**
             * Applied the use of the kotlin elvis operator to check for nulls for a java input.If result is -1 then
             * obj is null
             */
            val result = obj?: -1L
            if (result == -1L) {
                return true
            }
            return false
        }

        @JvmStatic
        fun isNumPresent(num:Long?): Boolean {
            val result = num?:-1L
            if (result==-1L) {
                return false
            }
            return true;
        }

    }

    /**
     * Using the as operator in Kotlin to do safe type casting for java!! null is returned
     * if the cast fails.
     * @return MutableList the mutable equivalent of java List
     */
    fun <E> convertToList(listParam:Any): MutableList<E>? {
        val list: MutableList<E>? = listParam as? MutableList<E>
        return list
    }

}