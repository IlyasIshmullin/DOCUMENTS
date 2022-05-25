package com.example.documents_rewrite.mainApplication.app.documents

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import com.example.documents_rewrite.R
import com.krobys.documentscanner.ScanActivity
import com.krobys.documentscanner.model.DocumentScannerErrorModel
import com.krobys.documentscanner.model.ScannerResults
import org.opencv.android.OpenCVLoader
import java.io.File

class AppScanActivity : ScanActivity() {

    private var numberOfFile: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_scan)
        addFragmentContentLayout()
        Log.d("AppScanActivity", "OnCreate")
        println("OnCreate AppScanActivity")
        Toast.makeText(applicationContext, "onCreate", Toast.LENGTH_LONG).show()

        if(OpenCVLoader.initDebug())
            Log.d("GAppScanActivity", "openCVDubeg")
        else
            Log.d("AppScanActivity", "OpenCVnotDebug")

    }

    override fun onSuccess(scannerResults: ScannerResults) {
        Log.d("AppScanActivity", "OnSuccess")
        Toast.makeText(applicationContext, "onSuccess", Toast.LENGTH_LONG).show()
//        nameOfFile(scannerResults.imageFile)
//        imageOfFile(scannerResults.imageFile)
        //sendFile(scannerResults.imageFile)
        finish()
    }
    /*public fun sendFile(file: File?) : (String, Int) {

        return "$numberOfFile", 1
    }*/

    /*public fun nameOfFile(file: File?) : String{
        numberOfFile++
        return "scanned File ${numberOfFile}"
    }
    */
    override fun onClose() {
        Toast.makeText(applicationContext, "OnClose", Toast.LENGTH_LONG).show()
        Log.d("AppScanActivity", "OnClose")

    }

    override fun onError(error: DocumentScannerErrorModel) {
        Toast.makeText(applicationContext, "onError", Toast.LENGTH_LONG).show()
        Log.e("AppScanActivity", "onError")
    }



}