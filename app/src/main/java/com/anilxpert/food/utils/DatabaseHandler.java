package com.anilxpert.food.utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHandler extends SQLiteOpenHelper {

    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME = "FOOD_APP";
    static String TABLE_NAME = "ORDER";
    static String NAME = "name";
    static String PRICE = "price";
    static String ID = "id";
    static String ITME = "itme";
    static String P_ID = "_id";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
        // dbb= context.openOrCreateDatabase(DATABASE_NAME,
        // SQLiteDatabase.CREATE_IF_NECESSARY,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table " + TABLE_NAME + "(" + P_ID + "  INTEGER PRIMARY KEY," + NAME + " text," + PRICE + " text," + ID + " text," + ITME + " text)");
        //   db.execSQL("create table UserOrder(o_id  INTEGER PRIMARY KEY,TABLE_NO text,DAP_id text,ORDER_NO text,M_ORDER_ID text,ITEM text,COVER text,TYPE text)");
        //   db.execSQL("create table UserDemoCart(p_id  INTEGER PRIMARY KEY,_name text,_price text,_id text,_item text,TABLE_NO text,ismodifire text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//    public String get_printerIP(String printer_name) {
//        String printerip = "";
//        SQLiteDatabase usersUser_name = this.getWritableDatabase();
//        Cursor cursor = usersUser_name.rawQuery(
//                "select p_ip from Printer where p_name = ?",
//                new String[]{printer_name});
//        // Cursor cursor = usersUser_name.rawQuery("select p_ip from Printer where p_name=" + printer_name, null);
//        if (cursor.moveToFirst()) {
//            do {
//                printerip = cursor.getString(cursor.getColumnIndex("p_ip"));
//            } while (cursor.moveToNext());
//
//        }
//
//        return printerip;
//    }

    public String Delete_table2(String table_name) {
        String dlt_msg = "Delete_table";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + table_name);
        db.close();
        return dlt_msg;
    }

    //      //////////////////      cart    ////////////////////////////
//    public String insert_ORDER(String TABLE_NO, String ORDER_NO, String M_ORDER_ID, String ITEM, String COVER, String TYPE, String DAP_id) {
//        String login_msg = "Save";
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("TABLE_NO", TABLE_NO);
//        contentValues.put("ORDER_NO", ORDER_NO);
//        contentValues.put("M_ORDER_ID", M_ORDER_ID);
//        contentValues.put("COVER", COVER);
//        contentValues.put("TYPE", TYPE);
//        contentValues.put("ITEM", ITEM);
//        contentValues.put("DAP_id", DAP_id);
//        Cursor cursor = db.query("UserOrder", null, " TABLE_NO=?",
//                new String[]{TABLE_NO}, null, null, null);
//        if (cursor.getCount() > 0) {
//            update_UserOrder(TABLE_NO, TYPE, ITEM);
//        } else {
//            db.insert("UserOrder", null, contentValues);
//        }
//        Log.d("insert", "insert");
//        db.close();
//        return login_msg;
//    }


    //////////////////      cart    ////////////////////////////
    public String insertPrinter(String p_name, String p_ip) {
        String login_msg = "Save";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("p_name", p_name);
        contentValues.put("p_ip", p_ip);
        Cursor cursor = db.query("UserOrder", null, " TABLE_NO=?",
                new String[]{p_name}, null, null, null);
        if (cursor.getCount() > 0) {

        } else {
            db.insert("Printer", null, contentValues);
        }
        db.close();
        return login_msg;
    }

