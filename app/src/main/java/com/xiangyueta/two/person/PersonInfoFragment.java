package com.xiangyueta.two.person;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xiangyueta.two.PersonChangeActivity;
import com.xiangyueta.two.PersonSpaceActivity;
import com.xiangyueta.two.R;
import com.xiangyueta.two.fragment.BaseFragment;
import com.xiangyueta.two.watch.WatchListActivity;

/**
 * Created by Administrator on 2017/2/23 0023.
 *
 */
public class PersonInfoFragment extends BaseFragment implements View.OnClickListener{
    LinearLayout person_change_info,person_my_active;
    RelativeLayout vip_pay,custom_center_btn,curstom_btn;
    private View mView;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = View.inflate(getActivity(), R.layout.fragment_person_info, null);
        person_change_info = (LinearLayout)findViewById(R.id.person_change_info);
        person_my_active = (LinearLayout)findViewById(R.id.person_my_active);
        custom_center_btn = (RelativeLayout)findViewById(R.id.custom_center_btn);
        curstom_btn = (RelativeLayout)findViewById(R.id.curstom_btn);
        vip_pay = (RelativeLayout)findViewById(R.id.vip_pay);
        person_change_info.setOnClickListener(this);
        person_my_active.setOnClickListener(this);
        vip_pay.setOnClickListener(this);
        custom_center_btn.setOnClickListener(this);
        curstom_btn.setOnClickListener(this);

        return mView;
    }


    private View findViewById(int id){
        return mView.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.person_change_info:
            	Intent personChangeIntent=new Intent(getActivity(),PersonChangeActivity.class);
            	startActivity(personChangeIntent);
                getActivity().overridePendingTransition(0, 0);
            	break;
            case R.id.person_my_active:
            	Intent personSpaceIntent=new Intent(getActivity(),PersonSpaceActivity.class);
            	startActivity(personSpaceIntent);
                getActivity().overridePendingTransition(0, 0);
            	break;
            case R.id.vip_pay:
                getActivity().startActivity(new Intent(getActivity(),PersonVipActivity.class));
                break;
            case R.id.custom_center_btn:
                getActivity().startActivity(new Intent(getActivity(),CustormServerActivity.class));
                break;
            case R.id.curstom_btn:
                getActivity().startActivity(new Intent(getActivity(),WatchListActivity.class));
                break;
            default:
                break;
        }

    }
}
