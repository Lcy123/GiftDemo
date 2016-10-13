package com.example.lcy.demo.bean;

import java.util.List;

/**
 * Created by Lcy on 2016/9/27.
 */
public class GiftListBean {

    /**
     * ad : [{"id":249,"title":"绝世武神独家礼包（IOS）","flag":1,"iconurl":"/allimgs/img_iad/_1474623667919.jpg","addtime":"2016-09-17","giftid":"1474618069","appName":"绝世武神","appLogo":"/allimgs/img_iapp/201607/_1469611677571.jpg","appId":1444892111},{"id":246,"title":"天天炫舞中秋独家礼包","flag":1,"iconurl":"/allimgs/img_iad/_1473751476804.jpg","addtime":"2016-09-15","giftid":"1473749901","appName":"天天炫舞","appLogo":"/allimgs/img_iapp/201609/_1474340811708.png","appId":2496048},{"id":250,"title":"克鲁赛德战记国庆独家专题礼包","flag":1,"iconurl":"/allimgs/img_iad/_1474961155879.jpg","addtime":"2016-09-15","linkurl":"http://www.1688wan.com/libao/1474959513.html","giftid":"1474959513","appName":"克鲁赛德战记","appLogo":"/allimgs/img_iapp/201604/_1459853961237.png","appId":1435301325},{"id":243,"title":"航海王强者之路中秋媒体节日礼包","flag":1,"iconurl":"/allimgs/img_iad/_1473316667867.jpg","addtime":"2016-09-14","giftid":"1473316129","appName":"航海王强者之路","appLogo":"/allimgs/img_iapp/201511/_1448446144213.png","appId":1448446177},{"id":245,"title":"大秦帝国OL中秋独家礼包 ","flag":1,"iconurl":"/allimgs/img_iad/_1473749952262.jpg","addtime":"2016-09-14","giftid":"1473742131","appName":"大秦帝国OL","appLogo":"/allimgs/img_iapp/201607/_1469687930154.jpg","appId":1467784200}]
     * pageno : 181
     * list : [{"id":"1474942859","iconurl":"/allimgs/img_iapp/201606/_1465177034263.jpg","giftname":"媒体尊贵礼包","number":159,"exchanges":1,"type":1,"gname":"进击的少女","integral":0,"isintegral":0,"addtime":"2016-09-27","ptype":"1,2,","operators":"","flag":1},{"id":"1474943718","iconurl":"/allimgs/img_iapp/201508/_1439365416187.png","giftname":"1688玩独家国庆礼包","number":100,"exchanges":0,"type":1,"gname":"宠物小精灵官方版","integral":0,"isintegral":0,"addtime":"2016-09-27","ptype":"1,","operators":"","flag":1},{"id":"1474955505","iconurl":"/allimgs/img_iapp/201606/_1466748346763.png","giftname":"国庆礼包","number":100,"exchanges":0,"type":1,"gname":"镜花奇缘","integral":0,"isintegral":0,"addtime":"2016-09-27","ptype":"1,2,","operators":"","flag":1},{"id":"1474955714","iconurl":"/allimgs/img_iapp/201604/_1461148472455.png","giftname":"国庆礼包","number":100,"exchanges":0,"type":1,"gname":"全民打BOSS","integral":0,"isintegral":0,"addtime":"2016-09-27","ptype":"1,2,","operators":"","flag":1},{"id":"1474957327","iconurl":"/allimgs/img_iapp/201512/_1450754502745.jpg","giftname":"国庆礼包","number":170,"exchanges":0,"type":1,"gname":"弹弹堂S","integral":0,"isintegral":0,"addtime":"2016-09-27","ptype":"1,2,","operators":"","flag":1},{"id":"1474957898","iconurl":"/allimgs/img_iapp/201608/_1472028153693.jpg","giftname":"上线礼包","number":99,"exchanges":1,"type":1,"gname":"魔灵战纪","integral":0,"isintegral":0,"addtime":"2016-09-27","ptype":"1,2,","operators":"","flag":1},{"id":"1474958088","iconurl":"/allimgs/img_iapp/201607/_1468306026273.png","giftname":"国庆礼包","number":30,"exchanges":0,"type":1,"gname":"啪啪三国","integral":0,"isintegral":0,"addtime":"2016-09-27","ptype":"1,2,","operators":"","flag":1},{"id":"1474958743","iconurl":"/allimgs/img_iapp/201607/_1467969041073.png","giftname":"周年庆礼包","number":60,"exchanges":0,"type":1,"gname":"新秦时明月","integral":0,"isintegral":0,"addtime":"2016-09-27","ptype":"1,2,","operators":"","flag":1},{"id":"1474960846","iconurl":"/allimgs/img_iapp/201604/_1459853961237.png","giftname":"国庆独家专题礼包","number":25,"exchanges":78,"type":1,"gname":"克鲁赛德战记","integral":0,"isintegral":0,"addtime":"2016-09-27","ptype":"1,","operators":"","flag":1},{"id":"1474961449","iconurl":"/allimgs/img_iapp/201609/_1474532136471.png","giftname":"国庆登录礼包","number":160,"exchanges":0,"type":1,"gname":"死神觉醒","integral":0,"isintegral":0,"addtime":"2016-09-27","ptype":"1,2,","operators":"","flag":1}]
     */

