package TestCase;

import java.io.IOException;

import lib.BaseTestCase;
import lib.Auto;
import lib.SS;


import android.util.Log;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class D_markplaylist extends BaseTestCase {
	SS sr = new SS();
	Auto bs = new Auto();
	public void test_markplaylist() throws IOException, UiObjectNotFoundException{
		SetTestCase(this.getClass().getName());
		bs.launchApplication(sr.APPPACKAGE,sr.APPACTIVITY);
		bs.clickByText("音乐架");
		UiObject ho = new UiObject(new UiSelector().className("android.widget.HorizontalScrollView").index(0));
		UiObject img = ho.getChild(new UiSelector().className("android.widget.ImageView").instance(2));
		img.click(); //点击排行榜
		sleep(1000);
		//点击多米新歌榜
		UiCollection llist = new UiCollection(new UiSelector().className("android.widget.ListView").instance(1));
		UiObject lif = llist.getChild(new UiSelector().index(2));
		String name = lif.getChild(new UiSelector().className("android.widget.TextView").index(0)).getText();
		Log.d("liliTest", "Name:"+name);
		//lif.click();
		bs.clickByText(name);
		sleep(1000);
		//点击红心喜欢
//		UiCollection listpag2 = new UiCollection(new UiSelector().className("android.widget.ListView").instance(1));
//		UiObject likeimag = listpag2.getChild(new UiSelector().className("android.widget.ImageView").instance(4));
//		likeimag.click();
		if(bs.checkText("离线")){
			bs.clickByText("更多");
			bs.clickByText("取消订阅");
		}
		bs.clickByText("订阅");
		sleep(800);
		bs.pressBack(2);
		sleep(1000);
		bs.clickByText("我的音乐");
		//滑动到文字
		UiObject p1l = new UiObject(new UiSelector().text(name));
		UiScrollable pag1 = new UiScrollable(new UiSelector().scrollable(true));
		for(int i=0;i<5;i++){
			Log.d("liliTest", "RE:"+name+"__"+p1l.exists());
			if(p1l.exists()){
				p1l.longClick();
				break;
			}
			pag1.swipeUp(200);
			sleep(1000);
		}
//		//pag1.scrollToBeginning(10);
//		pag1.scrollIntoView(new UiSelector().text(name));
		//长按
		
		
		bs.clickByText("取消订阅");
		bs.clickByButtonText("确定");
		assertTrue("UnMark fail!", !bs.checkText(name));
		sleep(1000);
		//bs.killprocess(sr.APPPACKAGE);
		
		
		//bs.getobjbytext("我的音乐");
//		while(true){
//			int l = llist.getChildCount();
//			Log.d("liliTest", ":"+l);
//			if(bs.checktext("创建歌单")){
//				UiObject t = llist.getChild(new UiSelector().className("android.widget.RelativeLayout").index(5));
//				t.longClick();
//				bs.getobjbytext("取消订阅");
//				sleep(900);
//				bs.getobjbybuttontext("确定");
//				sleep(1000);
//			}else break;
//		}
		
		
		UiDevice.getInstance().pressHome();
	}
}
