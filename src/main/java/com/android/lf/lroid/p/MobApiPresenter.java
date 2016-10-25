package com.android.lf.lroid.p;

import com.android.lf.lroid.m.bean.FoodInfoBean;
import com.android.lf.lroid.m.bean.FoodMenuBean;
import com.android.lf.lroid.m.bean.FoodRecipeBean;
import com.android.lf.lroid.m.bean.FoodStepBean;
import com.android.lf.lroid.m.bean.HealthBean;
import com.google.gson.Gson;
import com.mob.mobapi.API;
import com.mob.mobapi.APICallback;
import com.mob.mobapi.MobAPI;
import com.mob.mobapi.apis.Calendar;
import com.mob.mobapi.apis.Cook;
import com.mob.mobapi.apis.Health;
import com.mob.mobapi.apis.History;
import com.mob.mobapi.apis.Weather;
import com.mob.mobapi.apis.WxArticle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by feng on 2016/9/21.
 */

public class MobApiPresenter extends BasePresenter implements APICallback {

    public static final int PARSE_FOOD_MENU_FLAG = 100;
    public static final int PARSE_FOOD_LIST_FLAG = 101;

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

    @Override
    protected <T, R> R doSomething(int requestID, T[] ts) {
        switch (requestID){
            case PARSE_FOOD_MENU_FLAG:
                HashMap<String,Object> resultMap_MENU = (HashMap<String, Object>) ((HashMap<String,Object>)ts[0]).get("result");
                ArrayList<HashMap<String,Object>> childs = (ArrayList<HashMap<String, Object>>) resultMap_MENU.get("childs");
                if ((childs!=null && !childs.isEmpty())) {
                    ArrayList<FoodMenuBean> tempList = new ArrayList<>();
                    for (HashMap<String, Object> map : childs) {
                        HashMap<String, Object> childMap = (HashMap<String, Object>) map.get("categoryInfo");
                        FoodMenuBean foodMenuBean = new FoodMenuBean();
                        foodMenuBean.setParentId((String) childMap.get("parentId"));
                        foodMenuBean.setCtgId((String) childMap.get("ctgId"));
                        foodMenuBean.setName((String) childMap.get("name"));
                        ArrayList<FoodMenuBean> childMenuTempList = new ArrayList<>();
                        ArrayList<HashMap<String, Object>> childMenuList = (ArrayList<HashMap<String, Object>>) map.get("childs");
                        if (childMenuList != null && !childMenuList.isEmpty()) {
                            for (HashMap<String, Object> childMenuMap : childMenuList) {
                                HashMap<String, Object> categoryInfo = (HashMap<String, Object>) childMenuMap.get("categoryInfo");
                                FoodMenuBean childFoodMenuBean = new FoodMenuBean();
                                childFoodMenuBean.setName((String) categoryInfo.get("name"));
                                childFoodMenuBean.setCtgId((String) categoryInfo.get("ctgId"));
                                childFoodMenuBean.setParentId((String) categoryInfo.get("parentId"));
                                childMenuTempList.add(childFoodMenuBean);
                            }
                        }
                        foodMenuBean.setChilds(childMenuTempList);
                        tempList.add(foodMenuBean);
                    }
                    return (R) tempList;
                }
                break;
            case PARSE_FOOD_LIST_FLAG:
                try {
                    HashMap<String, Object> resultMap_LIST = (HashMap<String, Object>) ((HashMap<String, Object>) ts[0]).get("result");
                    if (resultMap_LIST != null && !resultMap_LIST.isEmpty()) {
                        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) resultMap_LIST.get("list");
                        ArrayList<FoodInfoBean> tempList = new ArrayList<>();
                        if (list != null && !list.isEmpty()) {
                            for (HashMap<String, Object> listMap : list) {
                                FoodInfoBean foodInfoBean = new FoodInfoBean();

                                foodInfoBean.setCtgTitles((String) listMap.get("ctgTitles"));
                                foodInfoBean.setMenuId((String) listMap.get("menuId"));
                                foodInfoBean.setThumbnail((String) listMap.get("thumbnail"));
                                foodInfoBean.setName((String) listMap.get("name"));
                                ArrayList<String> ctgIdsList = (ArrayList<String>) listMap.get("ctgIds");
                                foodInfoBean.setCtgIds(ctgIdsList);

                                HashMap<String, Object> recipeMap = (HashMap<String, Object>) listMap.get("recipe");
                                FoodRecipeBean recipeBean = new FoodRecipeBean();
                                recipeBean.setImg((String) recipeMap.get("img"));
                                recipeBean.setSumary((String) recipeMap.get("sumary"));
                                recipeBean.setTitle((String) recipeMap.get("title"));

                                String methodList = (String) recipeMap.get("method");
                                if (methodList != null) {
                                    JSONArray jsonArray = new JSONArray(methodList);
                                    ArrayList<FoodStepBean> foodStepList = new ArrayList<>();
                                    if (jsonArray.length() > 0) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject object = jsonArray.optJSONObject(i);
                                            FoodStepBean foodStep = new Gson().fromJson(object.toString(), FoodStepBean.class);
                                            foodStepList.add(foodStep);
                                        }
                                        recipeBean.setMethod(foodStepList);
                                    }
                                }
                                String ingredients = (String) recipeMap.get("ingredients");
                                recipeBean.setIngredients(ingredients);

                                foodInfoBean.setRecipe(recipeBean);
                                tempList.add(foodInfoBean);
                            }
                            return (R) tempList;
                        }
                    }
                    return null;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
        }
        return null;
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
