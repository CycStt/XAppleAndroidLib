package com.xapple.gankio.home;

import com.xapple.gankio.APP;
import com.xapple.gankio.data.Injection.Injection;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 14:16
 * 修改人：wengyiming
 * 修改时间：2016/8/26 14:16
 * 修改备注：
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    //    all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
    private String[] TITLE = {"all", "Android", "iOS", "休息视频", "福利", "拓展资源", "前端", "瞎推荐", "App"};

    //
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        HomeFragment fragment = HomeFragment.newInstance(TITLE[position]);
        new HomePresenter(
                Injection.provideTasksRepository(APP.getInstance()),
                fragment,
                Injection.provideSchedulerProvider());
        return fragment;//首页推荐
    }

    @Override
    public int getCount() {
        return TITLE.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position];
    }
}
