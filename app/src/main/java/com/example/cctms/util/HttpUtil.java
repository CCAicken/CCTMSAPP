package com.example.cctms.util;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(final String address,final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void parseJSONWithGSON(String jsonData, Context context,String strsucc,String strfalie) {
        //使用Jastjson
        JsonData res= JSON.parseObject(jsonData, JsonData.class);
        boolean flag;

        if(res==null){
            Looper.prepare();
            Toast.makeText(context,strfalie,Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
        else{
            if(res.code==0){
                Looper.prepare();
                Toast.makeText(context,strsucc,Toast.LENGTH_SHORT).show();
                Looper.loop();
//                //TextView username=this.findViewById(R.id.wode_username );
//                //Log.d("MainActivity",res.data.toString());
//                Tuser student=JSON.parseObject(res.data.toString(), Tuser.class);
//                //  Log.d("MainActivity",student.getStuName());
//                MyApplication appstu= (MyApplication)this.getApplication();
//                appstu.setStuId(student.getStuId());
//                appstu.setClassId(student.getClassId());
//                appstu.setAgend(student.getAgend());
//                appstu.setCreateTime(student.getCreateTime());
//                appstu.setStuName(student.getStuName());

            }else{
                Looper.prepare();
                Toast.makeText(context,strfalie,Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

        }
    }
}
