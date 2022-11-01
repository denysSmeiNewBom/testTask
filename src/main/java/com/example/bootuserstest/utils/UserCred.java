package com.example.bootuserstest.utils;

import com.example.bootuserstest.exception.NoSuchFieldInTableException;

public enum UserCred {
    PHONE("phoneNumber", "'phone_number'"),
    EMAIL("email", "'email'");

    private String valueInJson;
    private String valueInDB;

    UserCred(String valueInJson, String valueInDB) {
        this.valueInJson = valueInJson;
        this.valueInDB = valueInDB;
    }

    public String getValueInJson() {
        return valueInJson;
    }

    public String getValueInDB() {
        return valueInDB;
    }

    public static UserCred getUserByField(String valueInDB){
        for (UserCred c : UserCred.values()) {
            if(c.getValueInDB().equals(valueInDB)){
                return c;
            }
        }
        throw new NoSuchFieldInTableException("There is no field "+ valueInDB +" in table");
    }
}