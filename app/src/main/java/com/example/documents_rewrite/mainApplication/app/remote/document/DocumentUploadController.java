package com.example.documents_rewrite.mainApplication.app.remote.document;

import android.util.Log;

import com.example.documents_rewrite.mainApplication.app.remote.RetrofitClientInstance;
import com.github.mikephil.charting.utils.FileUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentUploadController {
    DocumentUploadApi documentUpload = RetrofitClientInstance
            .getRetrofitInstance()
            .create(DocumentUploadApi.class);

    public void upload(String mediaPath) {
        File file = new File(mediaPath);
        RequestBody requestDocument = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part document = MultipartBody.Part
                .createFormData("file", file.getName(), requestDocument);
        Call<ResponseBody> call = documentUpload.uploadDocument(document);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }
}
