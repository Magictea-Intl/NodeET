package com.stareating.nodeet;

import java.util.List;

/**
 * Created by Stardust on 2017/9/14.
 */

public class Categories {

    public static class CategoryItem {

        int cid;
        String name;
        String description;
        int topic_count;
        int post_count;

    }

    List<CategoryItem> categories;

    public List<CategoryItem> getCategoryItems() {
        return categories;
    }
}
