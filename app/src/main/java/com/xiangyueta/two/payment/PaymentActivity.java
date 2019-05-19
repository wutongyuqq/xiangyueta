package com.xiangyueta.two.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiangyueta.two.R;
import com.xiangyueta.two.chat.ChatMsgActivity;

public class PaymentActivity extends Activity implements OnClickListener{

    private LinearLayout phone_register,wx_login,phone_login;
    private TextView send_msg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_payment);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

	@Override
	public void onClick(View v) {
		
	}
}
