package com.android.lf.lroid.v.views;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.android.lf.lroid.R;

/**
 * Created by feng on 2016/10/31.
 */

public class CustomDialogHelper {

    public static void createEmptyDialog(Context context){
        Dialog emptyDialog = new Dialog(context, R.style.emptyDialog);
        View emptyView = View.inflate(context,R.layout.dialog_empty_layout,null);
        emptyDialog.setContentView(emptyView);
        emptyDialog.show();
    }
}
