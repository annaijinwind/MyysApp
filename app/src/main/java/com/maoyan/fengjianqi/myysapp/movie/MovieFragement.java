package com.maoyan.fengjianqi.myysapp.movie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maoyan.fengjianqi.myysapp.R;
import com.maoyan.fengjianqi.myysapp.fragements.BaseFragement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by fengjianqi on 2016/7/30.
 */
public class MovieFragement extends BaseFragement{

    @BindView(R.id.tv_fire)
    TextView tv_fire;
    @BindView(R.id.tv_wait)
    TextView tv_wait;
    @BindView(R.id.tv_overseas)
    TextView tv_overseas;
    @BindView(R.id.movie_topicViewpager)
    ViewPager movie_topicViewpager;
    @BindView(R.id.iv_point)
    ImageView iv_point;
    @BindView(R.id.ll_point)
    LinearLayout ll_point;
    @BindView(R.id.ll_adr)
    LinearLayout ll_adr;
    private List<BaseFragement>fragementList=new ArrayList<BaseFragement>();
    private MovePagerAdapter pageradapter;
    /**
     * 两个标题的间距
     */
    private int leftMargin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_move, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        InitView();
//        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("agrs1");
    }
    private void InitView(){
        fragementList.add(new FireFragment());
        fragementList.add(new WaitFragment());
        fragementList.add(new OverseasFragment());
        pageradapter=new MovePagerAdapter(getActivity().getSupportFragmentManager(),fragementList);
        movie_topicViewpager.setAdapter(pageradapter);
        movie_topicViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                //得到浮动块的移动距离
                int currstanceLeft= (int) ((position+positionOffset)*leftMargin);

                /**
                 * 针对移动的距离来设置透明度
                 */
                if(currstanceLeft>0&&currstanceLeft<leftMargin) {
                    tv_fire.setAlpha(positionOffset);
                    tv_wait.setAlpha(positionOffset);
                }else if(currstanceLeft>leftMargin&&currstanceLeft<leftMargin*2){
                    tv_wait.setAlpha(positionOffset);
                    tv_overseas.setAlpha(positionOffset);
                }else if(currstanceLeft==0||currstanceLeft==leftMargin||currstanceLeft==leftMargin*2){
                    tv_fire.setAlpha(1);
                    tv_wait.setAlpha(1);
                    tv_overseas.setAlpha(1);
                }

                /**
                 * 得到imageview的参数
                 * 动态设置参数
                 */
                iv_point.setLeft(currstanceLeft);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv_point.getLayoutParams();
                params.leftMargin=currstanceLeft;//赋值
                iv_point.setLayoutParams(params);//重新设置
            }

            @Override
            public void onPageSelected(int position) {
//                BaseFragement fragment = fragementList.get(position);
//                if(!fragment.isFragment) {
//                    fragment.isFragment=true;
//                    fragementList.get(position).initData();
//                }
                for (int i = 0; i <3 ; i++) {
                    //先设置所有为白色
                    ((TextView) (ll_point.getChildAt(i))).setTextColor(Color.WHITE);
                }
                //设置当前为红色
                ((TextView)ll_point.getChildAt(position)).setTextColor(Color.RED);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        iv_point.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                iv_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                leftMargin = ll_point.getChildAt(1).getLeft()-ll_point.getChildAt(0).getLeft();
            }
        });
    }
    @OnClick(R.id.tv_fire) void Fire(View v){
        movie_topicViewpager.setCurrentItem(0);
    }
    @OnClick(R.id.tv_wait) void Wait(View v){
        movie_topicViewpager.setCurrentItem(1);
    }
    @OnClick(R.id.tv_overseas) void Overseas(View v){
        movie_topicViewpager.setCurrentItem(2);
    }
}
