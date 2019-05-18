package com.xiangyueta.two.support;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xiangyueta.two.near.NearActivity;
import com.xiangyueta.two.home.HomeActivity;
import com.xiangyueta.two.person.PersonInfoActivity;
import com.xiangyueta.two.R;
import com.xiangyueta.two.msg.MsgListActivity;

public class TabSupport implements View.OnClickListener{

    private Activity mActivity;
    private int mFromType;//1、首页 2、附近  3、消息  4、个人
    private RelativeLayout rl_bttom_01,rl_bottom_02,rl_bottom_03, rl_bottom_04;
    private ImageView iv_one,iv_two,iv_three,iv_four;
    public TabSupport(Activity activity,int fromType){
        this.mActivity = activity;
        this.mFromType = fromType;
        initView();
    }

    private void initView() {
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
        setTabImage();
    }

    public View findViewById(int id){
        return mActivity.findViewById(id);
    }

    private void setTabImage(){
        iv_one.setImageResource(mFromType==1?R.drawable.first_nav_red:R.drawable.first_nav_white);
        iv_two.setImageResource(mFromType==2?R.drawable.btn_discovery_red:R.drawable.btn_discovery_white);
        iv_three.setImageResource(mFromType==3?R.drawable.btn_mail_red:R.drawable.btn_mail_white);
        iv_four.setImageResource(mFromType==4?R.drawable.btn_info_red:R.drawable.btn_info_white);
    }
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rl_bttom_01:
                intent = new Intent(mActivity, HomeActivity.class);
                mActivity.startActivity(intent);
                mActivity.overridePendingTransition(0, 0);
                break;
            /*
             * 消息列表
             * */
            case R.id.rl_bottom_02:
                intent = new Intent(mActivity, NearActivity.class);
                mActivity.startActivity(intent);
                mActivity.overridePendingTransition(0, 0);
                break;
            case R.id.rl_bottom_03:
                intent = new Intent(mActivity, MsgListActivity.class);
                mActivity.startActivity(intent);
                mActivity.overridePendingTransition(0, 0);
                break;

            case R.id.rl_bottom_04:
                intent = new Intent(mActivity, PersonInfoActivity.class);
                mActivity.startActivity(intent);
                mActivity.overridePendingTransition(0, 0);
                break;
        }

    }
}