//    ////////////////////
//    public ArrayList<HashMap<String, String>> get_insertPrinter() {
//        ArrayList<HashMap<String, String>> allUser_name = new ArrayList<HashMap<String, String>>();
//        SQLiteDatabase usersUser_name = this.getWritableDatabase();
//        Cursor cursor = usersUser_name.rawQuery("select * from Printer", null);
//        if (cursor.moveToFirst()) {
//            do {
//                HashMap<String, String> User_name_table = new HashMap<String, String>();
//                User_name_table.put("p_name",
//                        cursor.getString(cursor.getColumnIndex("p_name")));
//                User_name_table.put("p_ip",
//                        cursor.getString(cursor.getColumnIndex("p_ip")));
//
//                allUser_name.add(User_name_table);
//            } while (cursor.moveToNext());
//        }
//        return allUser_name;
//    }

    ////////////////////
    public ArrayList<HashMap<String, String>> get_ORDER() {
        ArrayList<HashMap<String, String>> allUser_name = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase usersUser_name = this.getWritableDatabase();
        Cursor cursor = usersUser_name.rawQuery("select * from UserOrder", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> User_name_table = new HashMap<String, String>();
                User_name_table.put("TABLE_NO",
                        cursor.getString(cursor.getColumnIndex("TABLE_NO")));
                User_name_table.put("ORDER_NO",
                        cursor.getString(cursor.getColumnIndex("ORDER_NO")));
                User_name_table.put("M_ORDER_ID",
                        cursor.getString(cursor.getColumnIndex("M_ORDER_ID")));
                User_name_table.put("ITEM",
                        cursor.getString(cursor.getColumnIndex("ITEM")));
                User_name_table.put("COVER",
                        cursor.getString(cursor.getColumnIndex("COVER")));
                User_name_table.put("TYPE",
                        cursor.getString(cursor.getColumnIndex("TYPE")));
                User_name_table.put("DAP_id",
                        cursor.getString(cursor.getColumnIndex("DAP_id")));
                allUser_name.add(User_name_table);
            } while (cursor.moveToNext());
        }
        return allUser_name;
    }

    //////////////////      cart    ////////////////////////////
    public String insertFood(String name, String price, String id, String qty) {
        String login_msg = "Save";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(PRICE, price);
        contentValues.put(ID, id);
        contentValues.put(ITME, qty);
        Cursor cursor = db.query("UserCart", null, " " + ID + "=?",
                new String[]{id}, null, null, null);
        if (cursor.getCount() > 0) {
            // update_byID(_id, _item);
        } else {
            db.insert(TABLE_NAME, null, contentValues);
        }
        Log.d("insert", "insert");
        db.close();
        return login_msg;
    }

    //////////////////      cart    ////////////////////////////
    public String insert_UserDEMOCart(String _name, String _price, String _id, String _item, String TABLE_NO, String ismodifire) {
        String login_msg = "Save";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_name", _name);
        contentValues.put("_price", _price);
        contentValues.put("_id", _id);
        contentValues.put("_item", _item);
        contentValues.put("TABLE_NO", TABLE_NO);
        contentValues.put("ismodifire", ismodifire);
        Cursor cursor = db.query("UserDemoCart", null, " _id=?",
                new String[]{_id}, null, null, null);
        if (cursor.getCount() > 0) {
            update_DemoID(_id, _item);
        } else {
            db.insert("UserDemoCart", null, contentValues);
        }
        Log.d("insert", "insert");
        db.close();
        return login_msg;
    }

    ////////////////////
    public ArrayList<HashMap<String, String>> get_UserDemoCart(String tableno) {
        ArrayList<HashMap<String, String>> allUser_name = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase usersUser_name = this.getWritableDatabase();
//        Cursor cursor = usersUser_name.rawQuery("select * from UserDemoCart where TABLE_NO="+tableno, null);
        //String query = "select * from UserDemoCart where TABLE_NO="+tableno;
        String query = "SELECT * FROM UserDemoCart WHERE TABLE_NO = ?";
        Cursor cursor = usersUser_name.rawQuery(query, new String[]{tableno});
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> User_name_table = new HashMap<String, String>();
                User_name_table.put("_name",
                        cursor.getString(cursor.getColumnIndex("_name")));
                User_name_table.put("_price",
                        cursor.getString(cursor.getColumnIndex("_price")));
                User_name_table.put("_id",
                        cursor.getString(cursor.getColumnIndex("_id")));
                User_name_table.put("_item",
                        cursor.getString(cursor.getColumnIndex("_item")));
                User_name_table.put("TABLE_NO",
                        cursor.getString(cursor.getColumnIndex("TABLE_NO")));
                User_name_table.put("ismodifire",
                        cursor.getString(cursor.getColumnIndex("ismodifire")));

                allUser_name.add(User_name_table);
            } while (cursor.moveToNext());
        }
        return allUser_name;
    }

    ////////////////////
    public ArrayList<HashMap<String, String>> get_UserCart() {
        ArrayList<HashMap<String, String>> allUser_name = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase usersUser_name = this.getWritableDatabase();
        Cursor cursor = usersUser_name.rawQuery("select * from UserCart", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> User_name_table = new HashMap<String, String>();
                User_name_table.put("_name",
                        cursor.getString(cursor.getColumnIndex("_name")));
                User_name_table.put("_price",
                        cursor.getString(cursor.getColumnIndex("_price")));
                User_name_table.put("_id",
                        cursor.getString(cursor.getColumnIndex("_id")));
                User_name_table.put("_item",
                        cursor.getString(cursor.getColumnIndex("_item")));
                User_name_table.put("ismodifire",
                        cursor.getString(cursor.getColumnIndex("ismodifire")));
                allUser_name.add(User_name_table);
            } while (cursor.moveToNext());
        }
        return allUser_name;
    }

    public int get_item(String _id) {
        int item = 0;
        SQLiteDatabase usersUser_name = this.getWritableDatabase();
        Cursor cursor = usersUser_name.rawQuery("select _item from UserCart where _id=" + _id, null);
        if (cursor.moveToFirst()) {
            do {
                item = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_item")));
            } while (cursor.moveToNext());

        }

        return item;
    }


    public void delete_byID(String id) {
        SQLiteDatabase usersUser_name = this.getWritableDatabase();
        usersUser_name.delete("UserCart", "_id=" + id, null);
    }


    ///////////////////////////////////////////////////
    ////////// update /////////
    public void update_byID(String id, String value) {
        Log.d("update_byID", id + " " + value);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_item", value);
        db.update("UserCart", values, "_id = ?",
                new String[]{id});
    }

    ////////// update /////////
    public void update_DemoID(String id, String value) {
        Log.d("update_byID", id + " " + value);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_item", value);
        db.update("UserDemoCart", values, "_id = ?",
                new String[]{id});
    }

    ////////// update /////////
    public void update_UserOrder(String tablenum, String type, String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TYPE", type);
        values.put("ITEM", item);
        // values.put("_item", value);
        db.update("UserOrder", values, "TABLE_NO = ?",
                new String[]{tablenum});
    }

    //////////////size////////
    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM UserCart";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public void delete_DEMOID(String table) {
        SQLiteDatabase usersUser_name = this.getWritableDatabase();
        usersUser_name.delete("UserDemoCart", "TABLE_NO=" + table, null);
    }


}
