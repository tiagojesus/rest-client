package com.tiagojesus.dev.businessRestClient;

import com.tiagojesus.dev.business.BusinessException;
import com.tiagojesus.dev.business.RestClient.AutorizationInterceptor;
import com.tiagojesus.dev.business.RestClient.RestUser;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class BusinessRestClient {
    protected final Retrofit retrofit;
    private RestUser user;
    private String baseUrl;

    public BusinessRestClient(String baseUrl, RestUser user) {
        this.user = user;
        this.baseUrl = baseUrl;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new AutorizationInterceptor(user))
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T>  T getService(Class<T> tClass){
        return retrofit.create(tClass);
    }

    public static <T>  T  execute(Class<T> mensagemClass, Call<T> call) {
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Não foi possivel executar a operação", e);
        }
    }
}
