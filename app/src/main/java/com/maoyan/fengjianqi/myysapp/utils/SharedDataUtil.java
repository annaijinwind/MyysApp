package com.maoyan.fengjianqi.myysapp.utils;

import com.jdsoft.os.data.SaveData;

/**
 * Created by fengjianqi on 2016/8/2.
 */
public class SharedDataUtil {
    static private SaveData Obj = new SaveData();
    //判断是否是第一次使用app
    private static final String APP_BEEN_USED_LOGIN = "app_been_used_login";


    public static boolean getAppBeenUsedLogin() {
        return Obj.getBoolean(APP_BEEN_USED_LOGIN, true);
    }
    public static void setAppBeenUsedLogin(boolean value) {
        Obj.setBoolean(APP_BEEN_USED_LOGIN, value);
    }
    public static void clear(){
        setAppBeenUsedLogin(false);
    }
}
