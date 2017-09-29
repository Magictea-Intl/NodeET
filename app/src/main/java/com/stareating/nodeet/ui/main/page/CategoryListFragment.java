package com.stareating.nodeet.ui.main.page;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.stareating.nodeet.R;
import com.stareating.nodeet.network.NodeBBService;
import com.stareating.nodeet.network.api.Categories;
import com.stareating.nodeet.network.api.CategoryApi;
import com.stareating.nodeet.ui.common.Typefaces;
import com.stareating.nodeet.ui.topic.TopicListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static com.stareating.nodeet.ui.topic.TopicListActivity.CATEGORY_ID;
import static com.stareating.nodeet.ui.topic.TopicListActivity.CATEGORY_NAME;

/**
 * Created by Stardust on 2017/9/16.
 */
public class CategoryListFragment extends Fragment {

    private static final String LOG_TAG = "CategoryListFragment";

    private CategoryListAdapter mCategoryListAdapter = new CategoryListAdapter();
    private List<Categories.Category> mCategories = new ArrayList<>();
    private Retrofit mRetrofit = NodeBBService.getInstance().getRetrofit();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpCategoryList(view);
        fetchCategories();

    }

    private void fetchCategories() {
        //网络访问等耗时操作不能在主线程(UI线程)中执行，否则会造成界面卡顿。
        //返回Call，异步执行并在获取后执行回调，不用开线程。

        mRetrofit.create(CategoryApi.class)
                .getCategories()
                .enqueue(new Callback<Categories>() {
                    @Override
                    public void onResponse(Call<Categories> call, retrofit2.Response<Categories> response) {
                        mCategories = response.body().getCategories();
                        mCategoryListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Categories> call, Throwable t) {
                        Toast.makeText(getContext(), R.string.fetch_failed, Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }

                });
    }


    private void setUpCategoryList(View view) {
        RecyclerView categoryListView = (RecyclerView) view.findViewById(R.id.category_list);
        categoryListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryListView.setAdapter(mCategoryListAdapter);
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.topic_count)
        TextView topic_count;

        @BindView(R.id.post_count)
        TextView post_count;

        @BindView(R.id.icon)
        TextView icon;

        GradientDrawable iconBackground;

        CategoryViewHolder(View itemView) {
            super(itemView);
            //第一个参数是注解所在的对象。第二个参数是View。
            ButterKnife.bind(this, itemView);
            icon.setTypeface(Typefaces.getAwesomeFont(getContext()));
            iconBackground = (GradientDrawable) icon.getBackground();
        }

        @OnClick(R.id.item)
        void showCategoryContent() {
            int pos = getAdapterPosition();
            Categories.Category item = mCategories.get(pos);
            startActivity(new Intent(getActivity(), TopicListActivity.class)
                    .putExtra(CATEGORY_ID, item.getCid())
                    .putExtra(CATEGORY_NAME, item.getName()));
        }
    }

    private class CategoryListAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

        @Override
        public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_category, parent, false));
        }

        @Override
        public void onBindViewHolder(CategoryViewHolder holder, int position) {
            Categories.Category category = mCategories.get(position);
            holder.name.setText(category.getName());
            holder.description.setText(category.getDescription());
            holder.topic_count.setText(String.valueOf(category.getTopicCount()));
            holder.post_count.setText(String.valueOf(category.getPostCount()));
            holder.iconBackground.setColor(Color.parseColor(category.getBgColor()));
            holder.icon.setText(getCharForFontAwesome(category.getIcon()));
        }

        @Override
        public int getItemCount() {
            return mCategories == null ? 0 : mCategories.size();
        }
    }

    private String getCharForFontAwesome(String icon) {
        int resId = getResources().getIdentifier(icon.replace('-', '_'), "string", getActivity().getPackageName());
        if (resId == 0)
            return "";
        return getString(resId);
    }

}
