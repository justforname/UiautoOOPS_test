package TestCase;

import java.io.IOException;

import lib.BaseTestCase;
import lib.Auto;
import lib.SS;


import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class D_miyu_screencheck extends BaseTestCase{
	public void test_miyu() throws IOException, UiObjectNotFoundException{
		SS sr = new SS();
		String [] keywords= {"发言", "送礼物", "点歌","公聊","私聊","蜜友","麦序"};
		Auto bs = new Auto();
		UiObject obj;
		SetTestCase(this.getClass().getName());
		bs.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY);
		sleep(1000);
		bs.clickByText("音乐架");
		
		int y = UiDevice.getInstance().getDisplayHeight();
		int x = UiDevice.getInstance().getDisplayWidth();
		
		for(int i=0;i<5;i++){
			UiObject err = new UiObject(new UiSelector().className("android.widget.TextView").text("加载失败，轻触屏幕重试"));
			if(err.exists()){
				UiDevice.getInstance().click(x/2, y/2);
			}else break;
			sleep(2000);
		}
		
		UiScrollable ls = new UiScrollable(new UiSelector().className("android.widget.ListView").scrollable(true));
		ls.scrollTextIntoView("蜜语好声音");
		sleep(1000);
		bs.clickByText("蜜语好声音");
		obj = new UiObject(new UiSelector().text("发言"));
		obj.waitForExists(30000);
		assertTrue("没有进入密语直播",obj.exists());
		assertTrue("Keywordsmiss",bs.checkSomeWords(keywords));
		UiDevice.getInstance().pressBack();
		sleep(1000);
		bs.clickByButtonText("确定");
		UiDevice.getInstance().pressBack();
		//bs.exitapplication();
		UiDevice.getInstance().pressHome();
	}
	
}
