package tech.gen.githubtesttask.repository

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider

class LoginRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    val isLoggedIn
        get() = firebaseAuth.currentUser != null

    fun login(activity: Activity): Task<AuthResult> {
        val provider = OAuthProvider.newBuilder("github.com")
        return firebaseAuth.startActivityForSignInWithProvider(activity, provider.build())
    }

}