package com.anilxpert.food.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 13-Dec-17.
 */

public class OrderListModel {
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("order_details")
    @Expose
    public List<OrderDetail> orderDetails = null;
    public class OrderDetail {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("order_id")
        @Expose
        public String orderId;
        @SerializedName("user_id")
        @Expose
        public String userId;
        @SerializedName("location_id")
        @Expose
        public String locationId;
        @SerializedName("mobile")
        @Expose
        public String mobile;
        @SerializedName("email")
        @Expose
        public String email;
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
