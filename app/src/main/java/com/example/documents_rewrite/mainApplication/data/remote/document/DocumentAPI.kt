package com.example.documents_rewrite.mainApplication.data.remote.document

import io.reactivex.Completable
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DocumentAPI {
    @POST("./upload-file")
    @Multipart
    suspend fun uploadFile(@Part body: MultipartBody.Part)
}