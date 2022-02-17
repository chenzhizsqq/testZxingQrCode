package com.example.testzxingqrcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeEncoder

//http://blog.dreamhanks.com/25-%e3%80%90android-kotlin%e3%80%91qr%e3%82%b3%e3%83%bc%e3%83%89%e7%94%9f%e6%88%90/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //做二维码
        makeCode()

        //扫二维码
        scanCode()
    }

    private fun scanCode() {
        val scan_btn = findViewById<Button>(R.id.scan_btn)        //スキャンボタン

        //スキャンボタンのクリックイベントを設定
        scan_btn.setOnClickListener {
            val qrScan = IntentIntegrator(this)
            qrScan.setOrientationLocked(false)
            qrScan.setPrompt("请扫二维码")
            qrScan.initiateScan()

            val scan_result = findViewById<TextView>(R.id.scan_result)        //二维码结果
            scan_result.text = ""
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val result =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            val scan_result = findViewById<TextView>(R.id.scan_result)        //二维码结果
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()

                scan_result.text = "扫码不成功"
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()

                scan_result.text = result.contents

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun makeCode() {
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