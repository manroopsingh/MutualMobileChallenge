package com.example.user.mutualmobilechallenge.utils;

/**
 * Created by singh on 3/3/18.
 */

public class TagManager {

    public static String get(Object className) {
        return className.getClass().getSimpleName() + "_TAG";
    }
}
