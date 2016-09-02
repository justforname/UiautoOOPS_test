package TestCase;

import java.io.IOException;

import android.util.Log;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

import lib.Auto;
import lib.BaseTestCase;
import lib.Oops;
import lib.SS;

public class OOPS_ZhizuohaibaoTest extends BaseTestCase{
	SS sr = new SS();
	Oops op = new Oops();
	public void test_zhizuohaibao() throws IOException, UiObjectNotFoundException{
		Auto.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY);
		op.zhizuoHaiBao(1, "TestTest", "testtesttesttest");
		
	}
}