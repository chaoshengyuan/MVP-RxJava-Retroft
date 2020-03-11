package com.wenbing.mvpdemo.module.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wenbing.mvpdemo.R;
import com.wenbing.mvpdemo.base.BaseFragment;
import com.wenbing.mvpdemo.beans.Article;
import com.wenbing.mvpdemo.beans.BannerBean;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.module.adapter.HomeAdapter;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;
import com.wenbing.mvpdemo.module.article.ArticleDetailActivity;
import com.wenbing.mvpdemo.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:39
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView, RecyclerFragment.RecyclerListener, BaseRVAdapter.OnItemClickLinsener {
    private HomeAdapter mAdapter;
    private RecyclerFragment<Article.DataBean> recyclerFragment;
//    private List<BannerBean>  bannerDatas;
    private Banner banner;
    @Override
    protected int initLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createrPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initViewsAndListener() {
        mAdapter = new HomeAdapter(mContext, new ArrayList<Article.DataBean>());
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        recyclerFragment = RecyclerFragment.newInstance();
        fragmentTransaction.add(R.id.home_frame, recyclerFragment).commit();
        recyclerFragment.init(mAdapter, this);
        mAdapter.setOnItemClickLinsener(this);
    }

    @Override
    public void onRecyclerCreated(XRecyclerView recyclerView) {
        createBanner();
        mAdapter.setXRecyclerView(recyclerView);
    }

    @Override
    public void loadData(int action, int pageSize, int page) {
        mPresenter.requestData(action, pageSize, page);
        mPresenter.requestBanner();
    }

    private void createBanner() {
        View header =   LayoutInflater.from(mContext).inflate(R.layout.layout_banner, (ViewGroup)$(android.R.id.content),false);
        banner = header.findViewById(R.id.banner);
        //直接new出来为什么不显示？？？
//        banner = new Banner(mContext);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);

        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
//        recyclerFragment.getRecyclerView().addHeaderView(banner);
        recyclerFragment.getRecyclerView().addHeaderView(header);
        recyclerFragment.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager)recyclerView.getLayoutManager();
                int i = manager.findFirstVisibleItemPosition();
                if(i>1){
                    banner.stopAutoPlay();
                }else{
                    banner.startAutoPlay();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void showData(Article article, int action) {
        recyclerFragment.loadCompleted(action, "", article == null ? null : article.getDatas());
    }

    @Override
    public void showBanner(List<BannerBean> banners) {
        List<String> bannerUrls = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (BannerBean bean : banners) {
            bannerUrls.add(bean.getImagePath());
            titles.add(bean.getTitle());
        }
        //测试当banner影藏后的是否stop
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.d("测试滚动：",i+"");
            }

            @Override
            public void onPageSelected(int i) {
           }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //设置图片集合
        banner.setImages(bannerUrls);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


    @Override
    public void onItemClick(BaseRVAdapter baseAdapter, int position) {
        Article.DataBean dataBean = mAdapter.getBeans().get(position - 2);
        Intent intent = new Intent(mContext, ArticleDetailActivity.class);
        intent.putExtra("url", dataBean.getLink());
        startActivity(intent);
    }
}
