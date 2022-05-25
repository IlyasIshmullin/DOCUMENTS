package com.example.documents_rewrite.mainApplication.logic

import com.example.documents_rewrite.mainApplication.data.remote.document.DocumentAPI
import kotlinx.coroutines.Dispatchers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.InputStream
import javax.inject.Inject

class LoaderDocument @Inject constructor(private val documentApi: DocumentAPI){

    fun sendFileRequest(inputStream : InputStream) : MultipartBody.Part {
        val byteArray = inputStream.readBytes()
        val body = MultipartBody.Part.createFormData(
            "file[content]", "file",
            byteArray.toRequestBody("*/*".toMediaTypeOrNull(), 0, byteArray.size)
        )
        return body;
    }
}