    private int pageno;
    /**
     * id : 249
     * title : 绝世武神独家礼包（IOS）
     * flag : 1
     * iconurl : /allimgs/img_iad/_1474623667919.jpg
     * addtime : 2016-09-17
     * giftid : 1474618069
     * appName : 绝世武神
     * appLogo : /allimgs/img_iapp/201607/_1469611677571.jpg
     * appId : 1444892111
     */

    private List<AdBean> ad;
    /**
     * id : 1474942859
     * iconurl : /allimgs/img_iapp/201606/_1465177034263.jpg
     * giftname : 媒体尊贵礼包
     * number : 159
     * exchanges : 1
     * type : 1
     * gname : 进击的少女
     * integral : 0
     * isintegral : 0
     * addtime : 2016-09-27
     * ptype : 1,2,
     * operators :
     * flag : 1
     */

    private List<ListBean> list;

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public List<AdBean> getAd() {
        return ad;
    }

    public void setAd(List<AdBean> ad) {
        this.ad = ad;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class AdBean {
        private int id;
        private String title;
        private int flag;
        private String iconurl;
        private String addtime;
        private String giftid;
        private String appName;
        private String appLogo;
        private int appId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getGiftid() {
            return giftid;
        }

        public void setGiftid(String giftid) {
            this.giftid = giftid;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppLogo() {
            return appLogo;
        }

        public void setAppLogo(String appLogo) {
            this.appLogo = appLogo;
        }

        public int getAppId() {
            return appId;
        }

        public void setAppId(int appId) {
            this.appId = appId;
        }
    }

    public static class ListBean {
        private String id;
        private String iconurl;
        private String giftname;
        private int number;
        private int exchanges;
        private int type;
        private String gname;
        private int integral;
        private int isintegral;
        private String addtime;
        private String ptype;
        private String operators;
        private int flag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getGiftname() {
            return giftname;
        }

        public void setGiftname(String giftname) {
            this.giftname = giftname;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getExchanges() {
            return exchanges;
        }

        public void setExchanges(int exchanges) {
            this.exchanges = exchanges;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getIsintegral() {
            return isintegral;
        }

        public void setIsintegral(int isintegral) {
            this.isintegral = isintegral;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getPtype() {
            return ptype;
        }

        public void setPtype(String ptype) {
            this.ptype = ptype;
        }

        public String getOperators() {
            return operators;
        }

        public void setOperators(String operators) {
            this.operators = operators;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
