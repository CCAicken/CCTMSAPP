package com.example.cctms.util;


import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 注意：在每一个activity创建时，都加入list管理（）ExitApplication.getInstance().addActivity(this)
 * 退出调用 ExitApplication.getInstance().exit(this);
 * 退出应用程序
 */
public class ExitApplication extends Application {
    //用来存储所有创建的activity
    private List<Activity> list = new ArrayList<Activity>();

    private static ExitApplication exit;

    private ExitApplication() {

    }

    public static ExitApplication getInstance() {
        if (null == exit) {
            exit = new ExitApplication();
        }
        return exit;
    }
    //添加新创建的activity
    public void addActivity(Activity activity) {
        list.add(activity);
    }
    //关闭所有activity，退出程序
    public void exit(Context context) {
        for (Activity activity : list) {
            activity.finish();
        }
        System.exit(0);
    }
}
