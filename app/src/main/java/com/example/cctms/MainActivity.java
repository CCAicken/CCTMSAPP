package com.example.cctms;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cctms.service.MyService;
import com.example.cctms.util.GPStool;
import com.example.cctms.util.HttpUtil;
import com.example.cctms.util.PermissionUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static final int REQUEST_PERMISSION_CODE = 12;

    String url="http://192.168.150.2:8080/AONT/user/stulogin";
    /*登录页面逻辑*/
    private Button btnLogin;
    private EditText username,userpassword;
    private GPStool gpstool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!PermissionUtils.hasPermissions(this, permissions)) {
            PermissionUtils.requestPermissions(this, REQUEST_PERMISSION_CODE, permissions);
        } else {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
        }
        btnLogin = findViewById(R.id.btn_login);
        username=findViewById(R.id.et_1);
        userpassword=findViewById(R.id.et_2);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gpstool.isOPen(MainActivity.this)){
                    //Toast.makeText(MainActivity.this,"密码：保存成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,IndexActivity.class);
                    startActivity(intent);
                }else{
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setTitle("提示")
                            .setMessage("请开启GPS开启定位")
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivityForResult(intent,887);
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                    //MainActivity
                    //Toast.makeText(MainActivity.this,"未打开gps",Toast.LENGTH_SHORT).show();
                }

                //url+="stuId="+stuId+"stuPwd"+stuPwd;
                //sendRequestWithOkHttp();
                //Intent intent = new Intent(MainActivity.this,IndexActivity.class);
                //startActivity(intent);

            }
        });
    }
    private void sendRequestWithOkHttp() {

        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("stuId",username.getText().toString());//传递键值对参数
        formBody.add("stuPwd",userpassword.getText().toString());
        Request request = new Request.Builder()//创建Request 对象。
                .url(url)
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){//回调的方法执行在子线程。
                    HttpUtil.parseJSONWithGSON(response.body().string(),MainActivity.this,"登陆成功","登陆失败，请重试！");
                }
            }});//此处省略回调方法。
    }

}
