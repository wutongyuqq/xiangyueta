package com.xiangyueta.two;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/2/23 0023.
 */
public class MainNavCenterctivity extends Activity  implements View.OnClickListener{
    ImageView iv_left,iv_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_nav_center);
        iv_left = (ImageView)findViewById(R.id.iv_left);
        iv_right = (ImageView)findViewById(R.id.iv_right);
        iv_left.setOnClickListener(this);
        iv_right.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_left:
                Intent leftIntent = new Intent(this, MainNavActivity.class);
                startActivity(leftIntent);
                overridePendingTransition(0, 0);
                break;
            case R.id.iv_right:
                Intent rightIntent = new Intent(this, MainNavRightctivity.class);
                startActivity(rightIntent);
                overridePendingTransition(0, 0);
                break;
        }
    }
}
