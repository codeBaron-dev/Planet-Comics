package com.codebaron.planetcomics.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.codebaron.planetcomics.R
import com.codebaron.planetcomics.Utils.isNetworkAvailable
import com.codebaron.planetcomics.Utils.showMessageDialog
import com.codebaron.planetcomics.home.ComicsHomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Anyanwu Nicholas(codeBaron)
 * @since July 25 - 2022
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * checks if device is connected to internet and performs required action based
         * on the outcome of the condition
         */
        this.let {
            if (isNetworkAvailable(this)) {
                lifecycleScope.launch {
                    delay(3000)
                    startActivity(Intent(this@MainActivity, ComicsHomeActivity::class.java))
                }
            } else {
                showMessageDialog(it, "Internet Connection Error",
                    "Please ensure you have a stable internet connection")
            }
        }
    }
}