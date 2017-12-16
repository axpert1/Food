package com.anilxpert.food.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AnilXpert 9887230800 on 13-Dec-17.
 */

public class OrderModel {
    @SerializedName("order_id")
    @Expose
    public String orderId;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public Integer status;
}
