package com.anilxpert.food.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AnilXpert 9887230800 on 11/29/2017.
 */

public class CommonModel {
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("msg")
    @Expose
    public String msg;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("user_id")
    @Expose
    public String userId;
}
