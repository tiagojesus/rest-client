package com.tiagojesus.dev.BusinessRestClient.web;

import com.tiagojesus.dev.business.core.model.Mensagem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @RequestMapping("")
    public Mensagem index(){
        return new Mensagem("msg.cad.0001", "Mensage 0001: New info 00001");
    }

}
