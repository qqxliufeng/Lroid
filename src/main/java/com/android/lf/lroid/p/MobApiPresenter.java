package com.android.lf.lroid.p;

import com.mob.mobapi.API;
import com.mob.mobapi.APICallback;
import com.mob.mobapi.MobAPI;
import com.mob.mobapi.apis.Calendar;
import com.mob.mobapi.apis.Weather;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by feng on 2016/9/21.
 */

public class MobApiPresenter extends BasePresenter implements APICallback {


    public void getData(API api, String... args) {
        if (iPresentListener != null) {
            iPresentListener.onRequestStart(0x1);
            if (api instanceof Weather) {
                ((Weather) api).queryByCityName("济南", this);
            }else if (api instanceof Calendar){
                ((Calendar)api).queryCalendar(args[0],this);
            }
        }
    }


    @Override
    public void onSuccess(API api, int i, Map<String, Object> map) {
        iPresentListener.onRequestEnd(0x1);
        iPresentListener.onRequestSuccess(0x1, map);
    }

    @Override
    public void onError(API api, int i, Throwable throwable) {
        iPresentListener.onRequestEnd(0x1);
        iPresentListener.onRequestFail(0x1, throwable);
    }
}
