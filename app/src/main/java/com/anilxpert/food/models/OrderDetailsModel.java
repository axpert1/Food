package com.anilxpert.food.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AnilXpert 9887230800 on 13-Dec-17.
 */

public class OrderDetailsModel {
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("order_details")
    @Expose
    public OrderDetails orderDetails;

    public class Locations {

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

    public class OrderDetails {

        @SerializedName("order_id")
        @Expose
        public String orderId;
        @SerializedName("user_id")
        @Expose
        public String userId;
        @SerializedName("date")
        @Expose
        public String date;
        @SerializedName("time")
        @Expose
        public String time;
        @SerializedName("order_json")
        @Expose
        public String orderJson;
        @SerializedName("spiceness_level")
        @Expose
        public String spicenessLevel;
        @SerializedName("dry_or_soup")
        @Expose
        public String dryOrSoup;
        @SerializedName("total_amount")
        @Expose
        public String totalAmount;
        @SerializedName("rest_amount")
        @Expose
        public String restAmount;
        @SerializedName("notes")
        @Expose
        public String notes;
        @SerializedName("banned")
        @Expose
        public String banned;
        @SerializedName("locations")
        @Expose
        public Locations locations;

    }
}
