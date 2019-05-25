package com.xiangyueta.two;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiangyueta.two.chat.voice.AudioFileFunc;
import com.xiangyueta.two.chat.voice.AudioRecordFunc;
import com.xiangyueta.two.chat.voice.ErrorCode;
import com.xiangyueta.two.chat.voice.MediaRecordFunc;
import com.xiangyueta.two.util.PermisstionUtil;

public class TestMainActivity extends Activity {


    private final static int FLAG_WAV = 0;
    private final static int FLAG_AMR = 1;
    private int mState = -1;    //-1:没再录制，0：录制wav，1：录制amr
    private Button btn_record_wav;
    private Button btn_record_amr;
    private Button btn_stop;
    private TextView txt;
    private UIHandler uiHandler;
    private UIThread uiThread;
    //申请录音权限
    private static final int GET_RECODE_AUDIO = 1;

    int REQUEST_RECORD_PERMISSION=0;
    private boolean isRequireCheck; // 是否需要系统权限检测

    private static final int PERMISSION_REQUEST_CODE = 0;        // 系统权限返回码
    private static final String PACKAGE_URL_SCHEME = "package:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        isRequireCheck = true;
        findViewByIds();
        setListeners();
        init();
    }




    private void findViewByIds() {
        btn_record_wav = (Button) this.findViewById(R.id.btn_record_wav);
        btn_record_amr = (Button) this.findViewById(R.id.btn_record_amr);
        btn_stop = (Button) this.findViewById(R.id.btn_stop);
        txt = (TextView) this.findViewById(R.id.text);
    }

    private void setListeners() {
        btn_record_wav.setOnClickListener(btn_record_wav_clickListener);
        btn_record_amr.setOnClickListener(btn_record_amr_clickListener);
        btn_stop.setOnClickListener(btn_stop_clickListener);
    }

    private void init() {
        uiHandler = new UIHandler();
    }

    private Button.OnClickListener btn_record_wav_clickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            checkPermission(FLAG_WAV);
        }
    };
    private Button.OnClickListener btn_record_amr_clickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            checkPermission(FLAG_AMR);
        }
    };
    private Button.OnClickListener btn_stop_clickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            stop();
        }
    };

    private void checkPermission(int recordType){
        //如果有权限直接执行
                        if(ContextCompat.checkSelfPermission(TestMainActivity.this, Manifest.permission.RECORD_AUDIO)==PackageManager.PERMISSION_GRANTED&&
                                ContextCompat.checkSelfPermission(TestMainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                            record(recordType);
                            }
                        //如果没有权限那么申请权限
                        else{
                            if(ContextCompat.checkSelfPermission(TestMainActivity.this, Manifest.permission.RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(TestMainActivity.this, new String[]{android.Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_PERMISSION);
                            }
                            if(ContextCompat.checkSelfPermission(TestMainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(TestMainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_RECORD_PERMISSION);
                            }

                     }
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        return super.checkPermission(permission, pid, uid);
    }

    /**
     * 开始录音
     *
     * @param mFlag，0：录制wav格式，1：录音amr格式
     */
    private void record(int mFlag) {
        if (mState != -1) {
            Message msg = new Message();
            Bundle b = new Bundle();// 存放数据
            b.putInt("cmd", CMD_RECORDFAIL);
            b.putInt("msg", ErrorCode.E_STATE_RECODING);
            msg.setData(b);

            uiHandler.sendMessage(msg); // 向Handler发送消息,更新UI
            return;
        }
        int mResult = -1;
        switch (mFlag) {
            case FLAG_WAV:
                AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance();
                mResult = mRecord_1.startRecordAndFile();
                break;
            case FLAG_AMR:
                MediaRecordFunc mRecord_2 = MediaRecordFunc.getInstance();
                mResult = mRecord_2.startRecordAndFile();
                break;
        }
        if (mResult == ErrorCode.SUCCESS) {
            uiThread = new UIThread();
            new Thread(uiThread).start();
            mState = mFlag;
        } else {
            Message msg = new Message();
            Bundle b = new Bundle();// 存放数据
            b.putInt("cmd", CMD_RECORDFAIL);
            b.putInt("msg", mResult);
            msg.setData(b);

            uiHandler.sendMessage(msg); // 向Handler发送消息,更新UI
        }
    }

    /**
     * 停止录音
     */
    private void stop() {
        if (mState != -1) {
            switch (mState) {
                case FLAG_WAV:
                    AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance();
                    mRecord_1.stopRecordAndFile();
                    break;
                case FLAG_AMR:
                    MediaRecordFunc mRecord_2 = MediaRecordFunc.getInstance();
                    mRecord_2.stopRecordAndFile();
                    break;
            }
            if (uiThread != null) {
                uiThread.stopThread();
            }
            if (uiHandler != null)
                uiHandler.removeCallbacks(uiThread);
            Message msg = new Message();
            Bundle b = new Bundle();// 存放数据
            b.putInt("cmd", CMD_STOP);
            b.putInt("msg", mState);
            msg.setData(b);
            uiHandler.sendMessageDelayed(msg, 1000); // 向Handler发送消息,更新UI
            mState = -1;
        }
    }

    private final static int CMD_RECORDING_TIME = 2000;
    private final static int CMD_RECORDFAIL = 2001;
    private final static int CMD_STOP = 2002;

    class UIHandler extends Handler {
        public UIHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            Log.d("MyHandler", "handleMessage......");
            super.handleMessage(msg);
            Bundle b = msg.getData();
            int vCmd = b.getInt("cmd");
            switch (vCmd) {
                case CMD_RECORDING_TIME:
                    int vTime = b.getInt("msg");
                    TestMainActivity.this.txt.setText("正在录音中，已录制：" + vTime + " s");
                    break;
                case CMD_RECORDFAIL:
                    int vErrorCode = b.getInt("msg");
                    String vMsg = ErrorCode.getErrorInfo(TestMainActivity.this, vErrorCode);
                    TestMainActivity.this.txt.setText("录音失败：" + vMsg);
                    break;
                case CMD_STOP:
                    int vFileType = b.getInt("msg");
                    switch (vFileType) {
                        case FLAG_WAV:
                            AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance();
                            long mSize = mRecord_1.getRecordFileSize();
                            TestMainActivity.this.txt.setText("录音已停止.录音文件:" + AudioFileFunc.getWavFilePath() + "\n文件大小：" + mSize);
                            break;
                        case FLAG_AMR:
                            MediaRecordFunc mRecord_2 = MediaRecordFunc.getInstance();
                            mSize = mRecord_2.getRecordFileSize();
                            TestMainActivity.this.txt.setText("录音已停止.录音文件:" + AudioFileFunc.getAMRFilePath() + "\n文件大小：" + mSize);
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    ;

    class UIThread implements Runnable {
        int mTimeMill = 0;
        boolean vRun = true;

        public void stopThread() {
            vRun = false;
        }

        public void run() {
            while (vRun) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mTimeMill++;
                Log.d("thread", "mThread........" + mTimeMill);
                Message msg = new Message();
                Bundle b = new Bundle();// 存放数据
                b.putInt("cmd", CMD_RECORDING_TIME);
                b.putInt("msg", mTimeMill);
                msg.setData(b);

                TestMainActivity.this.uiHandler.sendMessage(msg); // 向Handler发送消息,更新UI
            }

        }
    }
}
