package com.example.smartclass.util;

import android.support.design.widget.TabLayout;
import android.view.View;

import java.util.List;

/**
 * Created by YangFan
 * On 2019/3/5
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class TabLayoutUnderLineChangeUtil {

    public static void changeTabLayoutUnderLineView(TabLayout tabLayout, final List<View> underLineList){

        for(View view: underLineList){
            view.bringToFront();
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();
                underLineList.get(position).setVisibility(View.VISIBLE);

                for(int i = 0; i < underLineList.size(); i++){
                    if(position != i){
                        underLineList.get(i).setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
