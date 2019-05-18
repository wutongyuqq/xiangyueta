package com.xiangyueta.two.person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiangyueta.two.PersonChangeActivity;
import com.xiangyueta.two.PersonSpaceActivity;
import com.xiangyueta.two.R;
import com.xiangyueta.two.support.NavSupport;
import com.xiangyueta.two.support.TabSupport;

/**
 * Created by Administrator on 2017/2/23 0023.
 *
 */
public class PersonInfoActivity extends Activity implements View.OnClickListener{
    LinearLayout person_change_info,person_my_active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_person_info);
        new NavSupport(this,4);
        new TabSupport(this,4);
        person_change_info = (LinearLayout)findViewById(R.id.person_change_info);
        person_my_active = (LinearLayout)findViewById(R.id.person_my_active);
        person_change_info.setOnClickListener(this);
        person_my_active.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.person_change_info:
            	Intent personChangeIntent=new Intent(this,PersonChangeActivity.class);
            	startActivity(personChangeIntent);
            	overridePendingTransition(0, 0);
            	break;
            case R.id.person_my_active:
            	Intent personSpaceIntent=new Intent(this,PersonSpaceActivity.class);
            	startActivity(personSpaceIntent);
            	overridePendingTransition(0, 0);
            	break;
        }

    }
}
