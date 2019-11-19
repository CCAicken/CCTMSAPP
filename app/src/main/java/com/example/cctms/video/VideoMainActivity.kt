package com.example.cctms.video

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.example.cctms.util.JsonData
import kotlinx.android.synthetic.main.activity_main_video.*
import kotlinx.android.synthetic.main.activity_video_record.mBtnRecord
import okhttp3.*
import java.io.File
import java.io.IOException
import java.util.*


class VideoMainActivity : AppCompatActivity() {
    val REQUEST_VIDEO = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(com.example.cctms.R.layout.activity_main_video)

        mBtnRecord.setOnClickListener {
            rxRequestPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, describe = "相机、存储、录音") {
                startActivityForResult(Intent(this@VideoMainActivity, VideoRecordActivity::class.java), REQUEST_VIDEO)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_VIDEO) {
                var path = data?.getStringExtra("path")
                var imgPath = data?.getStringExtra("imagePath")
                val type = data?.getIntExtra("type",-1)
                if (type == TYPE_VIDEO) {
                    mTvResult.text = "视频地址：\n\r$path \n\r缩略图地址：\n\r$imgPath"
                } else if (type == TYPE_IMAGE) {
                    mTvResult.text = "图片地址：\n\r$imgPath"
                }
                //获取文件
              var file=  File(path);
                Thread(Runnable {
                    try {
                        //parseJSONWithGSON(UploadUtil.getInstance().upload("http://192.168.43.149:8080/CCTMS/fileuploadservlet.do",file).toString())
                        val client = OkHttpClient()
                        val requestBody = MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("file", file.name,
                                        RequestBody.create(MediaType.parse("multipart/form-data"), file))
                                .build()
                        val request = Request.Builder()
                                .header("Authorization", "ClientID" + UUID.randomUUID())
                                .url("http://192.168.43.149:8080/CCTMS/fileuploadservlet.do")
                                .post(requestBody)
                                .build()
                        client.newCall(request).enqueue(object : Callback {
                            override fun onFailure(call: Call, e: IOException) {}
                            @Throws(IOException::class)
                            override fun onResponse(call: Call, response: Response) {
                                if (response.isSuccessful) {//回调的方法执行在子线程。
                                    parseJSONWithGSON(response.body().string())
                                }
                            }
                        })//此处省略回调方法。
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }).start()
            }
        }
    }
    private fun parseJSONWithGSON(jsonData: String) {
        //使用Jastjson
        Log.d("测试",jsonData);
        val res = JSON.parseObject(jsonData, JsonData::class.java)

        if (res==null) {
            Looper.prepare()
            Toast.makeText(this, "登陆失败，请重试", Toast.LENGTH_SHORT).show()
            Looper.loop()
        } else {
            if (res.code == 0) {
                Looper.prepare()
                Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show()
                Looper.loop()
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

            } else {
                Looper.prepare()
                Toast.makeText(this, "登陆失败，请重试", Toast.LENGTH_SHORT).show()
                Looper.loop()
            }

        }
    }



}
