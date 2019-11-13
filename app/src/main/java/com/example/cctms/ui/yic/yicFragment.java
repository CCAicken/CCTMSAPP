package com.example.cctms.ui.yic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.cctms.IndexActivity;
import com.example.cctms.MainActivity;
import com.example.cctms.R;

public class yicFragment extends Fragment {

    private yicViewModel dashboardViewModel;

    /*异常菜单页面逻辑*/
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(yicViewModel.class);
                View root = inflater.inflate(R.layout.fragment_yic, container, false);

        // Button mbt_zhengd = root.findViewById(R.id.btn_zhengd);
        Button btn_addyic= root.findViewById(R.id.btn_addyic);
        Button btn_search=root.findViewById(R.id.btn_yicsearch);
        btn_addyic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转添加异常页面
                Intent intent = new Intent(getActivity(), addyic.class);
                startActivity(intent);
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转添加异常页面
                Intent intent = new Intent(getActivity(), searchyic.class);
                startActivity(intent);
            }
        });


        return root;
    }
}