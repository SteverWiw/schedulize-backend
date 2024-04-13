package com.schedulize.backend.adapters.externalapi;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DynamicServiceCall {
    private final OkHttpClient client = new OkHttpClient();


   public ResponseBody get(String baseUrl) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl)
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body();
    }

    public ResponseBody post(String baseUrl, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body();
    }

    public ResponseBody put(String baseUrl, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl)
                .put(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body();
    }

    public ResponseBody delete(String baseUrl) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl)
                .delete()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body();
    }
}
