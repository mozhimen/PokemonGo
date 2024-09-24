package com.hi.dhl.pokemon.restfs.mos

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

data class ListingRes(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ListingDataRes>
)

data class ListingDataRes(
    val name: String,
    val url: String
) {
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
    }

    override fun toString(): String {
        return "ListingData(name='$name', url='$url')"
    }
}