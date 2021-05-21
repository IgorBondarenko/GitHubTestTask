package tech.gen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import tech.gen.githubtesttask.MainActivity
import tech.gen.githubtesttask.R
import tech.gen.githubtesttask.databinding.ActivityStartBinding
import tech.gen.githubtesttask.ui.start.StartViewModel

class StartActivity : AppCompatActivity(R.layout.activity_start) {

    private lateinit var binding: ActivityStartBinding
    private lateinit var startViewModel: StartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startViewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        if (startViewModel.isLoggedIn) startMainScreen()

        binding.buttonLogin.setOnClickListener {
            startViewModel.login(this)
                .addOnSuccessListener {
                    startMainScreen()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun startMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}