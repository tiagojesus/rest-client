package com.tiagojesus.dev.businessRestClient;

import com.tiagojesus.dev.business.core.model.Mensagem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MensagemApi{
  @GET("/")
  Call<Mensagem> ultimaMensagem();
}