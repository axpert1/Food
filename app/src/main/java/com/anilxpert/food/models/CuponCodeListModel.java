package com.anilxpert.food.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 13-Dec-17.
 */

public class CuponCodeListModel {
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("coupon_details")
    @Expose
    public List<CouponDetail> couponDetails = null;
    public class CouponDetail {
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
