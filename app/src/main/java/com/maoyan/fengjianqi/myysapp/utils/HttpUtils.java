package com.maoyan.fengjianqi.myysapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.maoyan.fengjianqi.myysapp.R;
import com.maoyan.fengjianqi.myysapp.SplashActivity;
import com.maoyan.fengjianqi.myysapp.bean.Posterbean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

/**
 * Created by fengjianqi on 2016/7/27.
 */
public class HttpUtils {
    public static void getGuideImage(String url, final ImageView iv_welcome, final Context context) {

        OkHttpUtils
                .get()
                .url(url)
                /*.addParams("username", "hyman")
                .addParams("password", "123")*/
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("TAG", "请求引导页面=" + e.getMessage());
                        Tools.showToast("图片请求失败",context);
                        SplashActivity.handler.sendEmptyMessage(1);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("TAG", "请求引导页面=" + response);
                        try {
                            JSONObject json = new JSONObject(response);

                            JSONArray jsonArray = json.getJSONArray("posters");
                            List<Posterbean>listData=new ArrayList<Posterbean>();
                            listData.addAll(Utils.getObjesByGson(jsonArray.toString(),new Posterbean()));

                            String url =listData.get(0).getPic();
                            OkHttpUtils
                                    .get()
                                    .url(url)
                                    .build()
                                    .execute(new BitmapCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {

                                        }

                                        @Override
                                        public void onResponse(Bitmap response, int id) {
                                            iv_welcome.setImageBitmap(response);
                                            SplashActivity.handler.sendEmptyMessage(0);
                                        }
                                    });
//                            ControllerListener listener = new BaseControllerListener(){
//                                @Override
//                                public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
//                                    super.onFinalImageSet(id, imageInfo, animatable);
//                                    SplashActivity.handler.sendEmptyMessage(0);
//                                }
//
//                                @Override
//                                public void onFailure(String id, Throwable throwable) {
//                                    super.onFailure(id, throwable);
//                                    Log.i("TAG", "图片请求失败");
//                                }
//
//                                @Override
//                                public void onIntermediateImageFailed(String id, Throwable throwable) {
//                                    super.onIntermediateImageFailed(id, throwable);
//                                }
//                            };
//                            DraweeController draweeController1 = Fresco.newDraweeControllerBuilder().setUri(Uri.parse(url)) .setControllerListener(listener)
//                                    .build();
//                            iv_welcome.setController(draweeController1);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });
    }

}
