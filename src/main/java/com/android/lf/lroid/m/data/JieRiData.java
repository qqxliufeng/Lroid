package com.android.lf.lroid.m.data;

import android.util.Log;

import com.android.lf.lroid.m.bean.JieRiBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by feng on 2016/9/14.
 */

public class JieRiData {

//    public static String[] jieRiTimes = new String[]{
//            "正月十五",
//            "二月初二",
//            "五月初五",
//            "七月初七",
//            "八月十五",
//            "九月初九",
//            "十月初一",
//            "十二月初八",
//            "十二月二十三",
//            "十二月三十"
//    };

    public static String[] jieRiTitles = new String[]{
            "农历",
            "一月",
            "二月",
            "三月",
            "四月",
            "五月",
            "六月",
            "七月",
            "八月",
            "九月",
            "十月",
            "十一月",
            "十二月",
    };

    private static JieRiData instance = null;

    private ArrayList<JieRiBean> mArrayList = new ArrayList<JieRiBean>() {
        {
            for (int i = 0; i < jieRiMap.size(); i++) {
                Set<Map.Entry<String, String[][]>> entries = jieRiMap.entrySet();
                for (Map.Entry<String, String[][]> entry : entries) {
                    for (int j = 0; j < entry.getValue().length; j++) {
                        JieRiBean jieRiBean = new JieRiBean();
                        jieRiBean.setType(entry.getKey());
                        jieRiBean.setName(entry.getValue()[j][0]);
                        jieRiBean.setTime(entry.getValue()[j][1]);
                        add(jieRiBean);
                    }
                }
            }
        }
    };

    private JieRiData() {
    }

    public static JieRiData getInstance() {
        if (instance == null) {
            synchronized (JieRiData.class) {
                if (instance == null) {
                    instance = new JieRiData();
                }
            }
        }
        return instance;
    }

    public static HashMap<String, String[][]> jieRiMap = new HashMap<String, String[][]>() {
        {
            put("农历", new String[][]{
                    {"元宵节", "正月十五",},
                    {"龙抬头", "二月初二"},
                    {"端午节", "五月初五"},
                    {"七夕节", "七月初七"},
                    {"中秋节", "八月十五"},
                    {"重阳节", "九月初九"},
                    {"祭祖节", "十月初一"},
                    {"腊八节", "十二月初八"},
                    {"过小年", "十二月二十三"},
                    {"除夕", "十二月三十"},
            });
            put("一月", new String[][]{
                    {}, {}, {},
            });

        }
    };

    public ArrayList<JieRiBean> getArrayList() {
        return mArrayList;
    }
}
