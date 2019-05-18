package com.xiangyueta.two;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/2/23 0023.
 */
public class MainNavRightctivity extends Activity implements View.OnClickListener{
    ImageView iv_left,iv_center;
    LinearLayout ll_01,ll_02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_nav_right);
        iv_left = (ImageView)findViewById(R.id.iv_left);
        ll_01 = (LinearLayout)findViewById(R.id.ll_01);
        ll_02 = (LinearLayout)findViewById(R.id.ll_02);
        iv_center = (ImageView)findViewById(R.id.iv_center);
        iv_left.setOnClickListener(this);
        ll_01.setOnClickListener(this);
        ll_02.setOnClickListener(this);
        iv_center.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_center:
                Intent centerIntent=new Intent(this,MainNavCenterctivity.class);
                startActivity(centerIntent);
                overridePendingTransition(0, 0);
                break;
            case R.id.iv_left:
                Intent leftIntent=new Intent(this,MainNavActivity.class);
                startActivity(leftIntent);
                overridePendingTransition(0, 0);
                break;
            case R.id.ll_01:
            	Intent personChangeIntent=new Intent(this,PersonChangeActivity.class);
            	startActivity(personChangeIntent);
            	overridePendingTransition(0, 0);
            	break;
            case R.id.ll_02:
            	Intent personSpaceIntent=new Intent(this,PersonSpaceActivity.class);
            	startActivity(personSpaceIntent);
            	overridePendingTransition(0, 0);
            	break;
        }

    }
}
