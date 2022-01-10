package com.devdroid.easybiz.authentication;

import android.app.Activity;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;

import com.devdroid.easybiz.R;

import androidx.appcompat.app.AlertDialog;

public class RegistrationDialog {

    private final Activity activity;
    private AlertDialog alertDialog;

    public RegistrationDialog(Activity activity) {
        this.activity = activity;
    }

    public void buildDialog()
    {
        //View view = LayoutInflater.from(this.activity).inflate(R.layout.registration_layout,null);
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
        alertDialog=builder.create();
        alertDialog.setContentView(R.layout.registration_layout);
        alertDialog.setCancelable(true);
    }


    public void dismissDialog()
    {
        alertDialog.dismiss();
    }

    public void showDialog()
    {
        alertDialog.show();
    }
}
