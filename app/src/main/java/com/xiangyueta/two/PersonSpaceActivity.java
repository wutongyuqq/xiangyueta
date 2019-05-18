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
public class PersonSpaceActivity extends Activity implements View.OnClickListener{
    ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.person_space);
        iv_back = (ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_back:
            	finish();
                overridePendingTransition(0, 0);
                break;
            default:
                break;
        }

    }
}
