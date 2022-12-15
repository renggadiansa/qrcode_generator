package com.example.qrcodegenerator

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.content.getSystemService
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var qrcode: ImageView
    private lateinit var idedit: EditText
    private lateinit var btngenerator: MaterialButton

    var bitmap : Bitmap? = null
    var qrgEnCoder : QRGEncoder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        qrcode = findViewById(R.id.qrcode)
        idedit = findViewById(R.id.idedit)
        btngenerator = findViewById(R.id.btngenerator)

        btngenerator.setOnClickListener{
            if (TextUtils.isEmpty(idedit.text.toString())){
                Toast.makeText(this, "Please Enter ID", Toast.LENGTH_SHORT).show()
        }else{
               val manager = getSystemService(WINDOW_SERVICE) as WindowManager
                val display = manager.defaultDisplay
                val point = Point()
                display.getSize(point)
                val width = point.x
                val height = point.y
                var dimen = if (width < height) width else height

                dimen = dimen *3/4

                qrgEnCoder = QRGEncoder(idedit.text.toString(), null, QRGContents.Type.TEXT, dimen)
                try {
                    bitmap = qrgEnCoder!!.bitmap
                    qrcode.setImageBitmap(bitmap)
                }catch (e: Exception){
                    Log.e("tag", e.toString())
                }
            }
        }
    }
}