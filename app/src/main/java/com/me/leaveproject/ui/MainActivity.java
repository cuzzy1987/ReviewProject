package com.me.leaveproject.ui;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.me.leaveproject.R;
import com.me.leaveproject.entity.builderMode.BEntity;
import com.me.leaveproject.entity.HungryEntity;
import com.me.leaveproject.entity.LazyEntity;
import com.me.leaveproject.ui.test.SingleTopActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Handler mHandler,mHandler2;
	private TextView msgTv;
	private Button subBtn,chatBtn;
	private static final String TAG ="MainActivity";
	private int currentId = 0;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.navigation_home:
					msgTv.setText(R.string.title_home);
					return true;
				case R.id.navigation_dashboard:
					msgTv.setText(R.string.title_dashboard);
					return true;
				case R.id.navigation_notifications:
					msgTv.setText(R.string.title_notifications);
					return true;
					default:
						break;
			}
			return false;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		msgTv = (TextView) findViewById(R.id.message);
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		findViewById(R.id.chatBtn).setOnClickListener(this);
		findViewById(R.id.subscribeBtn).setOnClickListener(this);
		init();

	}


	private void init() {
		mHandler = new Handler();
		/*mHandler = new Handler();
		new Thread(() ->{
			*//*Looper.prepare();
			mHandler2 = new Handler();*//*
			Message message = new Message();
			message.obj = new DataBean(1,"name");
			Bundle bundle = new Bundle();
			bundle.putString("data","object");
			message.setData(bundle);
			mHandler.sendMessage(message);
		}).start();*/
		System.out.println("timeStamp==> "+System.currentTimeMillis());
		sendNotificationChannel();
//		post();

//		testSingleInstance();

		cleanTask();
	}

	private void cleanTask() {
		BEntity entity = new BEntity.Builder()
				.name("name").age(11).sex(true).build();
	}

	private void testSingleInstance() {

		HungryEntity hungry = HungryEntity.getInstance();
		HungryEntity h = HungryEntity.getInstance();
		System.out.println("hungry hash=> "+hungry.toString()+" h hash=> "+h.toString());

		LazyEntity lazy = LazyEntity.getInstance();
		LazyEntity l = LazyEntity.getInstance();
		System.out.println("lazy hash=> "+ lazy.toString()+" l hash=> "+l.toString());

	}

	private void sendNotificationChannel() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
			String channelId = "chat";
			String channelName = "聊天消息";
			int importance = NotificationManager.IMPORTANCE_HIGH;
			createNotificationChannel(channelId,channelName,importance);

			channelId = "subscribe";
			channelName = "订阅消息";
			importance = NotificationManager.IMPORTANCE_DEFAULT;
			createNotificationChannel(channelId,channelName,importance);
		}
	}

	@TargetApi(Build.VERSION_CODES.O)
	private void createNotificationChannel(String channelId, String channelName, int importance) {
		NotificationChannel channel = new NotificationChannel(channelId,channelName,importance);
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.createNotificationChannel(channel);
	}


	private void post() {

		List list;
//		Optional.ofNullable(list);

		/* 我在子线程中 运行一个耗时操作 */


		/*mHandler = new Handler();
		mHandler.post(()->{
			msgTv.setText("nm$l");
		});*/
		mHandler.postDelayed(() -> {
			System.out.println("-=send notify via handler=-");
			sendChatNotify("3000s");
		},3000);
		mHandler.postDelayed(() -> {
			System.out.println("-=send notify via handler=-");
			sendChatNotify("4000s");
		},4000);

		mHandler.postDelayed(() -> {
			System.out.println("-=send notify via handler=-");
			sendChatNotify("5000s");
		},5000);


		/*msgTv.postDelayed(() -> {
			msgTv.setText("修改我==> 1000ms 后");
		},1000);
*/
		/* 开启一个新线程 */
		System.out.println("-=home=-");
			/*new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						mTextMessage.setText("无法访问");
					}catch (Exception e){
						Log.d(TAG,"=> "+e.getMessage());
					}
				}
				*//* thread().start==> 线程启动并调用run方法
		 * thread().run==> 不会创建新线程 只有在线程运行时调用该方法
		 * *//*
			}).start();*/
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.chatBtn:
				startActivity(new Intent(this,SingleTopActivity.class));
//				sendChatNotify("点击的");
				break;
			case R.id.subscribeBtn:
				sendSubscribeNotify();
				break;
		}
	}

	private void sendSubscribeNotify() {
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new NotificationCompat.Builder(this,"subscribe")
				.setContentTitle("订阅消息")
				.setContentText("请续交月卡")
				.setWhen(System.currentTimeMillis())
				.setSmallIcon(R.mipmap.ic_launcher_round)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
				.setAutoCancel(true)
				.build();
		manager.notify(2,notification);
	}

	/* 如果第一次 */
	private void sendChatNotify(String content) {
		if (!checkNotifyPermission("chat"))return;
		currentId++;
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new NotificationCompat.Builder(this,"chat")
				.setContentTitle("chat")
				.setContentText(content+" how's going ?"+ " 当前Id=> "+currentId)
				.setWhen(System.currentTimeMillis())
				.setSmallIcon(R.mipmap.ic_launcher_round)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
				.setAutoCancel(true)
				.build();
		manager.notify(currentId, notification);


		/*try {
			String time = System.currentTimeMillis() + "";
			System.out.println("id==> " + time.substring(9, time.length()));
			manager.notify(Integer.valueOf(time.substring(9, time.length())), notification);
		}catch (Exception e){
			System.out.println("E==> "+e.getMessage());
		}*/

	}

	private boolean checkNotifyPermission(String channelName){
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
			NotificationChannel channel = notificationManager.getNotificationChannel(channelName);
			if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE){
				Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
				intent.putExtra(Settings.EXTRA_APP_PACKAGE,getPackageName());
				intent.putExtra(Settings.EXTRA_CHANNEL_ID,channel.getId());
				startActivity(intent);
				Toast.makeText(this,"需要您手动操作打开通知",Toast.LENGTH_LONG).show();
				return false;
			}
		}
		return true;
	}
}
