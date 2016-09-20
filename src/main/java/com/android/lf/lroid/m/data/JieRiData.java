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
                    {"元旦", "1月1日"}
            });
            put("二月", new String[][]{
                    {"情人节", "2月14日"}
            });
            put("三月", new String[][]{
                    {"中国植树节", "3月12日"}
            });
            put("四月", new String[][]{
                    {"国际愚人节", "4月1日"},
                    {"中国清明节", "4月5日"},
            });
            put("五月", new String[][]{
                    {"国际劳动节", "5月1日"},
                    {"中国青年节,五四运动纪念日", "5月4日"},
                    {"母亲节", "5月第二个星期日"},
            });
            put("六月", new String[][]{
                    {"国际儿童节", "6月1日"},
                    {"父亲节", "6月第三个星期日"},
            });
            put("七月", new String[][]{
                    {"中国共产党诞生日", "7月1日"},
            });
            put("八月", new String[][]{
                    {"中国人民解放军建军节", "8月1日"},
            });
            put("九月", new String[][]{
                    {"中国抗日战争胜利纪念日", "9月3日"},
                    {"中国教师节", "9月10日"},
                    {"九·一八事变纪念日(中国国耻日)", "9月18日"},
            });
            put("十月", new String[][]{
                    {"国庆节", "10月1日"},
            });
            put("十一月", new String[][]{
                    {"感恩节", "11月第四个星期四"},
            });
        }
    };

    public ArrayList<JieRiBean> getArrayList() {
        return mArrayList;
    }
}
