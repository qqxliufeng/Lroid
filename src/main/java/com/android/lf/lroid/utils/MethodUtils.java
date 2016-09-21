package com.android.lf.lroid.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.lf.lroid.v.activity.FragmentContainerActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by feng on 2016/9/13.
 */

public class MethodUtils {

    public static void startFragmentsActivity(Context context, String title, int id, Bundle extra){
        Intent intent = new Intent(context, FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.FRAGMENT_FLAG,id);
        intent.putExtra(FragmentContainerActivity.TITLE_FLAG,title);
        intent.putExtra(FragmentContainerActivity.EXTRA_FLAG,extra);
        context.startActivity(intent);
    }

    public static void startFragmentsActivity(Context context, String title, int id){
        Intent intent = new Intent(context, FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.FRAGMENT_FLAG,id);
        intent.putExtra(FragmentContainerActivity.TITLE_FLAG,title);
        context.startActivity(intent);
    }

    public static String getCurrentTime(String style){
        if (TextUtils.isEmpty(style)){
            style = "yyyy-MM-dd";
        }
        long times =  System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat(style);
        return dateFormat.format(new Date(times));
    }

    public static int getCurrentCode(Context context){
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isCurrentVersion(Context context){
        int oldCode = PreferenceUtils.getPrefInt(context,"currentCode",1);
        return getCurrentCode(context) > oldCode;
    }


}
