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

public class D_register extends BaseTestCase {
	SS sr = new SS();
	Auto bs = new Auto();
	loginbs lg = new loginbs();
	String[] mail_list = {"qq.com" , "163.com" , "126.com" , "yeah.net" , "yahoo.com.cn" , "yahoo.cn" , "sina.com" , "sina.com.cn" , "139.com" , "gmail.com" , "hotmail.com" , "live.com" , "tom.com" , "21cn.com" , "sohu.com" , "189.cn" , "eyou.com" , "wo.com.cn" , "263.net" , "263.net.cn" , "foxmail.com"};
	long num = Math.round(Math.random()*899999999 + 100000000);
	long mail = Math.round(Math.random()*mail_list.length + 0);
	String count = num + "@" + mail_list[(int)mail];
	String nickname = Long.toString(num);
	
	public void test_signin() throws UiObjectNotFoundException, IOException{
		SetTestCase(this.getClass().getName());
		bs.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY);
		sleep(5000);
		bs.clickByText("我的音乐");
		if(lg.checklogin()){
			lg.logoutmyaccont();
		}
		
		sleep(3000);
		UiObject myicon = new UiObject(new UiSelector().className("android.widget.ImageView").instance(3));
		myicon.click();
//		int x = UiDevice.getInstance().getDisplayWidth();
//		int y = UiDevice.getInstance().getDisplayHeight();
//		UiDevice.getInstance().click(x/2, y/3);
		
		sleep(1000);
		//bs.getobgbyclassindex("android.widget.ImageView", 1);
		UiScrollable sol = new UiScrollable(new UiSelector().scrollable(true));
		sol.scrollToEnd(30);
		bs.clickByText("注册多米帐号");
		UiObject mailtext = new UiObject(new UiSelector().className("android.widget.EditText").text("邮箱"));
		UiObject pwtext = new UiObject(new UiSelector().className("android.widget.EditText").index(2));
		mailtext.setText(count);
		pwtext.setText("111111");
		bs.clickByButtonText("提交");
		//assertTrue("Signup fail", bs.checktext("完成注册"));
		//UiObject nickname = new UiObject(new UiSelector().className("android.widget.EditText").text("输入昵称（可选）"));
		//nickname.setText("hengheng545f");
		bs.clickByText("完成");
		sleep(2000);
		//bs.killprocess("com.duomi.android");
		UiDevice.getInstance().pressHome();
	}
	
	
	
	
}
