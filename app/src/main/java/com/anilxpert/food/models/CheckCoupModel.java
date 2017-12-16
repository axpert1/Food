package com.anilxpert.food.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AnilXpert 9887230800 on 12-Dec-17.
 */

public class CheckCoupModel {

    @SerializedName("coupon_details")
    @Expose
    public CouponDetails couponDetails;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public Integer status;

    public class CouponDetails {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("promo_code")
        @Expose
        public String promoCode;
        @SerializedName("start_date")
        @Expose
        public String startDate;
        @SerializedName("end_date")
        @Expose
        public String endDate;
        @SerializedName("discount_amount")
        @Expose
        public Integer discountAmount;
        @SerializedName("discount_unit")
        @Expose
        public String discountUnit;
        @SerializedName("no_of_usage")
        @Expose
        public String noOfUsage;
        @SerializedName("usage_per_user")
        @Expose
        public String usagePerUser;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("min_ord_amount")
        @Expose
        public String minOrdAmount;
        @SerializedName("entry_date")
        @Expose
        public String entryDate;

    }
}
