package com.stareating.nodeet;

import java.util.List;

/**
 * Created by 婷 on 2017/9/15.
 */

public class Posts {

    public static class PostItem {
        int tid;
        int uid;
        int pid;
        String title;
        int viewcount;
        int postcount;
        long timestamp;
        boolean deleted;
        User user;
    }

    List<PostItem> topics;

    public List<PostItem> getPostItems() {
        return topics;
    }
}
