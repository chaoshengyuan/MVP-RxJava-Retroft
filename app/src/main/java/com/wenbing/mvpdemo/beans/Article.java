package com.wenbing.mvpdemo.beans;

import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 13:15
 */
public class Article {
    public int curPage;
    public int offset;
    public boolean over;
    public int pageCount;
    public int size;
    public int total;
    public List<DataDetailBean> datas;
    public static class DataDetailBean {
        /**
         * apkLink :
         * author : 叶应是叶
         * chapterId : 67
         * chapterName : 网络基础
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : false
         * id : 2809
         * link : https://www.jianshu.com/p/6d2f324c8f42
         * niceDate : 2018-04-12
         * origin :
         * projectLink :
         * publishTime : 1523532264000
         * superChapterId : 98
         * superChapterName : 网络访问
         * tags : []
         * title : 在 Android 设备上搭建 Web 服务器
         * type : 0
         * visible : 1
         * zan : 0
         */

        public String apkLink;
        public String author;
        public int chapterId;
        public String chapterName;
        public boolean collect;
        public int courseId;
        public String desc;
        public String envelopePic;
        public boolean fresh;
        public int id;
        public int originId;
        public String link;
        public String niceDate;
        public String origin;
        public String projectLink;
        public long publishTime;
        public int superChapterId;
        public String superChapterName;
        public String title;
        public int type;
        public int visible;
        public int zan;
        public List<?> tags;
    }
}
