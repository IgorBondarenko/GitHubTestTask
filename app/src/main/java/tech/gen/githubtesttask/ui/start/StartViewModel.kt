package tech.gen.githubtesttask.ui.start

import android.app.Activity
import androidx.lifecycle.ViewModel
import tech.gen.githubtesttask.repository.LoginRepository

class StartViewModel : ViewModel() {

    private val loginRepository by lazy { LoginRepository() }

    val isLoggedIn: Boolean
        get() = loginRepository.isLoggedIn

    fun login(activity: Activity) =
        loginRepository.login(activity)

}