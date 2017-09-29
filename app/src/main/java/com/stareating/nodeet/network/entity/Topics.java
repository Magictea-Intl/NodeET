package com.stareating.nodeet.network.entity;

import java.util.List;

/**
 * Created by 婷 on 2017/9/15.
 */

public class Topics {

    //这里把public改成Getter，一个作用是让变量只读，不能让外部轻易修改。
    //面向对象编程的一个原则就是封装。
    public static class Topic {
         int tid;
         int uid;
         int pid;
         String title;
         int viewcount;
         int postcount;
         long timestamp;
         boolean deleted;
         User user;

        public int getTid() {
            return tid;
        }

        public int getUid() {
            return uid;
        }

        public int getPid() {
            return pid;
        }

        public String getTitle() {
            return title;
        }

        public int getViewcount() {
            return viewcount;
        }

        public int getPostcount() {
            return postcount;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public User getUser() {
            return user;
        }
    }

    List<Topic> topics;

    public List<Topic> getTopics() {
        return topics;
    }
}
