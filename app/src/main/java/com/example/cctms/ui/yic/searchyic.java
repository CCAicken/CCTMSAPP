package com.example.cctms.ui.yic;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cctms.R;
import com.example.cctms.model.ReceivingEntity;
import com.example.cctms.ui.home.ReceivingLinearLayout;

import butterknife.ButterKnife;

public class searchyic extends AppCompatActivity {
    private LinearLayout linearLayout;//大布局
    private ReceivingLinearLayout receivingLinearLayout;//小布局
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchyic);

        linearLayout = (LinearLayout) findViewById(R.id.ell_product1);
        linearLayout.removeAllViews();//清除所有的子View（避免重新刷新数据时重复添加）
        for (int i = 1; i <= 10; i++){
            ReceivingEntity receivingEntity = new ReceivingEntity("异常记录名称"+i,"0000000000000"+i,"1000000000"+i,"2019-04-19 15:20:32");
            View view = View.inflate( searchyic.this, R.layout.receiving, null);
            receivingLinearLayout = (ReceivingLinearLayout)view.findViewById(R.id.linearLayout);
            ViewHolder viewHolder = new ViewHolder(view, receivingEntity);
            viewHolder.refreshUI();
            receivingLinearLayout.bindExpandButton((ImageView) view.findViewById(R.id.iv_expand_icon),(TextView) view.findViewById(R.id.tv_expand_icon), R.drawable.zhankai, R.drawable.zhankai);
            receivingLinearLayout.setLimitHeight(280);//设置折叠的临界高度
            linearLayout.addView(view);//添加子条目
        }
    }
    class ViewHolder {
        TextView name;
        TextView logistics_code;
        TextView order_number;
        TextView date;
        ReceivingEntity receivingEntity;

        public ViewHolder(final View view, final ReceivingEntity receivingEntity) {
            ButterKnife.bind(searchyic.this, view);
            this.receivingEntity = receivingEntity;

            name = (TextView) view.findViewById(R.id.name);
            logistics_code = (TextView) view.findViewById(R.id.logistics_code);
            order_number = (TextView) view.findViewById(R.id.order_number);
            date = (TextView) view.findViewById(R.id.date);
        }

        private void refreshUI() {
            name.setText(receivingEntity.getName());
            logistics_code.setText("异常记录编号：" + receivingEntity.getLogistics_code());
            order_number.setText("任务编号：" + receivingEntity.getOrder_number());
            date.setText("时间：" + receivingEntity.getData());
        }
    }
}
