package com.anilxpert.food.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 12/5/2017.
 */

public class FbJson {
    /**
     * {"id":"1651533878237932","name":"Anil Xpert",
     * "location":{"id":"106306812739694","name":"Jaipur, Rajasthan"},
     * "likes":{"data":[{"name":"Gold Coast Web Technology","id":"147790562432064"},{"name":"PlayUp","id":"251608648230591"},{"name":"Golmaal Again","id":"1459108890784912"},{"name":"Jessica Alba","id":"298334973652"},{"name":"Wingstud Infotech Pvt. Ltd.","id":"906612122711445"},{"name":"PayUmoney","id":"263409083860142"},{"name":"Ravi Kumar Sharma","id":"279169345531634"},{"name":"Chill & Grills","id":"514947642017943"},{"name":"Microsoft Lumia","id":"8195378771"},{"name":"Simplified Coding","id":"424235614384532"},{"name":"Volkswagen","id":"126105220733362"},{"name":"UC Browser","id":"240314422692625"},{"name":"Shri Bhawani Niketan Institute of Technology and Management","id":"817849081597657"},{"name":"GoHouster.com","id":"1543095332629754"},{"name":"Vivekanand Vidhya Bhawan Sr. Sec. School","id":"194511550571991"},{"name":"Bigg Boss","id":"599185270096920"},{"name":"Pamela Anderson","id":"64704837071"},{"name":"प्रधानमंत्री नरेन्द्र मोदी","id":"233621316834896"},{"name":"Windows","id":"143325535838863"},{"name":"Teri Meri Kahani","id":"220930277978256"},{"name":"Farhan Saeed","id":"658865754190216"},{"name":"Bollywood Tarka","id":"620366354717708"},{"name":"ITashan.com","id":"349860251797745"},{"name":"IPL - Indian Premier League","id":"69553328633"},{"name":"Tanvi Vyas","id":"38776574718"}],
     * "paging":{"cursors":{"before":"MTQ3NzkwNTYyNDMyMDY0","after":"Mzg3NzY1NzQ3MTgZD"},"next":"https://graph.facebook.com/v2.11/1651533878237932/likes?access_token=EAACEdEose0cBAHzye5tFuYM8ZCyt7KkqFYajhDn3iAuZAgZCkmZAyx2MffUXyoswcZBCUP0FiNQZB6rySjR289DKEplgBlnmfTDWuUDsCcCqSLRwfZAqJNctP7pYZBVkoOG5Q1w77xloOUJVSOzKCVJivkqWuI1JXCpvSltZBeZAUSZCfhZBbbtjTWoU3jjKZBU5Woz3jSZBZAtoMrGN6BvSSQjvIIT67WfH3VSSJmXCvbaJX0wuQZDZD&pretty=0&fields=name&limit=25&after=Mzg3NzY1NzQ3MTgZD"}},
     * "cover":{"id":"338202399571093","offset_x":0,"offset_y":0,"source":"https://scontent.xx.fbcdn.net/v/t31.0-8/s720x720/477126_338202399571093_1283572582_o.jpg?oh=c4bd80a789046aac3c860a64a00268ed&oe=5A9CA27A"},
     * "education":[{"school":{"id":"407081402743164","name":"Vivekanand vidhya bhawan,08"},"type":"High School","id":"564975653560432"},{"school":{"id":"257230464298073","name":"Vivekanand Sec. Sr. School,jaipur"},"type":"High School","year":{"id":"140617569303679","name":"2007"},"id":"212863788771622"},{"school":{"id":"125570270818007","name":"SBNITM"},"type":"College","id":"156310054426996"},{"school":{"id":"103143416393042","name":"Rajasthan Technical University"},"type":"College","id":"147660738625261"}],"email":"a.jangid5626@gmail.com","first_name":"Anil","last_name":"Xpert","picture":{"data":{"url":"https://scontent.xx.fbcdn.net/v/t1.0-1/p50x50/12644789_1045924028798923_8748259290023216474_n.jpg?oh=6ce1eb2ca4e8b2934f93724fd85e4222&oe=5A9237AA"}},"age_range":{"min":21},"gender":"male","verified":true,"updated_time":"2017-05-22T07:20:51+0000"}
     */


    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("likes")
    @Expose
    public Likes likes;
    @SerializedName("cover")
    @Expose
    public Cover cover;
    @SerializedName("education")
    @Expose
    public List<Education> education = null;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("picture")
    @Expose
    public Picture picture;
    @SerializedName("age_range")
    @Expose
    public AgeRange ageRange;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("verified")
    @Expose
    public Boolean verified;
    @SerializedName("updated_time")
    @Expose
    public String updatedTime;


    public class AgeRange {

        @SerializedName("min")
        @Expose
        public Integer min;

    }

    public class Cover {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("offset_x")
        @Expose
        public Integer offsetX;
        @SerializedName("offset_y")
        @Expose
        public Integer offsetY;
        @SerializedName("source")
        @Expose
        public String source;

    }

    public class Cursors {

        @SerializedName("before")
        @Expose
        public String before;
        @SerializedName("after")
        @Expose
        public String after;

    }

    public class Data {

        @SerializedName("url")
        @Expose
        public String url;

    }

    public class Datum {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("id")
        @Expose
        public String id;

    }

    public class Education {

        @SerializedName("school")
        @Expose
        public School school;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("year")
        @Expose
        public Year year;

    }

    public class Likes {

        @SerializedName("data")
        @Expose
        public List<Datum> data = null;
        @SerializedName("paging")
        @Expose
        public Paging paging;

    }

    public class Location {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("name")
        @Expose
        public String name;

    }

    public class Paging {

        @SerializedName("cursors")
        @Expose
        public Cursors cursors;
        @SerializedName("next")
        @Expose
        public String next;

    }

    public class Picture {

        @SerializedName("data")
        @Expose
        public Data data;

    }

    public class School {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("name")
        @Expose
        public String name;

    }

    public class Year {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("name")
        @Expose
        public String name;

    }
}
