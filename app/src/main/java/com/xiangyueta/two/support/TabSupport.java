package com.xiangyueta.two.support;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.xiangyueta.two.home.HomeFragment;
import com.xiangyueta.two.home.HomeActivity;
import com.xiangyueta.two.near.NearFragment;
import com.xiangyueta.two.R;
import com.xiangyueta.two.msg.MsgListFragment;
import com.xiangyueta.two.person.PersonInfoFragment;

import java.util.ArrayList;
import java.util.List;

public class TabSupport implements View.OnClickListener{

    private Activity mActivity;
    private int mFromType=1;//1、首页 2、附近  3、消息  4、个人
    private RelativeLayout rl_bttom_01,rl_bottom_02,rl_bottom_03, rl_bottom_04;
    private ImageView iv_one,iv_two,iv_three,iv_four;
    private List<Fragment> fragmentList;
    public TabSupport(Activity activity){
        this.mActivity = activity;
        initView();
    }

    public void setFromType(int fromType){
        this.mFromType = fromType;
    }

    private void initView() {
        fragmentList = new ArrayList<>(4);
        fragmentList.add(new HomeFragment());
        fragmentList.add(new NearFragment());
        fragmentList.add(new MsgListFragment());
        fragmentList.add(new PersonInfoFragment());
        rl_bttom_01 = (RelativeLayout) findViewById(R.id.rl_bttom_01);
        rl_bottom_03 = (RelativeLayout) findViewById(R.id.rl_bottom_03);
        rl_bottom_02 = (RelativeLayout) findViewById(R.id.rl_bottom_02);
        rl_bottom_04 = (RelativeLayout) findViewById(R.id.rl_bottom_04);
        iv_one = (ImageView) findViewById(R.id.iv_one);
        iv_two = (ImageView) findViewById(R.id.iv_two);
        iv_three = (ImageView) findViewById(R.id.iv_three);
        iv_four = (ImageView) findViewById(R.id.iv_four);
        rl_bttom_01.setOnClickListener(this);
        rl_bottom_02.setOnClickListener(this);
        rl_bottom_03.setOnClickListener(this);
        rl_bottom_04.setOnClickListener(this);

    }

    public View findViewById(int id){
        return mActivity.findViewById(id);
    }

    private void setTabImage(int fromType){
        mFromType = fromType;
        iv_one.setImageResource(mFromType==1?R.drawable.first_nav_red:R.drawable.first_nav_white);
        iv_two.setImageResource(mFromType==2?R.drawable.btn_discovery_red:R.drawable.btn_discovery_white);
        iv_three.setImageResource(mFromType==3?R.drawable.btn_mail_red:R.drawable.btn_mail_white);
        iv_four.setImageResource(mFromType==4?R.drawable.btn_info_red:R.drawable.btn_info_white);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_bttom_01:
                setTabImage(1);
                ((HomeActivity)mActivity).getSupportFragmentManager()    //
                        .beginTransaction()
                        .replace(R.id.fragment_tab,fragmentList.get(0))   // 此处的R.id.fragment_container是要盛放fragment的父容器
                        .commit();
                break;
            /*
             * 消息列表
             * */
            case R.id.rl_bottom_02:
                setTabImage(2);
                ((HomeActivity)mActivity).getSupportFragmentManager()    //
                        .beginTransaction()
                        .replace(R.id.fragment_tab,fragmentList.get(1))   // 此处的R.id.fragment_container是要盛放fragment的父容器
                        .commit();
                break;
            case R.id.rl_bottom_03:
                setTabImage(3);
                ((HomeActivity)mActivity).getSupportFragmentManager()    //
                        .beginTransaction()
                        .replace(R.id.fragment_tab,fragmentList.get(2))   // 此处的R.id.fragment_container是要盛放fragment的父容器
                        .commit();
                break;

            case R.id.rl_bottom_04:
                setTabImage(4);
                ((HomeActivity)mActivity).getSupportFragmentManager()    //
                        .beginTransaction()
                        .replace(R.id.fragment_tab,fragmentList.get(3))   // 此处的R.id.fragment_container是要盛放fragment的父容器
                        .commit();
                break;
        }

    }
}
