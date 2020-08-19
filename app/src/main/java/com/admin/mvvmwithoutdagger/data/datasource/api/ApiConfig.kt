package com.admin.mvvmwithoutdagger.data.datasource.api

interface ApiConfig {
    companion object {

        //Timeout
        val READ_TIMEOUT: Long = 5000
        val CONNECT_TIMEOUT: Long = 4000
        val WRITE_TIMEOUT: Long = 4000


        //    String API_KEY = "Authorization";
        //    String API_KEY_VALUE = "Authorization";
        //
        val API_KEY = ""  //  replace with yours if you have any (optional)
        val API_KEY_VALUE = ""    //  replace with yours if you have any (optional)
    }
}
