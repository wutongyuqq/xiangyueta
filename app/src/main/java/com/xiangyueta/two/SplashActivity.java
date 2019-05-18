package com.xiangyueta.two;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.xiangyueta.two.entity.Resualt;
import com.xiangyueta.two.home.HomeActivity;
import com.xiangyueta.two.http.AsyncHttp;
import com.xiangyueta.two.util.MyParcel;

/*
* 启动页
* */
public class SplashActivity extends Activity {
    Intent localIntent;
    ImageView image;
    DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
         localIntent=new Intent(this,HomeActivity.class);
         judgeStatu();
    }
    
    private void judgeStatu(){
    	HashMap<String,Object> map = new HashMap<String, Object>();
		MyParcel parm =  new MyParcel();
		map.put("name", 0);
		parm.setMap(map);
		AsyncHttp ah = new AsyncHttp("/Mee/b.php",parm,0) {
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}
			
			@Override
			public void onSuccess(Object reult) {
				int status=0;
				if(reult!=null){
					Gson gson = new Gson();
					String oldReult = reult.toString();
					String newReult = oldReult.substring(1, oldReult.length()-1);
					Resualt resualt = gson.fromJson(newReult, Resualt.class);
						status = resualt.getCode();
				}
				
				switch (status) {
				case 0:
					Toast.makeText(getApplicationContext(), "请求失败,请稍后重试", 0).show();
					break;
				case 1:
					Toast.makeText(getApplicationContext(), "请求成功", 0).show();
					toMain();
					break;
				}
			}
			@Override
			public void onFail(Object reult) {
				//返回的网络数据为空；
				Toast.makeText(getApplicationContext(), "未获取到网络数据", 0).show();
			}
		};
		ah.execute(1000);
	};
	
    private void toMain(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    startActivity(localIntent);
                    finish();
                	//ImageLoader.getInstance().displayImage("http://img2.imgtn.bdimg.com/it/u=676642173,3846669981&fm=23&gp=0.jpg", image);
                }catch (Exception e){
                    e.printStackTrace();

                }
            }
        }).start();

    }
    /**
     * 获取网落图片资源 
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
         
        return bitmap;
         
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
