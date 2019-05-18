package com.xiangyueta.two.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiangyueta.two.R;

public class LoginActivity extends Activity implements OnClickListener{

    private LinearLayout phone_register,wx_login,phone_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        phone_register = findViewById(R.id.phone_register);
        wx_login = findViewById(R.id.wx_login);
        phone_login = findViewById(R.id.phone_login);

        phone_register.setOnClickListener(this);
        wx_login.setOnClickListener(this);
        phone_login.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

	@Override
	public void onClick(View v) {
        switch (v.getId()){
            case R.id.phone_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            default:
                break;
        }
		
	}
}
