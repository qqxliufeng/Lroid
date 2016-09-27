package com.android.lf.lroid.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.lf.lroid.v.activity.FragmentContainerActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }



}
