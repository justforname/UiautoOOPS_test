package TestCase;

import java.io.IOException;

import lib.Auto;
import lib.Oops;
import lib.SS;

import com.android.uiautomator.core.UiObjectNotFoundException;

public class OOPS_LogoutTest {
	SS sr = new SS();
	Oops op = new Oops();
	String username = "13711111111" , pwd = "000000" , nick = "Yser";
	public void test_logout() throws UiObjectNotFoundException, IOException{
		Auto.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY,"广场");
		Auto.clickByText("偶的");
		if(Auto.checkText("未登录")){
			op.login(username,pwd,nick);
		}	
		op.logout();
	}
	
	
}
