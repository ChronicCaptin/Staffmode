package it.mr_replete.staff.reflection;

import java.lang.reflect.Field;

public class Modify {

    public static void set(Object object, String field, Object instance){
        try{
            Field f = object.getClass().getDeclaredField(field);
            f.setAccessible(true);
            f.set(object, instance);
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public static Object get(Object object, String field){
        try{
            Field f = object.getClass().getDeclaredField(field);
            f.setAccessible(true);
            return f.get(object);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
