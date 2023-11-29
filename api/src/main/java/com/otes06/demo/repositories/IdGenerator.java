package com.otes06.demo.repositories;

import java.util.Map;
import java.util.Random;

public class IdGenerator<T> {
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();
    private static final int idLength = 8;
    
    public String getRandomId(Map<String, T> table){
        String newId = getRandomString(idLength);
        if(table.get(newId) == null){
            return newId;
        }
        return getRandomId(table);
    }

    public static String getRandomString(int length) {
        StringBuilder result = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            result.append(CHARACTERS.charAt(randomIndex));
        }
        
        return result.toString();
    }
}
