package com.example.a24012011037_mad_prac3

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.CallLog
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri
import android.Manifest
import android.provider.AlarmClock
import android.provider.MediaStore
//final
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        implicitIntent()
        explicitIntent()
    }

    fun implicitIntent() {
        findViewById<Button>(R.id.btn_Browse).setOnClickListener {
            val url = findViewById<EditText>(R.id.editTextText).text.toString()
            Intent(Intent.ACTION_VIEW, url.toUri()).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.btn_Call).setOnClickListener {
            val number = findViewById<EditText>(R.id.editTextText2).text.toString()
            Intent(Intent.ACTION_DIAL).apply {
                data = "tel:$number".toUri()
            }.also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.btn_Calllog).setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CALL_LOG
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_CALL_LOG), 100
                )
            } else {
                openCallLog()
            }
        }

        findViewById<Button>(R.id.btn_Gallery).setOnClickListener {
            Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.btn_Camera).setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.btn_Alarm).setOnClickListener {
            Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_HOUR, 7)
                putExtra(AlarmClock.EXTRA_MINUTES, 30)
                putExtra(AlarmClock.EXTRA_MESSAGE, "Wake Up")
            }.also {
                startActivity(it)
            }
        }
    }
    fun explicitIntent() {
        findViewById<Button>(R.id.btn_Login).setOnClickListener {
            Intent(this, LoginActivity::class.java).also{
                startActivity(it)
            }
        }

    }

    private fun openCallLog(){
        Intent(Intent.ACTION_VIEW, CallLog.Calls.CONTENT_URI).also {
            startActivity(it)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCallLog()
        }
    }

}