package com.example.moviedemo.networkClass;

import android.util.Log;

import androidx.constraintlayout.widget.Constraints;

import com.bumptech.glide.load.Key;
import com.example.moviedemo.constants.Constants;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class HeaderInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName(Key.STRING_CHARSET_NAME);

    public Response intercept(Chain chain) throws IOException {
       // Request request = chain.request().newBuilder().addHeader(Constants.ACCESS_TOKEN, BMSPrefs.getString(null, Constants.DEVICE_TOKEN)).build();
        Request request = chain.request().newBuilder()
                .addHeader(Constants.ACCESS_TOKEN,"").build();

        if (request != null) {
        }
        Response response = chain.proceed(request);
        try {
            showResponseBody(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private void showResponseBody(Response response) {
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e2) {
                Log.i(Constraints.TAG, "");
                Log.i(Constraints.TAG, "Couldn't decode the response body; charset is likely malformed.");
                Log.i(Constraints.TAG, "<-- END HTTP");
            }
        }
        if (responseBody.contentLength() != 0) {
            Log.i(Constraints.TAG, "");
            Log.i(Constraints.TAG, buffer.clone().readString(charset));
        }
        String str = Constraints.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<-- END HTTP (");
        stringBuilder.append(buffer.size());
        stringBuilder.append("-byte body)");
        Log.i(str, stringBuilder.toString());
    }
}
