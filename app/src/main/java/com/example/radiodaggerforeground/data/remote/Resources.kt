package com.example.radiodaggerforeground.data.remote

object Resources {

    fun generate() : List<RadioStation>{

        val list = arrayListOf<RadioStation>()
        list.add(
                RadioStation(
                        "BBC - Radio 1",
                        "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1_mf_p"
                )
        )
        list.add(
                RadioStation(
                        "BBC - Radio 2",
                        "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio2_mf_p"
                )
        )
        list.add(
                RadioStation(
                        "BBC - Radio 3",
                        "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio3_mf_p"
                )
        )
        list.add(
                RadioStation(
                        "BBC - Radio 4",
                        "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio4fm_mf_p"
                )
        )
        list.add(
                RadioStation(
                        "BBC - Radio 5",
                        "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio5live_mf_p"
                )
        )
        list.add(
                RadioStation(
                        "BBC - Radio 6",
                        "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_6music_mf_p"
                )
        )
        list.add(
                RadioStation(
                        "BBC Radio Asian Network",
                        "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_asianet_mf_p"
                )
        )
        list.add(
                RadioStation(
                        "BBC World Service",
                        "http://bbcwssc.ic.llnwd.net/stream/bbcwssc_mp1_ws-eieuk"
                )
        )

        return list
    }

}
data class RadioStation(
        val name : String,
        val station : String
        )