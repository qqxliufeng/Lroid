package com.android.lf.lroid.m.data;

import com.android.lf.lroid.m.bean.JieQiBean;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by feng on 2016/9/13.
 */

public class JieQiData {

    private JieQiData() {
    }

    ;
    private static JieQiData instance = null;

    public static JieQiData getInstance() {
        if (instance == null) {
            synchronized (JieQiData.class) {
                if (instance == null) {
                    instance = new JieQiData();
                }
            }
        }
        return instance;
    }

    private String[] jieQiNames = new String[]{
            "立春", "雨水", "惊蛰", "春分", "清明", "谷雨",

            "立夏", "小满", "芒种", "夏至", "小暑", "大暑",

            "立秋", "处暑", "白露", "秋分", "寒露", "霜降",

            "立冬", "小雪", "大雪", "冬至", "小寒", "大寒"
    };

    private String[] jieQiTime = new String[]{
            "2月3-5日", "2月18-20日", "3月5-7日", "3月20-22日", "4月4-6日", "4月19-21日",

            "5月5-7日", "5月20-22日", "6月5-7日", "6月21-22日", "7月6-8日", "7月22日-24日",

            "8月7-9日", "8月22-24日", "9月7-9日", "9月22-24日", "10月8-9日", "10月23-24日",

            "11月7-8日", "11月22-23日", "12月6-8日", "12月21-23日", "1月5-7日", "1月20-21日"
    };

    private String[] jieQiDetailInfoUrl = new String[]{
            "http://baike.baidu.com/subview/25702/5798126.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/10790/10790.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/21801/5837879.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/10644/6334345.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/294/8201435.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/11235/5615203.htm?bkfr=detailtbl",

            "http://baike.baidu.com/subview/25743/5723127.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25760/8379312.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25774/25774.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25782/6029524.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25793/25793.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/28060/5870595.htm?bkfr=detailtbl",

            "http://baike.baidu.com/subview/25806/5840588.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25819/25819.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25828/5740752.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/11114/5833491.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25838/25838.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25848/5833534.htm?bkfr=detailtbl",

            "http://baike.baidu.com/subview/25856/25856.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25867/7072847.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25884/6061367.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/16674/5833579.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25902/5797898.htm?bkfr=detailtbl",
            "http://baike.baidu.com/subview/25921/25921.htm?bkfr=detailtbl",
    };

    private String[] jieQiPics = new String[]{
            "http://hiphotos.baidu.com/zhixin/abpic/item/aa251d4f78f0f736cb3d0a480855b319eac4137c.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/310f3b1f95cad1c8104c2b527d3e6709c83d5195.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/dc15484e9258d1090b3b51c4d358ccbf6d814da9.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/0d729944ebf81a4c6cab36bad52a6059242da6ed.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/425773224f4a20a4b3212cb492529822720ed043.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/b87985504fc2d562a048a495e51190ef77c66cc2.jpg",

            "http://hiphotos.baidu.com/zhixin/abpic/item/3853ad1bb051f819cb6ba801d8b44aed2f73e7f4.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/eab9044c510fd9f97ecee51d272dd42a2934a4ae.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/27d647ee3d6d55fb0a991e556f224f4a20a4dd23.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/d8b8c92a6059252dd51f18b8369b033b5ab5b9c6.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/8cf0d51349540923e89b1a0b9058d109b2de4991.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/72ccb7773912b31bacb2356f8418367adbb4e15f.jpg",

            "http://hiphotos.baidu.com/zhixin/abpic/item/7e7f7909c93d70cf3fda047bfadcd100baa12b60.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/4075890a304e251f9149428ea586c9177e3e53ce.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/86d5bac27d1ed21b2ed688aeaf6eddc451da3f1a.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/90cebeec08fa513dbc5a2e923f6d55fbb3fbd94f.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/cbc17b380cd79123dd8ea102af345982b2b7801e.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/7787b9efce1b9d1659996516f1deb48f8c546428.jpg",

            "http://hiphotos.baidu.com/zhixin/abpic/item/b110e6198618367a083251aa2c738bd4b21ce5cb.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/4a77b2af2edda3ccc294175403e93901213f9217.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/b74124f33a87e95051101cd412385343faf2b4c0.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/b6045da98226cffca876a00ebb014a90f703eaeb.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/d048adde9c82d158b2a866cb820a19d8bc3e4266.jpg",
            "http://hiphotos.baidu.com/zhixin/abpic/item/7787b9efce1b9d1617412b12f1deb48f8d546484.jpg",
    };

