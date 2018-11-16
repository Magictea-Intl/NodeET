package com.stareating.nodeet.ui.main.page;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static com.stareating.nodeet.ui.topic.TopicListActivity.CATEGORY_ID;
import static com.stareating.nodeet.ui.topic.TopicListActivity.CATEGORY_NAME;

/**
 * Created by Stardust on 2017/9/16.
 */
@EFragment(R.layout.fragment_category_list)
public class CategoryListFragment extends Fragment {

    private static final String LOG_TAG = "CategoryListFragment";

    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.category_list)
    RecyclerView mCategoryListRecyclerView;

    private CategoryListAdapter mCategoryListAdapter = new CategoryListAdapter();
    private List<Categories.Category> mCategories = new ArrayList<>();
    private Retrofit mRetrofit = NodeBBService.getInstance().getRetrofit();


    @AfterViews
    void setupViews() {
        mSwipeRefreshLayout.setOnRefreshListener(this::fetchCategories);
        setUpCategoryList();
        fetchCategories();
    }

    private void fetchCategories() {
        //网络访问等耗时操作不能在主线程(UI线程)中执行，否则会造成界面卡顿。
        //返回Call，异步执行并在获取后执行回调，不用开线程。
        mSwipeRefreshLayout.setRefreshing(true);
        // TODO 此处会crash 需要修复
        mRetrofit.create(CategoryApi.class)
                .getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                    mCategories = categories.getCategories();
                    mCategoryListAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                }, error -> {
                    Toast.makeText(getContext(), R.string.fetch_failed, Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                    mSwipeRefreshLayout.setRefreshing(false);

                });
    }


    private void setUpCategoryList() {
        mCategoryListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCategoryListRecyclerView.setAdapter(mCategoryListAdapter);
        mCategoryListRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .colorResId(R.color.color_category_list_divider)
                .sizeResId(R.dimen.size_category_list_divider)
                .build());
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
