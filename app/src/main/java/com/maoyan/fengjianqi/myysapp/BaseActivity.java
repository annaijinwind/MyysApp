package com.maoyan.fengjianqi.myysapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.maoyan.fengjianqi.myysapp.utils.Tools;
import com.maoyan.fengjianqi.myysapp.utils.Utils;

/**
 * Created by fengjianqi on 2016/7/27.
 */
public class BaseActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private boolean checkNetwork() {
        if (!Utils.isNetworkConnected(BaseActivity.this)) {
            String sError = getString(R.string.network_unavailable);
            Tools.showToast(sError,this);
            return false;
        }
        return true;
    }
}
