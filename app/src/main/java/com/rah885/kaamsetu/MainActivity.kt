package com.rah885.kaamsetu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.rah885.kaamsetu.ui.screens.home.HomeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                HomeScreen(
                    onCustomerClick = {
                        // ग्राहक वाला अगला screen बाद में जोड़ेंगे
                    },
                    onWorkerClick = {
                        // कामगार वाला अगला screen बाद में जोड़ेंगे
                    }
                )
            }
        }
    }
}
