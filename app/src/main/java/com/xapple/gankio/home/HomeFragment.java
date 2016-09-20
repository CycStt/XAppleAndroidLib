package com.xapple.gankio.home;

import com.xapple.gankio.R;
import com.xapple.gankio.data.model.GankIO;
import com.xapple.gankio.widget.DefaultFooter;
import com.xapple.gankio.widget.DefaultHeader;
import com.xapple.gankio.widget.RefreshLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 14:30
 * 修改人：wengyiming
 * 修改时间：2016/8/26 14:30
 * 修改备注：
 */
public class HomeFragment extends Fragment implements HomeContract.View {


    @BindView(R.id.ac_fragment_home)
    RecyclerView mRecyclerView;
    @BindView(R.id.freshLayout)
    RefreshLayout mRefreshLayout;


    private HomeRecyclerAdapter mHomeRecycleAdapter;
    private int page = 1;
    private String type = "福利";

    private HomePresenter mHomePresenter;

    public static HomeFragment newInstance(String type) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            type = bundle.getString("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ac_fragment_home, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        if (mHomeRecycleAdapter == null) {
            mHomeRecycleAdapter = new HomeRecyclerAdapter();
        }
        mRecyclerView.setAdapter(mHomeRecycleAdapter);
        mRefreshLayout.setHeader(new DefaultHeader(getActivity()));
        mRefreshLayout.setFooter(new DefaultFooter(getActivity()));
        mRefreshLayout.setType(RefreshLayout.Type.FOLLOW);
        mRefreshLayout.setGive(RefreshLayout.Give.BOTH);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshLayout.setListener(new RefreshLayout.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getList();
            }

            @Override
            public void onLoadmore() {
                ++page;
                getList();
            }
        });
        getList();
    }

    private void getList() {
        mHomePresenter.getGankList(type, 10, page);
    }


    @Override
    public void onResume() {
        super.onResume();
        mHomePresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHomePresenter.unsubscribe();
    }

    @Override
    public void showList(List<GankIO> listResult) {
        Timber.tag("OkHttp").e(listResult.toString());
        mHomeRecycleAdapter.onArticleResult(listResult);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mHomePresenter = (HomePresenter) presenter;
    }

    @Override
    public void showRefresh() {
        mRefreshLayout.callFresh();
    }

    @Override
    public void endRefresh() {
        mRefreshLayout.onFinishFreshAndLoad();
    }

    @Override
    public void loadMore() {
    }

    @Override
    public void showError(String error) {

    }

    public interface GetResult {
        void onArticleResult(List<GankIO> mArticleResult);
    }
}
