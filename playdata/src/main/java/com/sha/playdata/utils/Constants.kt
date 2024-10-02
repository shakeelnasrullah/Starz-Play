package com.sha.playdata.utils

import com.sha.playdata.data.models.Media


object Constants {

    const val mediaKey = "media.to.play"
    const val languageExtras = "lang.selected"
    const val VIDEO_URL = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"

    // Filter the list according to media type
    private fun filterMediaTypes(mediaList: List<Media>): HashMap<String, ArrayList<Media>>{
        val mediaMap = HashMap<String, ArrayList<Media>>()
        for (media in mediaList){
            if(mediaMap.containsKey(media.mediaType)){
                val list : ArrayList<Media>  = mediaMap.getValue(media.mediaType)
                list.add(media)
            }else{
                mediaMap.put(media.mediaType, arrayListOf(media))
            }
        }
        return mediaMap
    }

}