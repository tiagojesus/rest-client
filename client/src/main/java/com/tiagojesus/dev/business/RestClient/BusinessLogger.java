package com.tiagojesus.dev.business.RestClient;

import java.util.logging.Logger;

public final class BusinessLogger {
    public static void INFO(Class tClass, String ...info){
        Logger logger = Logger.getLogger(tClass.getCanonicalName());
        StringBuilder x = new StringBuilder("\n\n");
        for (String item: info){
            x.append(String.format("%s \n",  item));
        }
        logger.info(x.toString());
    }
}
