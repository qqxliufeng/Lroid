package com.android.lf.lroid.v.views;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.lf.lroid.R;

/**
 * Created by feng on 2016/10/31.
 */

public class CustomDialogHelper {

    public static void createEmptyDialog(Context context){
        final Dialog emptyDialog = new Dialog(context, R.style.emptyDialog);
        View emptyView = View.inflate(context,R.layout.dialog_empty_layout,null);
        ImageView iv_close = (ImageView) emptyView.findViewById(R.id.id_iv_dialog_empty_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyDialog.dismiss();
            }
        });
        emptyDialog.setContentView(emptyView);
        Window window = emptyDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window .setGravity(Gravity.CENTER | Gravity.TOP);
        lp.y = 200;
        window.setAttributes(lp);
        emptyDialog.show();
    }
}
