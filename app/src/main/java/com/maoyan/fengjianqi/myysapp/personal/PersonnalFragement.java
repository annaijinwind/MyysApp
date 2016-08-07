package com.maoyan.fengjianqi.myysapp.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maoyan.fengjianqi.myysapp.R;
import com.maoyan.fengjianqi.myysapp.fragements.BaseFragement;

/**
 * Created by fengjianqi on 2016/7/30.
 */
public class PersonnalFragement extends BaseFragement {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_personnl, container, false);
//        Bundle bundle = getArguments();
//        String agrs1 = bundle.getString("agrs1");
        return view;
    }

    public static PersonnalFragement newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        PersonnalFragement fragment = new PersonnalFragement();
        fragment.setArguments(args);
        return fragment;

    }
}
