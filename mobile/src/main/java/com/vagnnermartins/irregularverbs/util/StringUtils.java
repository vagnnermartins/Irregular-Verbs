package com.vagnnermartins.irregularverbs.util;

/**
 * Created by vagnnermartins on 05/02/16.
 */
public class StringUtils {

    public static boolean compareVerb(String verb, String spell){
        boolean result = false;
        String[] results = verb.split(" / ");
        for(String item : results){
            if(item.equals(spell)){
                result = true;
            }
        }
        return result;
    }
}