    private String[] jieQiContents = new String[]{

            "立春，农历二十四节气中的第一个节气。立春是从天文上来划分的，即太阳到达黄经315°时。立春是汉族民间重要的传统节日之一。“立”是“开始”的意思，自秦代以来，中国就一直以立春作为孟春时节的开始。所谓“一年之计在于春”，春是温暖，鸟语花香；春是生长，耕耘播种。立春之日迎春已有三千多年历史，中国自官方到民间都极为重视。立春时，天子亲率三公九卿、诸侯大夫去东郊迎春，祈求丰收。回来之后，要赏赐群臣，布德令以施惠兆民。这种活动影响到庶民，使之成为后来世世代代的全民的迎春活动 。",
            "雨水，是二十四节气之中的第2个节气，位于每年正月十五前后（公历2月18-20日），太阳到达黄经330°。\n" +
                    "雨水和谷雨、小雪、大雪一样，都是反映降水现象的节气。[1]  这天通常出嫁的女儿要回家探望父母，要给母亲送一段红绸和炖一罐肉。",
            "惊蛰，古称“启蛰”，是农历二十四节气中的第三个节气，标志着仲春时节的开始；太阳到达黄经345°时。《月令七十二候集解》：“二月节……万物出乎震，震为雷，故曰惊蛰，是蛰虫惊而出走矣。”\n" +
                    "此前，动物入冬藏伏土中，不饮不食，称为“蛰”；到了“惊蛰节”，天上的春雷惊醒蛰居的动物，称为“惊”。故惊蛰时，蛰虫惊醒，天气转暖，渐有春雷，中国大部分地区进入春耕季节。",
            "春分，是春季九十天的中分点。二十四节气之一，每年公历大约为3月20日左右，太阳位于黄经0°（春分点）时。春分这一天太阳直射地球赤道，南北半球季节相反，北半球是春分，在南半球来说就是秋分。春分是伊朗、土耳其、阿富汗、乌兹别克斯坦等国的新年，有着3000年的历史。\n" +
                    "《月令七十二候集解》：“二月中，分者半也，此当九十日之半，故谓之分。秋同义。”《春秋繁露·阴阳出入上下篇》说：“春分者，阴阳相半也，故昼夜均而寒暑平。”\n" +
                    "春分也是节日和祭祀庆典，古代帝王有春天祭日，秋天祭月的礼制。周礼天子日坛祭日。\n" +
                    "《礼记》：“祭日于坛。”孔颖达疏：“谓春分也”。 清潘荣陛《帝京岁时纪胜》：“春分祭日，秋分祭月，乃国之大典，士民不得擅祀。\n" +
                    "民间活动上，一般算做踏青的正式开始。活动有：（一）放风筝，妇女小孩放风筝。并在风筝上写祝福 希望天上神看到。（二）簪花喝酒：无论男女老少都簪花。（三）野外挑野菜：朱淑真《春日杂书十首》：写字弹琴无意绪 踏青挑菜没心情。饮食方面，则有春菜，春汤，春酒等",
            "清明节又叫踏青节，在仲春与暮春之交，也就是冬至后的第108天。是中国传统节日，也是最重要的祭祀节日之一，是祭祖和扫墓的日子[1]  。中华民族传统的清明节大约始于周代，距今已有二千五百多年的历史。\n" +
                    "清明最早只是一种节气的名称，其变成纪念祖先的节日与寒食节有关。晋文公把寒食节的后一天定为清明节。在山西大部分地区是在清明节前一天过寒食节；榆社县等地是在清明节前两天过寒食节；垣曲县还讲究清明节前一天为寒食节，前二天为小寒食。\n" +
                    "清明节是中国重要的“时年八节”之一，一般是在公历4月5号前后，节期很长，有10日前8日后及10日前10日后两种说法，这近20天内均属清明节。清明节原是指春分后十五天，1935年中华民国政府明定4月5日为国定假日清明节，也叫做民族扫墓节  。",
            "谷雨是二十四节气的第六个节气，也是春季最后一个节气，每年4月19日～21日时太阳到达黄经30°时为谷雨，源自古人“雨生百谷”之说。同时也是播种移苗、埯瓜点豆的最佳时节。[1] \n" +
                    "“清明断雪，谷雨断霜”，气象专家表示，谷雨是春季最后一个节气，谷雨节气的到来意味着寒潮天气基本结束，气温回升加快，大大有利于谷类农作物的生长。",

            "立夏是农历二十四节气中的第7个节气，夏季的第一个节气，表示孟夏时节的正式开始，太阳到达黄经45度时为立夏节气。斗指东南，维为立夏，万物至此皆长大，故名立夏也。\n" +
                    "《月令七十二候集解》：“立夏，四月节。立字解见春。夏，假也。物至此时皆假大也。 ”\n" +
                    "在天文学上，立夏表示即将告别春天，是夏天的开始。人们习惯上都把立夏当作是温度明显升高，炎暑将临，雷雨增多，农作物进入旺季生长的一个重要节气。",
            "小满是二十四节气之一，夏季的第二个节气。小满——其含义是夏熟作物的籽粒开始灌浆饱满，但还未成熟，只是小满，还未大满。每年5月20日到22日之间视太阳到达黄经60°时为小满。",
            "芒种是农历二十四节气中的第9个节气，夏季的第三个节气，表示仲夏时节的正式开始；太阳到达黄经75°时交芒种节气。芒种字面的意思是“有芒的麦子快收，有芒的稻子可种”。《月令七十二侯集解》：“五月节，谓有芒之种谷可稼种矣。”此时中国长江中下游地区将进入多雨的黄梅时节。",
            "夏至是二十四节气之一，在每年公历6月21日或22日。夏至这天，太阳运行至黄经90度（夏至点，目前处在双子座），太阳直射地面的位置到达一年的最北端，几乎直射北回归线，此时，北半球各地的白昼时间达到全年最长。对于北回归线及其以北的地区来说，夏至日也是一年中正午太阳高度最高的一天。在北京地区，夏至日白昼可长达15小时，正午太阳高度高达73°32′。这一天北半球得到的太阳辐射最多，比南半球多了将近一倍。\n" +
                    "天文专家称，夏至是太阳的转折点，这天过后它将走“回头路”，阳光直射点开始从北回归线向南移动，北半球白昼将会逐日减短。夏至日过后，北回归线及其以北的地区，正午太阳高度角也会逐日降低。同时，夏至到来后，夜空星象也逐渐变成夏季星空。",
            "小暑是农历二十四节气之第十一个节气，夏天的第五个节气，表示季夏时节的正式开始；太阳到达黄经105度时叫小暑节气。暑，表示炎热的意思，小暑为小热，还不十分热。意指天气开始炎热，但还没到最热，全国大部分地区基本符合。全国的农作物都进入了茁壮成长阶段，需加强田间管理。",
            "大暑是农历二十四节气之一，太阳位于黄经120°。大暑期间，中国民间有饮伏茶，晒伏姜，烧伏香等习俗。\n" +
                    "《月令七十二候集解》中说：“大暑，六月中。暑，热也，就热之中分为大小，月初为小，月中为大，今则热气犹大也。”其气候特征是：“斗指丙为大暑，斯时天气甚烈于小暑，故名曰大暑。”大暑节气正值“三伏天”里的“中伏”前后，是一年中最热的时期，气温最高，农作物生长最快，同时，很多地区的旱、涝、风灾等各种气象灾害也最为频繁。",

            "立秋，是农历二十四节气中的第13个节气，更是秋天的第一个节气，标志着孟秋时节的正式开始：“秋”就是指暑去凉来。到了立秋，梧桐树开始落叶，因此有“落叶知秋”的成语。从文字角度来看，“秋”字由禾与火字组成，是禾谷成熟的意思。秋季是天气由热转凉，再由凉转寒的过渡性季节。",
            "处暑，即为“出暑”，是炎热离开的意思。是农历二十四节气之中的第14个节气，交节时间点在公历8月23日前后，太阳到达黄经150°。\n" +
                    "处暑节气意味着进入气象意义的秋天，处暑后中国长江以北地区气温逐渐下降。\n" +
                    "此时太阳正运行到了狮子座的轩辕十四星近旁。夜晚观北斗七星，弯弯的斗柄还是指向“申”(西南方向)。",
            "白露是农历二十四节气中的第十五个节气，当太阳到达黄经165度时为白露。\n" +
                    "《月令七十二候集解》中说：“八月节……阴气渐重，露凝而白也。” 天气渐转凉，会在清晨时分发现地面和叶子上有许多露珠，这是因夜晚水汽凝结在上面，故名。古人以四时配五行，秋属金，金色白，故以白形容秋露。进入“白露”，晚上会感到一丝丝的凉意。",
            "秋分（autumnal equinox），农历二十四节气中的第十六个节气，时间一般为每年的9月22~24日。南方的气候由这一节气起才始入秋。一是太阳在这一天到达黄经180度，直射地球赤道，因此地球绝大部分地区这一天24小时昼夜均分，各12小时。在北极点（北纬90°）与南极点（南纬90°）附近，这一天可以观测到“太阳整日在地平线上转圈”的特殊现象。\n",
            "寒露是农历二十四节气中的第十七个节气，属于秋季的第五个节气，表示秋季时节的正式开始；时间在公历每年10月7日~9日。视太阳到达黄经195°时。《月令七十二候集解》说：“九月节，露气寒冷，将凝结也。”寒露的意思是气温比白露时更低，地面的露水更冷，快要凝结成霜了。寒露时节，南岭及以北的广大地区均已进入秋季，东北进入深秋，西北地区已进入或即将进入冬季。",
            "霜降，二十四节气之一，每年公历10月23日左右，霜降节气含有天气渐冷、初霜出现的意思，是秋季的最后一个节气，也意味着冬天即将开始。霜降时节，养生保健尤为重要，民间有谚语“一年补透透，不如补霜降”，足见这个节气对人们的影响。",

            "立冬是农历二十四节气之一，也是中国传统节日之一；时间点在公历每年11月7-8日之间，即太阳位于黄经225°。此时，地球位于赤纬-16°19'，北京地区正午太阳高度仅有33°47'。立冬过后，日照时间将继续缩短，正午太阳高度继续降低。立冬期间，汉族民间以立冬为冬季之始，需进补以度严冬的食俗。",
            "小雪，是二十四节气中的第20个。11月22或23日，太阳到达黄经240°，此时称为小雪节气。此时，太阳位于赤纬-20°16'，这天北京地区白昼时间仅9小时49分钟，正午太阳高度仅29°50‘。",
            "“大雪”是农历二十四节气中的第21个节气，更是冬季的第三个节气，标志着仲冬时节的正式开始；其时视太阳到达黄经255度。《月令七十二候集解》说：“大雪，十一月节，至此而雪盛也。”大雪的意思是天气更冷，降雪的可能性比小雪时更大了，并不指降雪量一定很大。",
            "冬至（Winter Solstice）又名‘一阳生’，是中国农历中一个重要的节气[1]  ，也是中华民族的一个传统节日，冬至俗称“冬节”、“长至节”、“亚岁”等。早在二千五百多年前的春秋时代，中国就已经用土圭观测太阳，测定出了冬至，它是二十四节气中最早制订出的一个，时间在每年的公历12月21日~23日之间。",
            "小寒，为农历二十四节气中的第23个节气，也是冬季的第五个节气，标志着季冬时节的正式开始。当太阳到达黄经285°(小寒)时，对于神州大地而言，标志着一年中最寒冷的日子到来了。",
            "大寒，是全年二十四节气中的最后一个节气。每年公历1月20日前后，太阳到达黄经300°时，即为大寒。"
    };

