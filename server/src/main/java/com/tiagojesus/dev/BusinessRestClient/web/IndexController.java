package com.tiagojesus.dev.BusinessRestClient.web;

import com.tiagojesus.dev.business.core.model.BusinessNotification;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class IndexController {
    @RequestMapping(path = {"basic/", "form/"})
    public BusinessNotification index(){
        return new BusinessNotification("msg.cad.0001", "Mensage 0001: New info 00001");
    }

    @RequestMapping(path = {"basic/", "form/"}, method = RequestMethod.POST)
    public BusinessNotification post(@RequestBody @Nullable BusinessNotification notification){
        if(!Optional.ofNullable(notification).isPresent()){
            return new BusinessNotification();
        }
        return notification;
    }

    @RequestMapping(path = {"basic/for/{owner}", "form/for/{owner}"})
    public List<BusinessNotification> from(@PathVariable String owner){
        return Arrays.asList(
                new BusinessNotification("10.01", "notification "+owner),
                new BusinessNotification("10.02", "notification "+owner),
                new BusinessNotification("10.03", "notification "+owner),
                new BusinessNotification("10.04", "notification "+owner),
                new BusinessNotification("10.05", "notification "+owner),
                new BusinessNotification("10.06", "notification "+owner)
        );
    }
}
