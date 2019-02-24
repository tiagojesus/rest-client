package com.tiagojesus.dev.BusinessRestClient.web;

import com.tiagojesus.dev.business.core.model.BusinessNotification;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public BusinessNotification index(){
        return new BusinessNotification("msg.cad.0001", "Mensage 0001: New info 00001");
    }

    @RequestMapping( method = RequestMethod.POST)
    public BusinessNotification post(@RequestBody @Nullable BusinessNotification notification){
        if(!Optional.ofNullable(notification).isPresent()){
            return new BusinessNotification();
        }
        return notification;
    }
}
