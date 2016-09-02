package lib;

import android.util.Log;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Oops extends UiAutomatorTestCase{
	/**
	 * 进入我的指定团
	 * @param tuanname 在列表中团的名字
	 * @throws UiObjectNotFoundException
	 */
	public void intoMyTuan(String tuanname) throws UiObjectNotFoundException{
		Auto.clickByText("偶的");
		Auto.swipe("up");
		sleep(1000);
		Auto.clickByText("查看全部");
		Auto.scrollTextIntoView(tuanname);
		
	}
	/**
	 * logout
	 * @throws UiObjectNotFoundException
	 */
	public void logout() throws UiObjectNotFoundException{
		Auto.clickByText("偶的");
		Auto.swipe("up");
		sleep(500);
		Auto.clickByText("设置");
		Auto.clickByText("退出当前帐号");
		Auto.clickByText("我确定");
		assertTrue("logout fail", Auto.checkText("未登录"));
	}
	/**
	 * login
	 * @param username
	 * @param pwd
	 * @param nick
	 * @throws UiObjectNotFoundException
	 */
	public void login(String username, String pwd, String nick) throws UiObjectNotFoundException{
		Auto.getViewByClassWithInstance("android.widget.ImageView", 0).click();
		assertTrue("Can't insert login page", Auto.checkText("登录"));
		String t = Auto.getViewByClassWithInstance("android.widget.EditText", 0).getText();
		for(int i =0; i<t.length();i++){
			UiDevice.getInstance().pressKeyCode(112);
			sleep(20);
		}
		Auto.inPutWords("android.widget.EditText", 0, username);
		Auto.inPutWords("android.widget.EditText", 1, pwd);
		new UiObject(new UiSelector().text("登录").instance(1)).click();
		sleep(2000);
		assertTrue("login fail", Auto.checkText(nick));
		
	}
	/**
	 * 制作海报
	 * @param number
	 * @param name
	 * @param dec
	 * @throws UiObjectNotFoundException
	 */
	public void zhizuoHaiBao(int number, String name, String dec) throws UiObjectNotFoundException{
		Auto.clickByText("制作");
		Auto.getViewByClassWithInstance("android.widget.TextView", 1).click();//
		sleep(1000);
		UiCollection lis = Auto.getAllList("android.support.v7.widget.RecyclerView");
		Log.i("TTTTTT", "--"+lis.getChildCount());
		assertTrue("Count not match 6",lis.getChildCount()==6); //模板数目
		Auto.getListChild("android.support.v7.widget.RecyclerView", "android.widget.FrameLayout", 1).click();
		Auto.clickByText("使用");
		
		switch (number) {
		case 2:
			Auto.clickByText("新增页面");
			Auto.clickByText("二张");
			Auto.getListChild("android.support.v7.widget.RecyclerView", "android.widget.FrameLayout", 1).click();
			Auto.clickByText("使用");
			break;
		case 3:
			Auto.clickByText("新增页面");
			Auto.clickByText("三张");
			Auto.getListChild("android.support.v7.widget.RecyclerView", "android.widget.FrameLayout", 1).click();
			Auto.clickByText("使用");
			break;
		default:
			break;
		}
		
		Auto.clickByText("设置");
		Auto.clickByText("背景音乐");
		UiObject mu = Auto.getListChild("android.support.v7.widget.RecyclerView", "android.widget.RelativeLayout", 1);
		String mn = mu.getChild(new UiSelector().className("android.widget.TextView")).getText();
		Log.i("TTTTTT", "--"+mn);//名称
		mu.click();
		Auto.clickByText("确定");
		sleep(1000);
		assertTrue("change background music fail", Auto.checkText(mn));
		Auto.clickByText("保存");
		Auto.clickByText("下一步");
		Auto.presskeycode(112, Auto.getViewByClassWithInstance("android.widget.EditText", 0).getText().length());
		Auto.inPutWords("android.widget.EditText", 0, name);
		Auto.presskeycode(112, Auto.getViewByClassWithInstance("android.widget.EditText", 1).getText().length());
		Auto.inPutWords("android.widget.EditText", 1, dec);
		Auto.clickByText("保存");
		assertTrue("creat not SUCCESSFUL", Auto.checkText(name));
		
	}
	/**
	 * 进入特定海报的More菜单
	 * @param name 海报名称，同名为第一个
	 * @throws UiObjectNotFoundException
	 */
	public void haibaomore(String name) throws UiObjectNotFoundException{
		Auto.clickByText("制作");
		sleep(3000);
		UiObject sl = new UiObject(new UiSelector().scrollable(true));
		int i =0;
		while(sl.isScrollable()){
			i++;
			if(Auto.checkText(name)){
				break;
			}
			if(i>10){
				assertFalse("Can't find "+name, true);
			}
			 Auto.swipe("up");
			}
		//sl.scrollTextIntoView(name);
		Auto.getViewByClassWithText("android.widget.TextView", name).getFromParent(new UiSelector().className("android.widget.ImageView").instance(2)).click();
		
	}
	/**
	 * 发表评论
	 * @param sms 评论内容 只限英文
	 * @throws UiObjectNotFoundException
	 */
	public void sendComment(String sms) throws UiObjectNotFoundException{
		Auto.getViewByClass("android.widget.EditText").setText(sms);
		Auto.clickByText("发送");
		
	}
	
}
