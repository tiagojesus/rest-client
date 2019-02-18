package com.tiagojesus.dev;

import com.tiagojesus.dev.business.RestClient.RestUser;
import com.tiagojesus.dev.business.RestClient.RestUserBasic;
import com.tiagojesus.dev.business.core.model.Mensagem;
import com.tiagojesus.dev.businessRestClient.BusinessRestClient;
import com.tiagojesus.dev.businessRestClient.MensagemApi;

import static com.tiagojesus.dev.businessRestClient.BusinessRestClient.execute;

public class Main {
    public static void main(String[] args){
        RestUser user = new RestUserBasic("user", "password", "App1");

        BusinessRestClient cliente = new BusinessRestClient("http://localhost:8080", user);

        MensagemApi mensagemApi = cliente.getService(MensagemApi.class);
        Mensagem mensagem = execute(Mensagem.class,  mensagemApi.ultimaMensagem());

        System.out.println(mensagem);
    }
}