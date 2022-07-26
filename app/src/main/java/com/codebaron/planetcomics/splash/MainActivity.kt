package com.codebaron.planetcomics.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.codebaron.planetcomics.R
import com.codebaron.planetcomics.Utils.errorToast
import com.codebaron.planetcomics.Utils.isNetworkAvailable
import com.codebaron.planetcomics.home.ComicsHomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.let {
            if (isNetworkAvailable(this)) {
                lifecycleScope.launch {
                    delay(3000)
                    startActivity(Intent(this@MainActivity, ComicsHomeActivity::class.java))
                }
            } else {
                errorToast(this, "Poor internet connection", Toast.LENGTH_LONG)
            }
        }
    }
}