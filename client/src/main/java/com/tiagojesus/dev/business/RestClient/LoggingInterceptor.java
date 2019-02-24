package com.tiagojesus.dev.business.RestClient;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class LoggingInterceptor implements Interceptor {
  @Override
  public Response intercept(Interceptor.Chain chain) throws IOException {
    Request request = chain.request();

    long t1 = System.nanoTime();
    BusinessLogger.INFO(LoggingInterceptor.class,
            String.format("\n*** Sending request %s\n", request.url()),
            chain.connection().toString(),
            request.headers().toString()
    );

    Response response = chain.proceed(request);

    long t2 = System.nanoTime();
    BusinessLogger.INFO(LoggingInterceptor.class,
            String.format("*** Received response for %s in %.1fms%n", response.request().url(), (t2 - t1) / 1e6d),
            response.headers().toString());

    return response;
  }
}