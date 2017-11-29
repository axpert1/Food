package com.anilxpert.food.custom_class;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class GoogleUtils {
    public static String getMapImageURL(double latitude, double longitude, int width, int height) {

        String googleMapsAPIKey = "";

        String api = "https://maps.googleapis.com/maps/api/staticmap";
        String markers = "markers=" + latitude + "," + longitude;
        String size = "zoom=18&size=" + width + "x" + height;
        String key = "key=" + googleMapsAPIKey;

        return api + "?" + markers + "&" + size + "&" + key;
    }
}
