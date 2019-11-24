package com.example.cctms.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.cctms.R;
import com.example.cctms.model.ReceivingEntity;

import butterknife.ButterKnife;


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
        for (int i = 1; i <= 10; i++){
            ReceivingEntity receivingEntity = new ReceivingEntity("任务名称"+i,"0000000000000"+i,"1000000000"+i,"2019-04-19 15:20:32");

            View view = inflater.inflate(R.layout.receiving, null);
            receivingLinearLayout = (ReceivingLinearLayout)view.findViewById(R.id.linearLayout);
            ViewHolder viewHolder = new ViewHolder(view, receivingEntity);
            viewHolder.refreshUI();
            receivingLinearLayout.bindExpandButton((ImageView) view.findViewById(R.id.iv_expand_icon),(TextView) view.findViewById(R.id.tv_expand_icon), R.drawable.zhankai, R.drawable.zhankai_up);
            receivingLinearLayout.setLimitHeight(280);//设置折叠的临界高度
            linearLayout.addView(view);//添加子条目
        }

        return root;
    }
    class ViewHolder {
        TextView name;
        TextView logistics_code;
        TextView order_number;
        TextView date;
        ReceivingEntity receivingEntity;

        //删除按钮
        ImageView iv_delete;
        public ViewHolder(final View view, final ReceivingEntity receivingEntity) {
            ButterKnife.bind(root, view);
            this.receivingEntity = receivingEntity;

            name = (TextView) view.findViewById(R.id.name);
            logistics_code = (TextView) view.findViewById(R.id.logistics_code);
            order_number = (TextView) view.findViewById(R.id.order_number);
            date = (TextView) view.findViewById(R.id.date);
        }

        private void refreshUI() {
            name.setText(receivingEntity.getName());
            logistics_code.setText("任务编号：" + receivingEntity.getLogistics_code());
            order_number.setText("冷链车号牌：" + receivingEntity.getOrder_number());
            date.setText("时间：" + receivingEntity.getData());
        }
    }
}