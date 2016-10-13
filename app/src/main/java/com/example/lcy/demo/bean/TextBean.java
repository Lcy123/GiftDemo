package com.example.lcy.demo.bean;

import java.util.List;

/**
 * Created by Lcy on 2016/10/7.
 */
public class TextBean {

    /**
     * id : 1475205577
     * iconurl : /allimgs/img_iapp/201609/_1475049290778.png
     * gname : 星座英雄
     * testtype : 2
     * score : 5
     * linkurl : 10:00
     * istop : 0
     * colors : 0
     * platform : 1
     * operators : 山东千贝网络科技有限公司
     * state : 1
     * addtime : 10-12 10:00
     * teststarttime : 2016-10-12 10:00:00
     * gift : 角色扮演
     * keyword :
     * uid : 402881d255627faf01556c3e92fa0255
     * gid : 1464169212
     * indexpy : 0
     * isdel : 1
     * openflag : 2
     * openflagname : 封测
     * vtypeimage : <i class='android'></i>
     */

    private List<InfoBean> info;

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        private int id;
        private String iconurl;
        private String gname;
        private String testtype;
        private int score;
        private String linkurl;
        private int istop;
        private int colors;
        private String platform;
        private String operators;
        private int state;
        private String addtime;
        private String teststarttime;
        private String gift;
        private String keyword;
        private String uid;
        private String gid;
        private String indexpy;
        private int isdel;
        private int openflag;
        private String openflagname;
        private String vtypeimage;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getTesttype() {
            return testtype;
        }

        public void setTesttype(String testtype) {
            this.testtype = testtype;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getLinkurl() {
            return linkurl;
        }

        public void setLinkurl(String linkurl) {
            this.linkurl = linkurl;
        }

        public int getIstop() {
            return istop;
        }

        public void setIstop(int istop) {
            this.istop = istop;
        }

        public int getColors() {
            return colors;
        }

        public void setColors(int colors) {
            this.colors = colors;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getOperators() {
            return operators;
        }

        public void setOperators(String operators) {
            this.operators = operators;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getTeststarttime() {
            return teststarttime;
        }

        public void setTeststarttime(String teststarttime) {
            this.teststarttime = teststarttime;
        }

        public String getGift() {
            return gift;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getIndexpy() {
            return indexpy;
        }

        public void setIndexpy(String indexpy) {
            this.indexpy = indexpy;
        }

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public int getOpenflag() {
            return openflag;
        }

        public void setOpenflag(int openflag) {
            this.openflag = openflag;
        }

        public String getOpenflagname() {
            return openflagname;
        }

        public void setOpenflagname(String openflagname) {
            this.openflagname = openflagname;
        }

        public String getVtypeimage() {
            return vtypeimage;
        }

        public void setVtypeimage(String vtypeimage) {
            this.vtypeimage = vtypeimage;
        }
    }
}
