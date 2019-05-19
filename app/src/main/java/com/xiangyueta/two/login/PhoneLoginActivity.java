package com.xiangyueta.two.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiangyueta.two.R;
import com.xiangyueta.two.home.HomeActivity;

public class PhoneLoginActivity extends Activity implements OnClickListener{

    private TextView login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_phone_login);
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

	@Override
	public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                startActivity(new Intent(this,HomeActivity.class));
                break;
            default:
                break;
        }
		
	}
}
