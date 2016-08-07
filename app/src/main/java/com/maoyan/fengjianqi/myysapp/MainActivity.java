package com.maoyan.fengjianqi.myysapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.maoyan.fengjianqi.myysapp.cinema.CinemaFragement;
import com.maoyan.fengjianqi.myysapp.find.FindFragement;
import com.maoyan.fengjianqi.myysapp.movie.MovieFragement;
import com.maoyan.fengjianqi.myysapp.personal.PersonnalFragement;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{
    @BindView(R.id.bottom_bar)BottomNavigationBar bottom_bar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Fragment> fragments;
    private MovieFragement moviefragement;
    private CinemaFragement cinemafragement;
    private FindFragement findfragement;
    private PersonnalFragement personnalfragement;
    private FragmentManager fragmentManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottom_bar.setMode(BottomNavigationBar.MODE_FIXED);
        bottom_bar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottom_bar.addItem(new BottomNavigationItem(R.mipmap.movie_normal, "电影").setActiveColorResource(R.color.maoyan_title__red))
                .addItem(new BottomNavigationItem(R.mipmap.cinema_normal, "影院").setActiveColorResource(R.color.maoyan_title__red))
                .addItem(new BottomNavigationItem(R.mipmap.find_normal, "发现").setActiveColorResource(R.color.maoyan_title__red))
                .addItem(new BottomNavigationItem(R.mipmap.my_normal, "我的").setActiveColorResource(R.color.maoyan_title__red))
                .setFirstSelectedPosition(0)
                .initialise();
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
        bottom_bar.setTabSelectedListener(this);
    }
    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                setTabSelection(0);
                break;
            case 1:
                setTabSelection(1);
                break;
            case 2:
                setTabSelection(2);
                break;
            case 3:
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }
    private void setTabSelection(int index) {
//        Bundle startendtime=new Bundle();
//
//        startendtime.putString("starttime", startdate);
//        startendtime.putString("endtime", enddate);
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (moviefragement == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    moviefragement = new MovieFragement();
//                    moviefragement.setArguments(startendtime);
                    transaction.add(R.id.layFrame, moviefragement);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(moviefragement);
                }
                break;
            case 1:
                if (cinemafragement == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    cinemafragement = new CinemaFragement();
//                    cinemafragement.setArguments(startendtime);

                    transaction.add(R.id.layFrame, cinemafragement);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(cinemafragement);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                if (findfragement == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    findfragement = new FindFragement();
                    //				stockcgargemrnt.setArguments(startendtime);
                    transaction.add(R.id.layFrame, findfragement);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(findfragement);
                }
                break;
            case 3:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                if (personnalfragement == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    personnalfragement = new PersonnalFragement();
                    //				stockcgargemrnt.setArguments(startendtime);
                    transaction.add(R.id.layFrame, personnalfragement);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(personnalfragement);
                }
                break;

            default:
                break;
        }
        transaction.commit();
    }
    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (moviefragement != null) {
            transaction.hide(moviefragement);
        }
        if (cinemafragement != null) {
            transaction.hide(cinemafragement);
        }
        if (findfragement != null) {
            transaction.hide(findfragement);
        }
        if (personnalfragement != null) {
            transaction.hide(personnalfragement);
        }

    }
}
