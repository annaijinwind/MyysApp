package com.maoyan.fengjianqi.myysapp;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.maoyan.fengjianqi.myysapp.location.City;
import com.maoyan.fengjianqi.myysapp.location.CityActivity;
import com.maoyan.fengjianqi.myysapp.utils.HttpUtils;
import com.maoyan.fengjianqi.myysapp.utils.SharedDataUtil;
import com.maoyan.fengjianqi.myysapp.utils.Urls;
import com.yxp.permission.util.lib.PermissionUtil;
import com.yxp.permission.util.lib.callback.PermissionResultCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_welcome)ImageView iv_welcome;
    public static SplashActivity context;
    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    context.setAnimotion();
                    break;
                case 1:
                    context.startActivity(new Intent(context, MainActivity.class));
                    context.finish();
                    break;

            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context=this;
        ButterKnife.bind(this);
        if (!SharedDataUtil.getAppBeenUsedLogin()){

            PermissionUtil.getInstance().request(SplashActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE
            }, new PermissionResultCallBack() {
                @Override
                public void onPermissionGranted() {
                    // 当所有权限的申请被用户同意之后,该方法会被调用
                    Intent ii=new Intent(SplashActivity.this,CityActivity.class);
                    ii.putExtra("splash","1");
                    startActivity(ii);
                    finish();
                }

                @Override
                public void onPermissionGranted(String... strings) {

                }
                @Override
                public void onPermissionDenied(String... strings) {
                    // 当权限申请中的某一个或多个权限,被用户曾经否定了,并确认了不再提醒时,也就是权限的申请窗口不能再弹出时,该方法将会被调用
                }

                @Override
                public void onRationalShow(String... strings) {
                    // 当权限申请中的某一个或多个权限,被用户否定了,但没有确认不再提醒时,也就是权限窗口申请时,但被否定了之后,该方法将会被调用.
                }
            });
        }
        else {
            String url = Urls.GuideUrl;
            HttpUtils.getGuideImage(url, iv_welcome,this);
        }


    }

    public void setAnimotion() {
        Animation anm= AnimationUtils.loadAnimation(this,R.anim.enlarge);
        anm.setFillAfter(true);
        iv_welcome.startAnimation(anm);
        anm.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, CityActivity.class));
                context.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
