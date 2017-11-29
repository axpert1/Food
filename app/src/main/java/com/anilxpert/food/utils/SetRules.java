package com.anilxpert.food.utils;

/**
 * Created by AnilXpert 9887230800 on 11/29/2017.
 */

public class SetRules {
    public static String serRule(String editText, String type) {
        String validRespone = "";
        switch (type) {
            case "email":
                if (editText.length() > 0) {
                    if (!ValidField.isValidEmail(editText))
                        validRespone = "* Enter valid email \n";
                } else {
                    validRespone = "* Enter email\n";
                }


                break;
            case "mobile":
                if (editText.length() > 0) {
                    if (!ValidField.isValidPhoneNumber(editText))
                        validRespone = "* Enter valid mobile number\n";
                } else {
                    validRespone = "* Enter mobile number\n";
                }


                break;
            case "password":
                if (editText.length() > 0) {
                    if (!ValidField.isValidPass(editText)) {
                        validRespone = "* Password must be at least 6 characters\n";
                    }

                } else {
                    validRespone = "* Enter password\n";
                }


                break;
            case "first_name":
                if (editText.length() <= 0) {
                    validRespone = "* Enter first name\n";
                }


                break;
            case "name":
                if (editText.length() <= 0) {
                    validRespone = "* Enter name\n";
                }


                break;
            case "message":
                if (editText.length() <= 0) {
                    validRespone = "* Enter message\n";
                }


                break;

        }
        return validRespone;
    }
}
