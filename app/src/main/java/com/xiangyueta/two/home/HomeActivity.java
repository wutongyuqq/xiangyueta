package com.xiangyueta.two.home;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiangyueta.two.R;
import com.xiangyueta.two.adapter.MyFragmentPagerAdapter;
import com.xiangyueta.two.fragment.FirstFragment;
import com.xiangyueta.two.fragment.FourthFragment;
import com.xiangyueta.two.fragment.SecondFragment;
import com.xiangyueta.two.fragment.ThirdFragment;
import com.xiangyueta.two.support.NavSupport;
import com.xiangyueta.two.support.TabSupport;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/23 0023.
 * 首页
 */
public class HomeActivity extends FragmentActivity implements View.OnClickListener{


    private View indicator;
    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    private int position_two;
    private int position_three;
    private int dWidth;
    private ViewPager mPager;
    private TextView tv_tongcheng,tv_tuijian,tv_guanzhu,tv_xihuan;
    private Resources resource;
    private ViewPager viewpager;
	private LinearLayout dots;
	int currItem = 0;
	private boolean flag = true;
	final ArrayList<View> views = new ArrayList<View>();
	int[] arr = { R.drawable.banner01, R.drawable.banner02, R.drawable.banner03 };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);


        tv_tongcheng = (TextView) findViewById(R.id.tv_tongcheng);
        tv_tuijian = (TextView) findViewById(R.id.tv_tuijian);
        tv_guanzhu = (TextView) findViewById(R.id.tv_guanzhu);
        tv_xihuan = (TextView) findViewById(R.id.tv_xihuan);
      
        indicator = findViewById(R.id.iv_bottom_line);
        


        tv_tongcheng.setOnClickListener(new MyOnClickListener(0));
        tv_tuijian.setOnClickListener(new MyOnClickListener(1));
        tv_guanzhu.setOnClickListener(new MyOnClickListener(2));
        tv_xihuan.setOnClickListener(new MyOnClickListener(3));
        resource = getResources();
        
        viewpager = (ViewPager)findViewById(R.id.vImagePager);
		dots = (LinearLayout)findViewById(R.id.dots);
		new TabSupport(this,1);
		new NavSupport(this,1);
		showViewPager();
        initViewPager();
    }
   


    @Override
    public void onClick(View v) {

    }

    ArrayList<Fragment> fragmentsList;

    private void initViewPager() {
    	 int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
    	 int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
    	 tv_guanzhu.measure(w, h);
    	 indicator.measure(w, h);
    	
       // int firstToLeft =
        
        bottomLineWidth = indicator.getMeasuredWidth();
        //offset = (int) ((screenW / 2.0 - bottomLineWidth) / 2);
        //offset = tv_guanzhu.getLeft()+ tv_guanzhu.getWidth()/2;
        int[] location = new int[2];  
        tv_guanzhu.getLocationOnScreen(location); 
        int x = location[0];
        int[] location2 = new int[2];
        tv_tongcheng.getLocationOnScreen(location2); 
        int x2 = location2[0];
        dWidth = x2-x;
        offset = x;
        position_one = offset;
        position_two = position_one + dWidth;

        mPager = (ViewPager) findViewById(R.id.vPager);
        fragmentsList = new ArrayList<Fragment>();

        Fragment firstFtagment = new FirstFragment();
        Fragment secondFtagment = new SecondFragment();
        Fragment thirdFtagment = new ThirdFragment();
        Fragment fourthFtagment = new FourthFragment();
        fragmentsList.add(firstFtagment);
        fragmentsList.add(secondFtagment);
        fragmentsList.add(thirdFtagment);
        fragmentsList.add(fourthFtagment);
        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
        mPager.setCurrentItem(0);
        Animation animation = new TranslateAnimation(currIndex * dWidth + offset, dWidth, 0, 0);
        currIndex = 0;
        animation.setFillAfter(true);
        animation.setDuration(300);
        indicator.startAnimation(animation);
    }


    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int pos) {
            switch (pos) {
                case 0:
                	tv_tongcheng.setTextColor(resource.getColor(R.color.little_red));
                	tv_tuijian.setTextColor(resource.getColor(R.color.little_grey));
                	tv_guanzhu.setTextColor(resource.getColor(R.color.little_grey));
                	tv_xihuan.setTextColor(resource.getColor(R.color.little_grey));
                    break;
                case 1:
                	tv_tongcheng.setTextColor(resource.getColor(R.color.little_grey));
                	tv_tuijian.setTextColor(resource.getColor(R.color.little_red));
                	tv_guanzhu.setTextColor(resource.getColor(R.color.little_grey));
                	tv_xihuan.setTextColor(resource.getColor(R.color.little_grey));
                    break;
                case 2:
                	tv_tongcheng.setTextColor(resource.getColor(R.color.little_grey));
                	tv_tuijian.setTextColor(resource.getColor(R.color.little_grey));
                	tv_guanzhu.setTextColor(resource.getColor(R.color.little_red));
                	tv_xihuan.setTextColor(resource.getColor(R.color.little_grey));
                	break;
                case 3:
                	tv_tongcheng.setTextColor(resource.getColor(R.color.little_grey));
                	tv_tuijian.setTextColor(resource.getColor(R.color.little_grey));
                	tv_guanzhu.setTextColor(resource.getColor(R.color.little_grey));
                	tv_xihuan.setTextColor(resource.getColor(R.color.little_red));
                	break;
                default:
                    break;
            }
            Animation animation = new TranslateAnimation(currIndex * dWidth + offset, pos * dWidth + offset, 0, 0);
            currIndex = pos;
            animation.setFillAfter(true);
            animation.setDuration(300);
            indicator.startAnimation(animation);
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }
    
    
    public void showViewPager() {
		// --------------------------------------------------------

		for (int i = 0; i < 3; i++) {
			View v = (View) View.inflate(this,
					R.layout.viewpager_item_view, null);
			ImageView image = (ImageView) v.findViewById(R.id.iv_new);
			image.setImageResource(arr[i]);
			views.add(v);
		}
		PagerAdapter pageAdapter = new PagerAdapter() {
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {

			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				position = position % 3;
				if (position < 0) {
					position = views.size() + position;
				}
				View view = views.get(position);
				ViewParent vp = view.getParent();
				if (vp != null) {
					ViewGroup parent = (ViewGroup) vp;
					parent.removeView(view);
				}
				container.addView(view);

				return view;
			}

			@Override
			public boolean isViewFromObject(View view, Object obj) {
				return view == obj;
			}

			@Override
			public int getCount() {
				return Integer.MAX_VALUE;
			}
		};

		final ArrayList<ImageView> allDot = new ArrayList<ImageView>();
		for (int i = 0; i < 3; i++) {
			ImageView child = (ImageView) View.inflate(this,
					R.layout.dot_view, null);
			dots.addView(child);
			allDot.add(child);
		}
		allDot.get(currItem).setSelected(true);

		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			// ��Ӧҳ���л�
			@Override
			public void onPageSelected(int position) {

				position = position % 3;
				allDot.get(currItem).setSelected(false);
				allDot.get(position).setSelected(true);
				currItem = position;

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {


			}
		});
		viewpager.setAdapter(pageAdapter);
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int next = viewpager.getCurrentItem() + 1;
			viewpager.setCurrentItem(next);
		};
	};

	private void startScoll() {
		new Thread() {
			public void run() {
				while (flag) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Message msg = handler.obtainMessage(1);
					handler.sendMessage(msg);
				}

			};
		}.start();
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		flag = false;
	}

	@Override
	public void onResume() {
		super.onResume();
		flag = true;
		startScoll();
	}

}
