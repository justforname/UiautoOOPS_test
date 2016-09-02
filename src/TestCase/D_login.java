package TestCase;

import java.io.IOException;

import lib.BaseTestCase;
import lib.Auto;
import lib.SS;
import lib.loginbs;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class D_login extends BaseTestCase {
	SS sr = new SS();
	Auto bs = new Auto();
	loginbs logbs = new loginbs();
	public void test_logind() throws IOException, UiObjectNotFoundException{
		SetTestCase(this.getClass().getName());
		bs.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY);
		bs.checkText("我的音乐");
		sleep(1000);
		if(logbs.checklogin()){
			logbs.logoutmyaccont();
		}
		sleep(2000);
		logbs.loginmyaccont("heheheihei@qq.com","123456", "lniq");
		sleep(1000);
		//bs.killprocess("com.duomi.android");
		UiScrollable li = new UiScrollable(new UiSelector().scrollable(true));
		UiDevice.getInstance().pressHome();
	}

}
