package TestCase;

import java.io.IOException;

import lib.BaseTestCase;
import lib.Auto;
import lib.SS;

import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class D_miyu_music extends BaseTestCase {
	Auto bs = new Auto();
	public void test_checkmiyumic() throws IOException, UiObjectNotFoundException{
		SS sr = new SS();
		SetTestCase(this.getClass().getName());
		bs.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY);
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
		sleep(3000);
		assertTrue("没有进入密语直播",bs.checkText("发言"));
		bs.clickByStartText("点歌");
		bs.checkText("点歌");
	
		UiDevice.getInstance().click(x/4, y/2 );
		sleep(1000);
		
		UiObject clicklist =new UiObject(new UiSelector().className("android.widget.ListView")).getChild(new UiSelector().className("android.widget.RelativeLayout").index(1));
		/*UiScrollable miyutop = new UiScrollable(new UiSelector().className("android.widget.ListView").scrollable(true));
		miyutop.scrollIntoView(clicklist);*/
		clicklist.click(); 
		
		UiObject paimai = new UiObject(new UiSelector().text("排麦"));
		paimai.waitForExists(60000);
		UiDevice.getInstance().pressBack();
		bs.clickByText("已点");
		
		UiObject yidian =new UiObject(new UiSelector().className("android.widget.ListView")).getChild(new UiSelector().className("android.widget.RelativeLayout").index(0)).getChild(new UiSelector().className("android.widget.TextView").index(0));
		String name = yidian.getText();
		Log.d("liliTest", "点歌名称:"+name);
		sleep(1000);
		bs.clickByText(name);
		sleep(10000);
		bs.clickByStartText(name);
		assertTrue("没有弹出取消对话框", bs.checkText("麦序操作"));
		bs.clickByText("取消排麦");
		sleep(2000);
		bs.clickByText("点歌");
		bs.clickByText("已点");
		
		UiObject longhod = new UiObject(new UiSelector().text(name));
		longhod.longClick();
		bs.clickByText("删除");
		//sleep(5000);
		//bs.killprocess("com.duomi.android");
		//bs.exitapplication();
		//sleep(5000);
		UiDevice.getInstance().pressHome();
	}
	
}
