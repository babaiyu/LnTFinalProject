package com.lntfinal.data;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserData {
    public String id_bimbel;
    public String name;
    public String email;

    public UserData() {
        // Default constructor
    }

    public UserData(String id_bimbel, String name, String email) {
        this.id_bimbel = id_bimbel;
        this.name = name;
        this.email = email;
    }
}
