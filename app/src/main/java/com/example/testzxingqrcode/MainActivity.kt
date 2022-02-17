package com.example.testzxingqrcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder

//http://blog.dreamhanks.com/25-%e3%80%90android-kotlin%e3%80%91qr%e3%82%b3%e3%83%bc%e3%83%89%e7%94%9f%e6%88%90/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val qr_code = findViewById<ImageView>(R.id.qr_code)
        val qr_et = findViewById<EditText>(R.id.qr_et)
        val create_qr_btn = findViewById<Button>(R.id.create_qr_btn)

        //生成ボタンのクリックイベントを設定
        create_qr_btn.setOnClickListener {
            val multiFormatWriter = MultiFormatWriter()
            try {
                val bitMatrix =
                    multiFormatWriter.encode(qr_et.text.toString(), BarcodeFormat.QR_CODE, 200, 200)
                val barcodeEncoder = BarcodeEncoder()
                val bitmap = barcodeEncoder.createBitmap(bitMatrix)
                qr_code.setImageBitmap(bitmap)
            } catch (e: Exception) {
            }
        }
    }
}