package TestCase;

import java.io.IOException;

import com.android.uiautomator.core.UiObjectNotFoundException;


import lib.Auto;
import lib.BaseTestCase;
import lib.Oops;
import lib.SS;

public class OOPS_LoginTest extends BaseTestCase{
	SS sr = new SS();
	Oops op = new Oops();
	String username = "13711111111" , pwd = "000000" , nick = "Yser";
	public void test_login() throws IOException, UiObjectNotFoundException{
		Auto.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY,"广场");
		Auto.clickByText("偶的");
		if(!Auto.checkText("未登录")){
			op.logout();
		}
		op.login(username,pwd,nick);	
	}
	
}
