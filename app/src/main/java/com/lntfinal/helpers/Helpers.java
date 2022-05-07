package com.lntfinal.helpers;

import android.content.Context;
import android.widget.Toast;

public class Helpers {
    public Boolean checkIdBimbel(String id_bimbel) {
        return id_bimbel.length() >= 1;
    }

    public Boolean checkName(String name) {
        return name.length() >= 5;
    }

    public Boolean checkEmail(String email) {
        return email.contains("@") && email.contains(".com");
    }

    public Boolean checkPassword(String password) {
        return password.length() >= 1;
    }

    public Boolean checkConfirmPass(String password, String confirmPassword) {
        return confirmPassword.length() >= 1 && confirmPassword.equals(password);
    }

    public void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
