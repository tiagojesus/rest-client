package com.tiagojesus.dev.business.RestClient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BusinessLogger {
    public static void INFO(Class tClass, String ...info){
        Logger logger = LoggerFactory.getLogger(tClass.getCanonicalName());

        StringBuilder x = new StringBuilder("\n\n");
        for (String item: info){
            x.append(String.format("%s \n",  item));
        }

        logger.info(x.toString());
    }
}
