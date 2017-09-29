package com.stareating.nodeet.network.api;

import java.util.List;

/**
 * Created by Stardust on 2017/9/14.
 */

public class Categories {

    public static class Category {

        int cid;
        String name;
        String description;
        String icon;
        String bgColor;
        int topic_count;
        int post_count;

        public int getCid() {
            return cid;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }

        public String getBgColor() {
            return bgColor;
        }

        public int getTopicCount() {
            return topic_count;
        }

        public int getPostCount() {
            return post_count;
        }
    }

    List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }
}
