package TestCase;

import java.io.IOException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

import lib.BaseTestCase;
import lib.Auto;
import lib.SS;
import lib.Image;
import lib.Utility;

public class fwtest extends BaseTestCase {
	Auto au = new Auto();
	SS dc = new SS();
	Image mg = new Image();
	Utility ut = new Utility();
	public void testDemo() throws IOException, UiObjectNotFoundException{
		//Auto.launchApplication(dc.APPPACKAGE, dc.APPACTIVITY);
		//Auto.clickByText("我的音乐");
//		UiObject myicon = new UiObject(new UiSelector().className("android.widget.ImageView").instance(3));
//		myicon.click();
		//Auto.takescreen("loginhome");
		//assertFalse("asdfasdfasdfa", ut.checkScreenshot("home", 20, 20, 100, 100, 0.9));
		
		int x = UiDevice.getInstance().getDisplayHeight();
		int y = UiDevice.getInstance().getDisplayWidth();
		
		while(true){
			
			UiDevice.getInstance().click(x/2, y/2);
			
		}
	}

}
