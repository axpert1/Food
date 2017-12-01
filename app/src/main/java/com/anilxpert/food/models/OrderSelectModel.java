package com.anilxpert.food.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/30/2017.
 */

public class OrderSelectModel {
    //  {"status":1,"message":"List of Products","Dry_Soup":[{"id":"1","category_name":"Dry or Soup","product_name":"Mild Spicy","price":"0","activated":"1","banned":"0","is_delete":"0","created_at":"2017-11-24 17:47:08","updated_at":"2017-11-28 11:26:27"},{"id":"4","category_name":"Dry or Soup","product_name":"ddd","price":"0","activated":"1","banned":"0","is_delete":"0","created_at":"2017-11-30 08:26:11","updated_at":"2017-11-30 08:26:11"}],"Spiciness_Level":[{"id":"2","category_name":"Spiciness Level","product_name":"abc","price":"0","activated":"1","banned":"0","is_delete":"0","created_at":"2017-11-30 08:25:43","updated_at":"2017-11-30 08:25:43"},{"id":"5","category_name":"Spiciness Level","product_name":"xxx","price":"0","activated":"1","banned":"0","is_delete":"0","created_at":"2017-11-30 08:26:23","updated_at":"2017-11-30 08:26:23"}],"Mala_Xiang_Guo":[{"id":"3","category_name":"Mala Xiang Guo","product_name":"xyz","price":"10","activated":"1","banned":"0","is_delete":"0","created_at":"2017-11-30 08:25:56","updated_at":"2017-11-30 08:25:56"},{"id":"6","category_name":"Mala Xiang Guo","product_name":"mala","price":"20","activated":"1","banned":"0","is_delete":"0","created_at":"2017-11-30 08:26:35","updated_at":"2017-11-30 08:26:35"}]}
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("Dry_Soup")
    @Expose
    public List<DrySoup> drySoup = null;
    @SerializedName("Spiciness_Level")
    @Expose
    public List<SpicinessLevel> spicinessLevel = null;
    @SerializedName("Mala_Xiang_Guo")
    @Expose
    public List<MalaXiangGuo> malaXiangGuo = null;

    public class MalaXiangGuo {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("category_name")
        @Expose
        public String categoryName;
        @SerializedName("product_name")
        @Expose
        public String productName;
        @SerializedName("price")
        @Expose
        public String price;
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

    public class SpicinessLevel {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("category_name")
        @Expose
        public String categoryName;
        @SerializedName("product_name")
        @Expose
        public String productName;
        @SerializedName("price")
        @Expose
        public String price;
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

    public class DrySoup {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("category_name")
        @Expose
        public String categoryName;
        @SerializedName("product_name")
        @Expose
        public String productName;
        @SerializedName("price")
        @Expose
        public String price;
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
