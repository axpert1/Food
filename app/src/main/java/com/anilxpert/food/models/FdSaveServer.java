package com.anilxpert.food.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AnilXpert 9887230800 on 12/6/2017.
 */

public class FdSaveServer {
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("f_name")
    @Expose
    public String fName;
    @SerializedName("l_name")
    @Expose
    public String lName;
}
