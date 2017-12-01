package com.anilxpert.food.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/30/2017.
 */

public class FindUsModel {
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("locations")
    @Expose
    public List<LocationsData> locations = null;

    public class LocationsData {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("location")
        @Expose
        public String location;
        @SerializedName("land_mark")
        @Expose
        public String landMark;
        @SerializedName("country")
        @Expose
        public String country;
        @SerializedName("state")
        @Expose
        public String state;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("pincode")
        @Expose
        public String pincode;
        @SerializedName("lat")
        @Expose
        public String lat;
        @SerializedName("lon")
        @Expose
        public String lon;
        @SerializedName("open_day")
        @Expose
        public String openDay;
        @SerializedName("close_day")
        @Expose
        public String closeDay;
        @SerializedName("open_time")
        @Expose
        public String openTime;
        @SerializedName("close_time")
        @Expose
        public String closeTime;
        @SerializedName("activated")
        @Expose
        public String activated;
        @SerializedName("banned")
        @Expose
        public String banned;
        @SerializedName("is_delete")
        @Expose
        public String isDelete;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;

    }
}
