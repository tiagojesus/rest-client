package com.tiagojesus.dev.business.RestClient;

import com.tiagojesus.dev.business.BusinessException;
import com.tiagojesus.dev.business.core.model.BusinessNotification;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class BusinessRestClient {
    protected final OkHttpClient client;
    private final Optional<RestUser> user;

    public BusinessRestClient(){
        this(null);
    }

    public BusinessRestClient(RestUser user) {
        this.user = Optional.ofNullable(user);
        client = createHttpCliente();
    }

    private OkHttpClient createHttpCliente() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggingInterceptor());

        if(this.user.isPresent()){
            builder.authenticator(getAuthenticator(this.user.get()));
        }
        return builder.build();
    }

    private Authenticator getAuthenticator(RestUser user) {
        if(user == null) return null;

        return new DefaultRestAuthenticator(user);
    }

    public static String buildUrl(String url, Map<String, String> params){
        Optional<Map<String, String>> map = Optional.ofNullable(params);
        HttpUrl.Builder urlBuider = HttpUrl.parse(url).newBuilder();
        if (map.isPresent()) {
            for(Map.Entry<String, String> param : map.get().entrySet()) {
                urlBuider.addQueryParameter(param.getKey(),param.getValue());
            }
        }
        return urlBuider.build().toString();
    }

    public String GET(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return execute(request);
    }

    public String POST(String url) {
        return POST(url, "");
    }

    public String POST(String url, String param) {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.get("application/json; charset=utf-8"),param))
                .build();

        return execute(request);
    }

    private String execute(Request request) {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new BusinessException("Unexpected code " + response);
            return response.body().string();
        } catch (IOException e) {
            throw new BusinessException("Problem with the connection", e);
        }
    }




//    public <T>  T getService(Class<T> tClass){
//        return client.create(tClass);
//    }
//
//    public static <T>  T  execute(Class<T> mensagemClass, Call<T> call) {
//        try {
//            return call.execute().body();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new BusinessException("Não foi possivel executar a operação", e);
//        }
//    }
}
