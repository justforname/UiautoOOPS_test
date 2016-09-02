package lib;

import java.io.IOException;

import android.widget.ImageButton;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Watcher extends UiAutomatorTestCase {

	/**
	 * 网络错误监听
	 */
	public void watchernetwork() {
		UiDevice.getInstance().registerWatcher("network",
				new UiWatcher() {
			UiObject jiantingerr = new UiObject(new UiSelector().className("android.widget.TextView").text("加载失败，轻触屏幕重试"));

			@Override
			public boolean checkForCondition() {
				// TODO Auto-generated method stub
				System.out.println("Network miss");
				// sleep(3000);
				if (jiantingerr.exists()) {
					System.out.println("Network missing");
					int y = UiDevice.getInstance().getDisplayHeight();
					int x = UiDevice.getInstance().getDisplayWidth();
					UiDevice.getInstance().click(x / 2, y / 2);
					return true;
				}

				return false;
			}
				});
	}
	
	
	
	/**
	 * 监听异常，结束进程
	 */
	public void watchererrorkill() {
		UiDevice.getInstance().registerWatcher("killprocess",
				new UiWatcher() {
					@Override
					public boolean checkForCondition() {
					
						System.out.println("监听器检查函数开始运行");
						String shellcmd = "am force-stop com.duomi.android";
				    	try {
							Runtime.getRuntime().exec(shellcmd);
							UiDevice.getInstance().pressHome();
							return true;
						} catch (IOException e) {
							
							e.printStackTrace();
							System.out.println("监听器条件判断失败");
							return false;
							
						}
						// sleep(3000);
						
					}
				});
	}
	
	
	
	/**
	 * 来电监听，挂断
	 */
	public void watcherAnswerThePhone() {
		UiDevice.getInstance().registerWatcher("answerThePhone",
				new UiWatcher() {

					UiObject jietingObject = new UiObject(new UiSelector()
							.text("下拉接听"));

					@Override
					public boolean checkForCondition() {
						// TODO Auto-generated method stub
						System.out.println("监听器检查函数开始运行-挂电话");
						// sleep(3000);
						if (jietingObject.exists()) {
							System.out.println("监听器条件判断成功--挂电话");
							int y = UiDevice.getInstance().getDisplayHeight();
							int x = UiDevice.getInstance().getDisplayWidth();
							UiDevice.getInstance().swipe(x / 2, y / 2, x / 2,
									10, 10);
							return true;
						}
						System.out.println("监听器条件判断失败--挂电话");
						return false;
					}
				});
	}

	/**
	 * 短信监听
	 */
	public void watcherMms() {
		UiDevice.getInstance().registerWatcher("mms", new UiWatcher() {
			UiObject xx = new UiObject(new UiSelector()
					.className(ImageButton.class.getName()));
			UiObject yiduObject = new UiObject(new UiSelector().text("回复"));

			@Override
			public boolean checkForCondition() {
				System.out.println("监听器开始运行--短信监听");
				// TODO Auto-generated method stub
				if (yiduObject.exists()) {
					System.out.println("监听器条件判断成功--短信监听");
					try {
						xx.click();
					} catch (UiObjectNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}
				System.out.println("监听器条件判断失败--短信监听");
				return false;
			}
		});
	}

	/**
	 * 闹钟监听，取消闹钟
	 */
	public void watcherAlarmClock() {
		UiDevice.getInstance().registerWatcher("alarm", new UiWatcher() {
			UiObject alarmObject = new UiObject(new UiSelector().text("闹钟"));

			@Override
			public boolean checkForCondition() {
				System.out.println("监听器开始运行--闹钟监听");
				// TODO Auto-generated method stub
				if (alarmObject.exists()) {
					System.out.println("监听器条件判断成功--闹钟监听");
					int y = UiDevice.getInstance().getDisplayHeight();
					int x = UiDevice.getInstance().getDisplayWidth();
					UiDevice.getInstance().swipe(x / 2, y / 2, x / 2, 10, 10);
					return true;
				}
				System.out.println("监听器条件判断失败--闹钟监听");
				return false;
			}
		});
	}

}
