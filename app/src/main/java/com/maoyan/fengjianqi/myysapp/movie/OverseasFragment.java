package com.maoyan.fengjianqi.myysapp.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maoyan.fengjianqi.myysapp.R;
import com.maoyan.fengjianqi.myysapp.fragements.BaseFragement;

/**
 * Created by fengjianqi on 2016/8/4.
 */
public class OverseasFragment extends BaseFragement{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overseas, container, false);
        return view;
    }
}
