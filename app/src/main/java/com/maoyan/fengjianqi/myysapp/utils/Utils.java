package com.maoyan.fengjianqi.myysapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 工具类
 */
public class Utils {
    /**判断是否有网络***/
    public static boolean isNetworkConnected(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {

                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {

                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
/*****判断wifi是否可用****/
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }
   /*****判断手机网络是否可用****/
    public static boolean isMobileConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }
    /***json转list***/
    public static <T> ArrayList<T> getObjesByGson(String json, T classOfT) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();

        ArrayList<JsonObject> jsonObjs = new Gson().fromJson(json, type);

        ArrayList<T> listOfT = new ArrayList<T>();
        for (JsonObject jsonObj : jsonObjs) {
            listOfT.add((T) new Gson().fromJson(jsonObj.toString(),
                    classOfT.getClass()));
        }

        return listOfT;
    }
/****json转对象****/
    public static Object getObjByGson(String json, Class clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static JSONArray getArrayValue(JSONObject obj, String key) {
        JSONArray jionMans = new JSONArray();
        try {
            jionMans = obj.getJSONArray(key);
        } catch (Exception e) {
            Log.i("TAG",e.getMessage());
        }
        return jionMans;
    }
}
