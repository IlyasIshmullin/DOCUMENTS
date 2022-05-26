package com.example.documents_rewrite.mainApplication.app.remote.document;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DocumentUploadApi {
    @Multipart
    @POST("upload-file")
    Call<ResponseBody> uploadDocument(@Part MultipartBody.Part file);
}
