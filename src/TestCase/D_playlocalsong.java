package TestCase;

import java.io.IOException;

import lib.BaseTestCase;
import lib.Auto;
import lib.SS;

import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class D_playlocalsong extends BaseTestCase {
	SS sr = new SS();
	Auto bs = new Auto();
	public void test_playsong() throws IOException, UiObjectNotFoundException{
		SetTestCase(this.getClass().getName());
		bs.launchApplication(sr.APPPACKAGE,sr.APPACTIVITY);
		bs.clickByText("本地音乐");
		sleep(2000);
		bs.clickByText("随机播放全部");
		sleep(2000);
		int x1 = UiDevice.getInstance().getDisplayWidth();
		int y1 = UiDevice.getInstance().getDisplayHeight();
		UiDevice.getInstance().click(50, y1-50); //进入播放界面
		UiObject seek = new UiObject(new UiSelector().className("android.widget.SeekBar"));
		//assertTrue("seekbar not enable",seek.isEnabled());
		UiObject songtime = new UiObject(new UiSelector().className("android.widget.TextView").instance(4));
		sleep(2000);
		assertTrue("Song time fail",!"00:00".equals(songtime));
		seek.swipeLeft(30);
		sleep(2000);
		seek.swipeRight(20);
		sleep(3000);
		UiObject nextsong = new UiObject(new UiSelector().className("android.widget.ImageButton").instance(7));
		for(int i=0;i<5;i++){
			//getUiDevice().pressKeyCode(87);
			nextsong.click();
			sleep(6000);
		}
		
		//bs.killprocess("com.duomi.android");
		UiDevice.getInstance().pressHome();
	}
	
}
