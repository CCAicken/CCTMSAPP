package com.example.cctms.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.fastjson.JSON;
import com.example.cctms.R;
import com.example.cctms.model.Vlinearrange;
import com.example.cctms.util.HttpUtil;
import com.example.cctms.util.JsonData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class homeFragment extends Fragment {

//    菜单主页模块
    private homeViewModel homeViewModel;
    private LinearLayout linearLayout;//大布局
    private ReceivingLinearLayout receivingLinearLayout;//小布局
    private    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(com.example.cctms.ui.home.homeViewModel.class);
         root = inflater.inflate(R.layout.fragment_home, container, false);

        linearLayout =root.findViewById(R.id.ell_product);
        linearLayout.removeAllViews();//清除所有的子View（避免重新刷新数据时重复添加）
        List<Vlinearrange> listvlinearrange=new ArrayList<Vlinearrange>();
        for (int i = 0; i <10 ; i++) {
            Vlinearrange vlinearrange=new Vlinearrange();
            vlinearrange.setTaskname("昆明跑曲靖"+i);
            vlinearrange.setUserName("里斯"+i);
            vlinearrange.setCarNum("云A8952_"+i);
            vlinearrange.setLineRemarks("测试数据"+i);
            listvlinearrange.add(vlinearrange);
        }
        for (Vlinearrange vlinearrange:listvlinearrange){
            //ReceivingEntity receivingEntity = new ReceivingEntity("任务名称"+i,"0000000000000"+i,"1000000000"+i,"2019-04-19 15:20:32");

            View view = inflater.inflate(R.layout.linetask, null);
            receivingLinearLayout = (ReceivingLinearLayout)view.findViewById(R.id.linearLayout);
            ViewHolder viewHolder = new ViewHolder(view,vlinearrange);
            viewHolder.refreshUI();
            receivingLinearLayout.bindExpandButton((ImageView) view.findViewById(R.id.iv_expand_icon),(TextView) view.findViewById(R.id.tv_expand_icon), R.drawable.zhankai, R.drawable.zhankai_up);
            receivingLinearLayout.setLimitHeight(280);//设置折叠的临界高度
            linearLayout.addView(view);//添加子条目
        }

        return root;
    }

    private void sendRequestWithOkHttp() {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid","jj");//传递键值对参数
        Request request = new Request.Builder()//创建Request 对象。
                .url("")
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){//回调的方法执行在子线程。
                    HttpUtil.parseJSONWithGSON(response.body().string(), getActivity(),"登陆成功","登陆失败，请重试！");
                }
            }});//此处省略回调方法。
    }

    public static void parseJSONWithGSON(String jsonData, Context context, String strsucc, String strfalie) {
        //使用Jastjson
        JsonData res= JSON.parseObject(jsonData, JsonData.class);
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

    class ViewHolder {
        TextView tv_taskname;
        TextView tv_username;
        TextView tv_taskcar;
        TextView tv_taskremark;

        Vlinearrange vlinearrange;

        public ViewHolder(final View view, final Vlinearrange vlinearrange) {
            ButterKnife.bind(root, view);
            this.vlinearrange = vlinearrange;

            tv_taskname = (TextView) view.findViewById(R.id.tv_taskname);
            tv_username = (TextView) view.findViewById(R.id.tv_username);
            tv_taskcar = (TextView) view.findViewById(R.id.tv_taskcar);
            tv_taskremark = (TextView) view.findViewById(R.id.tv_taskremark);
        }

        private void refreshUI() {
            tv_taskname.setText(vlinearrange.getTaskname());
            tv_username.setText("运输人员：" + vlinearrange.getUserName());
            tv_taskcar.setText("冷链车号牌：" + vlinearrange.getCarNum());
            tv_taskremark.setText("备注：" + vlinearrange.getLineRemarks());
        }
    }
}