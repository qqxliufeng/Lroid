package com.android.lf.lroid.p;

import com.android.lf.lroid.m.bean.HealthBean;
import com.mob.mobapi.API;
import com.mob.mobapi.APICallback;
import com.mob.mobapi.MobAPI;
import com.mob.mobapi.apis.Calendar;
import com.mob.mobapi.apis.Cook;
import com.mob.mobapi.apis.Health;
import com.mob.mobapi.apis.History;
import com.mob.mobapi.apis.Weather;
import com.mob.mobapi.apis.WxArticle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by feng on 2016/9/21.
 */

public class MobApiPresenter extends BasePresenter implements APICallback {

    public static final int REQUEST_CODE_WEATHER = 0x0;
    public static final int REQUEST_CODE_CALENDAR = 0x1;
    public static final int REQUEST_CODE_HISTORY_TODAY = 0x2;
    public static final int REQUEST_CODE_WEIXIN = 0x3;
    public static final int REQUEST_CODE_HEALTH = 0x4;
    public static final int REQUEST_CODE_FOOD_FOR_MENU = 0x5;
    public static final int REQUEST_CODE_FOOD_FOR_INFO = 0x6;


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
                case REQUEST_CODE_WEIXIN:
                    WxArticle wxArticle = (WxArticle) MobAPI.getAPI(WxArticle.NAME);
                    wxArticle.searchArticleList(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]),this);
                    break;
                case REQUEST_CODE_HEALTH:
                    Health health = (Health) MobAPI.getAPI(Health.NAME);
                    health.queryHealth(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]),this);
                    break;
                case REQUEST_CODE_FOOD_FOR_MENU:
                    Cook cook = (Cook) MobAPI.getAPI(Cook.NAME);
                    cook.queryCategory(this);
                    break;
                case REQUEST_CODE_FOOD_FOR_INFO:
                    Cook cookInfo = (Cook) MobAPI.getAPI(Cook.NAME);
                    cookInfo.searchMenu(args[0],args[1],Integer.parseInt(args[2]),Integer.parseInt(args[3]),this);
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

    public <T> ArrayList<T> parseResult(Map<String, Object> result){
        HashMap<String, Object> res = (HashMap<String, Object>) result.get("result");
        if (null != res && res.size() > 0) {
            ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) res.get("list");
            ArrayList<HealthBean> tempList = new ArrayList<>();
            for (HashMap<String, Object> map : list) {
                HealthBean healthBean = new HealthBean();
                healthBean.setArticleId((String) map.get("id"));
                healthBean.setSourceUrl((String) map.get("sourceUrl"));
                healthBean.setArticleTitle((String) map.get("title"));
                healthBean.setTime((String) map.get("pubTime"));
                tempList.add(healthBean);
            }
            return (ArrayList<T>) tempList;
        }
        return null;
    }
}
