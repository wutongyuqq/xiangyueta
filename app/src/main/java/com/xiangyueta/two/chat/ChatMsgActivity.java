package com.xiangyueta.two.chat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangyueta.two.R;
import com.xiangyueta.two.persondetail.PersonDetailActivity;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

public class ChatMsgActivity extends Activity implements OnClickListener {

    TextView title;
    private EditText mEdit;
    private RecyclerView mRecycler;
    ChatMsgAdapter mAdapter;
    private int position;
    private String userName;
    private Conversation conversation;
    private boolean one;
    private ImageView jg_details_img;
    //调用系统相册-选择图片
    private static final int IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chat_new);
        title = findViewById(R.id.jg_details_title);
        mEdit = findViewById(R.id.jg_details_edit);
        jg_details_img = findViewById(R.id.jg_details_img);
        mRecycler = findViewById(R.id.jg_details_recy);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ChatMsgAdapter(this);
        mRecycler.setAdapter(mAdapter);
        jg_details_img.setOnClickListener(this);

        position = getIntent().getIntExtra("position", 0);
        //设置消息接收 监听
        GlobalEventListener.setJG(this, false);
        Conversation.createSingleConversation("10033", "365bf6e6f5be7f76e61a6b59");
        //进入会话状态,不接收通知栏
        JMessageClient.enterSingleConversation("10033");
        initData();

    }

    //发送消息
    private void sendMsg(Message message) {
        //Message message = JMessageClient.createSingleTextMessage("","");
        message.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseDesc) {
                if (responseCode == 0) {
                    // 消息发送成功
                    initData();
                } else {
                    // 消息发送失败
                }
            }
        });
        JMessageClient.sendMessage(message);    // 之后再调用发送消息 API
    }


    public void initData() {
        List<Conversation> msgList = JMessageClient.getConversationList();
        if (msgList != null) {
            if (msgList.size() > 0) {
                if (msgList.get(position) != null) {
                    conversation = msgList.get(position);
                    //重置会话未读消息数
                    conversation.resetUnreadCount();

                }
            }
        }

        if (conversation != null) {
            title.setText(conversation.getTitle() == null ? "" : conversation.getTitle());
            UserInfo info = (UserInfo) conversation.getTargetInfo();
            userName = info.getUserName();
            //userName = "f8443445-a7ef-47d8-8005-b0d57851b396";  //todo 可自定义

            //使列表滚动到底部
            if (conversation.getAllMessage() != null) {
                if (conversation.getAllMessage().size() > 0) {
                    mAdapter.setData(conversation.getAllMessage());
                    //设置刷新不闪屏
                    ((SimpleItemAnimator) mRecycler.getItemAnimator()).setSupportsChangeAnimations(false);
                    if (one) {
                        mAdapter.notifyDataSetChanged();
                    } else {
                        mAdapter.notifyItemInserted(conversation.getAllMessage().size() - 1);

                    }
                    mRecycler.scrollToPosition(conversation.getAllMessage().size() - 1);

                }
            }
            mAdapter.setOnItemClickListener(new ChatMsgAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    switch (view.getId()) {
                        case R.id.item_jg_details_img:
                            ImageContent imageContent = (ImageContent) conversation.getAllMessage().get(position).getContent();
                            startActivity(new Intent(ChatMsgActivity.this, PersonDetailActivity.class)
                                    .putExtra("ImgUrl", imageContent.getLocalThumbnailPath()));
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);//动画
                            break;
                    }
                }
            });
        }
        one = false; // 代表不是第一次initData
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.jg_details_img:
                if (mEdit.getText() != null && !TextUtils.isEmpty(mEdit.getText().toString())) {
                    //Conversation.createSingleConversation(String username, String appkey);

                    Message message = JMessageClient.createSingleTextMessage("10033", "365bf6e6f5be7f76e61a6b59", mEdit.getText().toString());
                    if (message != null) {
                        sendMsg(message);
                    }
                }
                break;
            default:
                break;
        }
    }


    public void onClickImage(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imaePath) {
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        ((ImageView) findViewById(R.id.image)).setImageBitmap(bm);
    }


    @Override
    protected void onDestroy() {
        //退出会话界面 (开始接收通知栏)
        JMessageClient.exitConversation();
        //设置消息接收 监听
        GlobalEventListener.setJG(null, false);
        super.onDestroy();
    }


}

