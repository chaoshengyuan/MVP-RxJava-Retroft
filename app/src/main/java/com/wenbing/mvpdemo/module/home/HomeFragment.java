package com.wenbing.mvpdemo.module.home;

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
import com.wenbing.mvpdemo.beans.ArticleBean;
import com.wenbing.mvpdemo.beans.BannerBean;
import com.wenbing.mvpdemo.event.CollectEvent;
import com.wenbing.mvpdemo.module.RecyclerFragment;
import com.wenbing.mvpdemo.module.adapter.HomeAdapter;
import com.wenbing.mvpdemo.module.adapter.base.BaseRVAdapter;
import com.wenbing.mvpdemo.module.article.ArticleDetailActivity;
import com.wenbing.mvpdemo.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wenbing
 * @date: 2020/3/5 10:39
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeView, RecyclerFragment.RecyclerListener, BaseRVAdapter.OnItemClickLinsener {
    private HomeAdapter mAdapter;
    private RecyclerFragment<ArticleBean.DataBean> recyclerFragment;
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
        mAdapter = new HomeAdapter(mContext, new ArrayList<ArticleBean.DataBean>());
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
    protected void onVisible(boolean isFirstVisible) {
        super.onVisible(isFirstVisible);
        if(!isFirstVisible){
            mAdapter.notifyDataSetChanged();
        }
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
    public void showData(ArticleBean ArticleBean, int action) {
        recyclerFragment.loadCompleted(action, "", ArticleBean == null ? null : ArticleBean.getDatas());
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
        ArticleBean.DataBean dataBean = mAdapter.getBeans().get(position - 2);
        ArticleDetailActivity.start(mContext,dataBean);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectedAction(CollectEvent collectEvent){
        List<ArticleBean.DataBean> dataBeans  = mAdapter.getBeans();
        for (int i = 0; i < dataBeans.size(); i++) {
            if(dataBeans.get(i).getId()==collectEvent.getArticleID()){
                dataBeans.get(i).setCollect(collectEvent.isCollected());
                //2是因为RecyclerView加了2个header
                mAdapter.notifyItemChanged(i+ 2);
                break;
            }
        }
    }
}
