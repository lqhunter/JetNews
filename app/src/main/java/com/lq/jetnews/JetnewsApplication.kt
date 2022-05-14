package com.lq.jetnews

import android.app.Application
import com.lq.jetnews.data.AppContainer
import com.lq.jetnews.data.AppContainerImpl

class JetnewsApplication :Application(){

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl()
    }
}