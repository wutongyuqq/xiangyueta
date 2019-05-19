package com.xiangyueta.two.person;

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
import com.xiangyueta.two.payment.PaymentActivity;

public class PersonVipActivity extends Activity implements OnClickListener{

    private LinearLayout seven_day_pay,one_mounth_pay,three_mounth_pay,one_year_pay;
    private TextView send_msg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_person_vip);
        seven_day_pay = findViewById(R.id.seven_day_pay);
        one_mounth_pay = findViewById(R.id.one_mounth_pay);
        three_mounth_pay = findViewById(R.id.three_mounth_pay);
        one_year_pay = findViewById(R.id.one_year_pay);
        seven_day_pay.setOnClickListener(this);
        one_mounth_pay.setOnClickListener(this);
        three_mounth_pay.setOnClickListener(this);
        one_year_pay.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

	@Override
	public void onClick(View v) {
        switch (v.getId()){
            case R.id.seven_day_pay:
                startActivity(new Intent(PersonVipActivity.this,PaymentActivity.class));
                break;
            case R.id.one_mounth_pay:
                startActivity(new Intent(PersonVipActivity.this,PaymentActivity.class));
                break;
            case R.id.three_mounth_pay:
                startActivity(new Intent(PersonVipActivity.this,PaymentActivity.class));
                break;
            case R.id.one_year_pay:
                startActivity(new Intent(PersonVipActivity.this,PaymentActivity.class));
                break;
            default:
                break;
        }
		
	}
}
