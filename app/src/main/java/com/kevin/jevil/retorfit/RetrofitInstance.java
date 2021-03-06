package com.kevin.jevil.retorfit;

import com.kevin.devil.DevilNetworkInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://reqres.in/";

    /**
     * Create an instance of a Retrofit object
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            DevilNetworkInterceptor customInterceptor = new DevilNetworkInterceptor();
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(customInterceptor)
                    .addInterceptor(logging)
                    .build();
            retrofit = new retrofit2.Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
