package com.example.documents_rewrite.mainApplication.app

import android.app.Application
import android.graphics.Bitmap
import com.krobys.documentscanner.ui.DocumentScanner

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        val configuration = DocumentScanner.Configuration()
        configuration.imageQuality = 1
        configuration.imageSize = 1000000 // 1 MB
        configuration.imageType = Bitmap.CompressFormat.PNG
        DocumentScanner.init(this, configuration) // or simply DocumentScanner.init(this)
    }

}