package com.in.sbnri.typeConverterForRoom;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.in.sbnri.entities.License;
import com.in.sbnri.entities.Permissions;

import java.io.Serializable;
import java.lang.reflect.Type;


public class ObjectToStringTypeConverter implements Serializable {

    @TypeConverter
    public String licenseToString(License license) {
        if (license == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<License>() {
        }.getType();
        String json = gson.toJson(license, type);
        return json;
    }

    @TypeConverter
    public License stringToLicense(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<License>() {
        }.getType();
        License productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }

    @TypeConverter
    public String permissionsToString(Permissions license) {
        if (license == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Permissions>() {
        }.getType();
        String json = gson.toJson(license, type);
        return json;
    }

    @TypeConverter
    public Permissions stringToPermissions(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Permissions>() {
        }.getType();
        Permissions productCategoriesList = gson.fromJson(optionValuesString, type);
        return productCategoriesList;
    }
}