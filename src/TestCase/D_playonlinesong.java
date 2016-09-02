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

public class D_playonlinesong extends BaseTestCase {
	SS sr = new SS();
	Auto bs = new Auto();
	public void test_playonlinesong() throws IOException, UiObjectNotFoundException{
		SetTestCase(this.getClass().getName());
		bs.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY);
		bs.clickByText("音乐架");
		UiObject ho = new UiObject(new UiSelector().className(
				"android.widget.HorizontalScrollView").index(0));
		UiObject img = ho.getChild(new UiSelector().className(
				"android.widget.ImageView").instance(0));
		img.click();
		sleep(2000);
		UiObject newsong = new UiObject(new UiSelector().className("android.widget.ListView").index(0));
		UiObject fis = newsong.getChild(new UiSelector().index(1)).getChild(new UiSelector().index(0)).getChild(new UiSelector().index(0));
		fis.click();
		sleep(2000);
		UiObject lis = new UiObject(new UiSelector().className("android.widget.ListView").instance(1));
		UiObject liner = lis.getChild(new UiSelector().className("android.widget.ImageView").instance(1));
		liner.click();
		sleep(2000);
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
