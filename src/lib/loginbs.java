package lib;

import java.io.IOException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class loginbs extends BaseTestCase {
	
	Auto da = new Auto();
	public boolean checklogin() throws UiObjectNotFoundException{
		UiObject myicon = new UiObject(new UiSelector().className("android.widget.ImageView").instance(3));
		myicon.click();
		if(da.checkText("资料")){
			UiDevice.getInstance().pressBack();
			sleep(1000);
			return true;
		}else{
			UiDevice.getInstance().pressBack();
			sleep(1000);
			return false;
		} 

		
	}
	
	
	public void loginmyaccont(String username, String password, String nickname) throws UiObjectNotFoundException{
		UiObject myicon = new UiObject(new UiSelector().className("android.widget.ImageView").instance(3));
		myicon.click();
		UiObject name = new UiObject(new UiSelector().className("android.widget.EditText").index(0));
		UiObject psw = new UiObject(new UiSelector().className("android.widget.EditText").index(2));
		UiObject ini = new UiObject(new UiSelector().className("android.widget.Button").text("登录"));
		String s = name.getText();
		name.clearTextField();
		for(int i =0; i<s.length();i++){
			UiDevice.getInstance().pressKeyCode(112);
			sleep(80);
		}
		//name.clearTextField();
		name.setText(username);
		psw.setText(password);
		sleep(1000);
		ini.click();
		sleep(5000);
		assertTrue("nickname is missing",da.checkText(nickname));
		
	}
	
	public void logoutmyaccont() throws UiObjectNotFoundException, IOException{
		UiObject myicon = new UiObject(new UiSelector().className("android.widget.ImageView").instance(3));
		myicon.click();
		UiScrollable sol = new UiScrollable(new UiSelector().scrollable(true));
		sol.scrollToEnd(5);
		da.clickByButtonText("退出登录");
		sleep(1000);
		da.killProcess("com.duomi.android");
		sleep(1000);
		da.launchApplication("com.duomi.android", "com.duomi.android.DMLauncher");
		//bs.exitapplication();
	}
	
		
}
