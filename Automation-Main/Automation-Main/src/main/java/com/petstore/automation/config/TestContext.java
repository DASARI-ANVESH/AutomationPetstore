//package com.petstore.automation.config;
import java.util.HashMap;
import java.util.List; 
import java.util.Map;


public class TestContext {
    private static final ThreadLocal<Map<String, Object>> context=ThreadLocal.withInitial(HashMap::new);
    
    public static void set(String key,Object value){
        context.get().put(key,value);

    }
    @SuppressWarnings("unchecked")
    public static <T> T get(String key){
        return (T) context.get().get(key);
    }
    @SuppressWarnings("unchecked")
    public static <T> T get(String key,Class<T> type){
        return (T) context.get().get(key);
}
    public static void clear(){
        context.get().clear();
    }
    public static final String PET_NAMES="pet_names";
    public static final String API_RESPONSE="api_response";
    public static final String CREATED_PET_ID="created_pet_id";
}
