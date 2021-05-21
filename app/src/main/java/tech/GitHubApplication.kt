package tech

import android.app.Application
import com.google.firebase.FirebaseApp
import tech.gen.githubtesttask.core.network.NetworkUtil
import tech.gen.githubtesttask.core.network.ServerApi

class GitHubApplication: Application() {

    companion object {

        lateinit var serverApi: ServerApi

    }

    override fun onCreate() {
        super.onCreate()

        serverApi = NetworkUtil.createServerApi()
        FirebaseApp.initializeApp(this)
    }

}