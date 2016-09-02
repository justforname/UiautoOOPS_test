package TestCase;

import java.io.IOException;

import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;

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

public class D_search extends BaseTestCase {
	SS sr = new SS();
	Auto bs = new Auto();
	String txname;
	int r =0;
	public void test_search() throws IOException, UiObjectNotFoundException{
		SetTestCase(this.getClass().getName());
		bs.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY);
		bs.clickByText("搜索");
		sleep(2000);
		bs.clickByClassIndex("android.widget.TextView", 0);
		sleep(1000);
		UiObject ed = new UiObject(new UiSelector().className("android.widget.EditText"));
		ed.setText("dream");
		sleep(1000);
		//ed.setText(Utf7ImeHelper.e("ありがとう"));
		UiCollection relist = new UiCollection(new UiSelector().className("android.widget.ListView").instance(1));
		int m = relist.getChildCount();
		
		for(int i=1;i<m;i++){
			UiObject tx = relist.getChild(new UiSelector().className("android.widget.LinearLayout").index(i)).getChild(new UiSelector().className("android.widget.TextView").index(1));
			if(tx.exists()){
				txname = tx.getText();
				r++;
			}
			Log.d("LILITest", "searchlist:"+txname);
		}
		assertTrue("miss search result", r>0);
		bs.clickByButtonText("搜索");
		sleep(3000);
		//bs.killprocess("com.duomi.android");
		UiDevice.getInstance().pressHome();
	}
	
}
