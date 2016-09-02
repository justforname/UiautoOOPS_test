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
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class D_scansong extends BaseTestCase {
	SS sr = new SS();
	Auto bs = new Auto();
	public void test_scansong() throws IOException, UiObjectNotFoundException{
		SetTestCase(this.getClass().getName());
		bs.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY);
		bs.clickByText("我的音乐");
		sleep(2000);
		bs.clickByText("本地音乐");
		sleep(2000);
		bs.clickByText("扫描");
		UiObject scan = new UiObject(new UiSelector().textStartsWith("导入"));
		scan.waitForExists(60000);
		UiCollection relis = new UiCollection(new UiSelector().className("android.widget.ListView").instance(1));
		int ls = relis.getChildCount();
		Log.d("liliTest","scan song namber:"+ls);
		assertTrue("scan song null!",ls>1);
		sleep(2000);
		scan.click();
		int x=UiDevice.getInstance().getDisplayWidth();
		int y=UiDevice.getInstance().getDisplayHeight();
		sleep(5000);
		UiDevice.getInstance().click(x/2, y/2);
		sleep(5000);
		//bs.killprocess("com.duomi.android");
		UiDevice.getInstance().pressHome();
	}
}
