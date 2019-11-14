package com.example.cctms.util;

import android.content.Context;
import android.content.SharedPreferences;

public class userInfo {
    /**
     *          SharedPreferences sharedPre=getSharedPreferences("config", MODE_PRIVATE);
     *         String saveusername=sharedPre.getString("username", "");
     *         String savepassword=sharedPre.getString("password", "");
     * 使用SharedPreferences保存用户登录信息
     * @param context
     * @param username
     * @param password
     */
    public static void saveLoginInfo(Context context, String username, String password){
        //获取SharedPreferences对象
        SharedPreferences sharedPre=context.getSharedPreferences("config", context.MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor=sharedPre.edit();
        //设置参数
        editor.putString("username", username);
        editor.putString("password", password);
        //提交
        editor.commit();
    }
}