    private int[] types = new int[]{
            0, 1, 2, 3
    };

    public static String[] TYPE_NAME = new String[]{
            "春", "夏", "秋", "冬"
    };

    private String[] jieQiBanners = new String[]{
            "http://a.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=bc0a162159df8db1a8237436684ab631/728da9773912b31b503bda908118367adab4e130.jpg",
            "http://h.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=d109fa668094a4c21e2eef796f9d70b0/4e4a20a4462309f797d3b67b750e0cf3d7cad61e.jpg",
            "http://g.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=3f39dba6ce5c10383073c690d378f876/c9fcc3cec3fdfc03442c3804dc3f8794a4c22632.jpg",
            "http://g.hiphotos.baidu.com/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=35e50a3a9545d688b70fbaf6c5ab167b/5bafa40f4bfbfbed5d7cf5767ff0f736afc31f96.jpg",
            "http://c.hiphotos.baidu.com/baike/crop%3D26%2C0%2C944%2C623%3Bc0%3Dbaike116%2C5%2C5%2C116%2C38/sign=67ea0d3f7bf0820239ddcb7f76c9cdc1/810a19d8bc3eb135f95b5ea7ae1ea8d3fd1f440a.jpg",
            "http://h.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=bfe7397d4134970a537e187df4a3baad/50da81cb39dbb6fd455214740924ab18972b3752.jpg",

            "http://g.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=5409df39a60f4bfb98dd960662261395/d439b6003af33a873f5bb0bcc45c10385343b566.jpg",
            "http://g.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=7701e8366b81800a7ae8815cd05c589f/d788d43f8794a4c2db47f69209f41bd5ad6e3926.jpg",
            "http://g.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=ae09176c31adcbef15397654cdc645b8/3b292df5e0fe9925c29a47f834a85edf8cb17187.jpg",
            "http://c.hiphotos.baidu.com/baike/crop%3D0%2C22%2C700%2C463%3Bc0%3Dbaike92%2C5%2C5%2C92%2C30/sign=1548ee084aa7d933abe7be33907bfd21/cefc1e178a82b901506b172f7b8da9773812efec.jpg",
            "http://d.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=aa349e686059252db71a155655f2685e/d0c8a786c9177f3e14c4807f70cf3bc79e3d56cb.jpg",
            "http://d.hiphotos.baidu.com/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=aadc2157b851f819e5280b18bbdd2188/8b13632762d0f703024132a000fa513d2797c5df.jpg",

            "http://f.hiphotos.baidu.com/baike/crop%3D0%2C40%2C1266%2C837%3Bc0%3Dbaike150%2C5%2C5%2C150%2C50/sign=a2835a68a5345982d1c5bfd231c41d99/54fbb2fb43166d227a14db884e2309f79052d24f.jpg",
            "http://a.hiphotos.baidu.com/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=e1ec8a3e652762d09433acedc185639f/eaf81a4c510fd9f99f2a6074232dd42a2834a4e9.jpg",
            "http://d.hiphotos.baidu.com/baike/crop%3D0%2C0%2C519%2C343%3Bc0%3Dbaike80%2C5%2C5%2C80%2C26/sign=ddda32783287e9505658a92c2d087f73/a2cc7cd98d1001e952db29b4b00e7bec55e797a1.jpg",
            "http://g.hiphotos.baidu.com/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=b77bdf42263fb80e18dc698557b8444b/48540923dd54564e24ca374ab4de9c82d1584f12.jpg",
            "http://h.hiphotos.baidu.com/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=d815bd48271f95cab2f89ae4a87e145b/b7fd5266d0160924e4071a2dd60735fae6cd34e1.jpg",
            "http://a.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=ea080c7e1cd5ad6ebef46cb8e0a252be/9922720e0cf3d7ca43d808ccf51fbe096b63a959.jpg",

            "http://g.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=14e98ab604d79123f4ed9c26cc5d32e7/7c1ed21b0ef41bd57307324a59da81cb39db3d3c.jpg",
            "http://e.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=ef8d94554f4a20a425133495f13bf347/dc54564e9258d1091f4565f5d158ccbf6d814dd7.jpg",
            "http://e.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=acc12098bd315c60579863bdecd8a076/b8014a90f603738de54880afb21bb051f819ec39.jpg",
            "http://d.hiphotos.baidu.com/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=985a529b36d12f2eda08a6322eabbe07/0eb30f2442a7d9332b23d73fad4bd11372f001f6.jpg",
            "http://f.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=bde671c2563d26973ade000f3492d99e/bd315c6034a85edf90bbb22b4e540923dd547586.jpg",
            "http://h.hiphotos.baidu.com/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=1eb298658d13632701e0ca61f0e6cb89/f31fbe096b63f624640d3dba8044ebf81a4ca332.jpg",

    };


    ArrayList<JieQiBean> jieQiBeanArrayList = new ArrayList<JieQiBean>() {
        {
            for (int i = 0; i < jieQiNames.length; i++) {
                JieQiBean jieQiBean = new JieQiBean();
                jieQiBean.setContent(jieQiContents[i]);
                jieQiBean.setDetail_info_url(jieQiDetailInfoUrl[i]);
                jieQiBean.setImage_url(jieQiPics[i]);
                jieQiBean.setTime(jieQiTime[i]);
                jieQiBean.setName(jieQiNames[i]);
                if (i > 6) {
                    jieQiBean.setType(types[0]);
                } else if (i >= 6 && i <= 11) {
                    jieQiBean.setType(types[1]);
                } else if (i >= 12 && i <= 17) {
                    jieQiBean.setType(types[2]);
                } else {
                    jieQiBean.setType(types[3]);
                }
                add(jieQiBean);
            }
        }
    };

    public ArrayList<JieQiBean> getJieQiBeanArrayList() {
        return jieQiBeanArrayList;
    }

    public String[] getJieQiBanners() {
        return jieQiBanners;
    }
}
