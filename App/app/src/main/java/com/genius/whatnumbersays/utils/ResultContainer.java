package com.genius.whatnumbersays.utils;

import java.io.Serializable;

/**
 * Created by ADMIN on 10/6/2017.
 */

public class ResultContainer implements Serializable {
    public static String title;
    public static String fact;
    public static String category;

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        ResultContainer.title = title;
    }

    public static String getFact() {
        return fact;
    }

    public static void setFact(String fact) {
        ResultContainer.fact = fact;
    }

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        ResultContainer.category = category;
    }
}
