package com.anilxpert.food.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AnilXpert 9887230800 on 11/29/2017.
 */

public class LoginModel {
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("user_details")
    @Expose
    public UserDetails userDetails;

    public class UserDetails {

        @SerializedName("password")
        @Expose
        public String password;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("user_id")
        @Expose
        public String userId;
        @SerializedName("mobile")
        @Expose
        public String mobile;
        @SerializedName("activated")
        @Expose
        public String activated;
        @SerializedName("banned")
        @Expose
        public String banned;
        @SerializedName("f_name")
        @Expose
        public String fName;
        @SerializedName("l_name")
        @Expose
        public String lName;

    }
}
