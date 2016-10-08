package com.android.lf.lroid.p;

import com.mob.mobapi.API;
import com.mob.mobapi.APICallback;
import com.mob.mobapi.MobAPI;
import com.mob.mobapi.apis.Calendar;
import com.mob.mobapi.apis.History;
import com.mob.mobapi.apis.Weather;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by feng on 2016/9/21.
 */

public class MobApiPresenter extends BasePresenter implements APICallback {

    public static final int REQUEST_CODE_WEATHER = 0x0;
    public static final int REQUEST_CODE_CALENDAR = 0x1;
    public static final int REQUEST_CODE_HISTORY_TODAY = 0x2;


    public void getData(int requestApiCode,String... args) {
        if (getPresentListener()!=null) {
            getPresentListener().onRequestStart(0x1);
            switch (requestApiCode) {
                case REQUEST_CODE_WEATHER:
                    Weather weather = (Weather) MobAPI.getAPI(Weather.NAME);
                    weather.queryByCityName(args[0], this);
                    break;
                case REQUEST_CODE_CALENDAR:
                    Calendar calendar = (Calendar) MobAPI.getAPI(Calendar.NAME);
                    calendar.queryCalendar(args[0],this);
                    break;
                case REQUEST_CODE_HISTORY_TODAY:
                    History history = (History) MobAPI.getAPI(History.NAME);
                    history.queryHistory(args[0],this);
                    break;
            }
        }
    }

    @Override
    public void onSuccess(API api, int i, Map<String, Object> map) {
        getPresentListener().onRequestEnd(0x1);
        getPresentListener().onRequestSuccess(0x1, map);
    }

    @Override
    public void onError(API api, int i, Throwable throwable) {
        getPresentListener().onRequestEnd(0x1);
        getPresentListener().onRequestFail(0x1, throwable);
    }
}
