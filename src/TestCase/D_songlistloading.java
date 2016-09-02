package TestCase;

import java.io.IOException;

import lib.BaseTestCase;
import lib.Auto;
import lib.SS;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class D_songlistloading extends BaseTestCase {
	Auto bs = new Auto();
	SS sr = new SS();
	public void test_songlistlloading() throws IOException, UiObjectNotFoundException{
		SetTestCase(this.getClass().getName());
		bs.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY);
		bs.clickByText("音乐架");
		//点击在线歌单
		UiObject pl = new UiObject(new UiSelector().className("android.widget.HorizontalScrollView"));
		pl.getChild(new UiSelector().className("android.widget.ImageView").instance(1)).click();
		if(!bs.checkText("推荐")){
			bs.clickByText("分类");
			bs.clickByText("全部歌单");
		}
		bs.clickByText("最新");
		scrolling();
		bs.clickByText("推荐");
		scrolling();
		bs.clickByText("热门");
		scrolling();
	}
	
	
	public void scrolling() throws UiObjectNotFoundException{
		UiScrollable ll = new UiScrollable(new UiSelector().scrollable(true));
		while(true){
			//ll.swipeUp(100);
			boolean scroll = ll.scrollForward();
			sleep(3000);
			if(!scroll){
				sleep(1000);
				if(!ll.scrollForward())
				break;
				else
				continue;
			}
		}
		
	}
	
}
