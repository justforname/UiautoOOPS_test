package TestCase;

import java.io.IOException;

import lib.BaseTestCase;
import lib.Auto;
import lib.SS;

import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

public class D_miyu_creathome extends BaseTestCase {
	String homename = "Testhome";
	String [] keywords= {"发言", "送礼物", "点歌","公聊","私聊","蜜友","麦序"};
	String name;
	public void test_creatmiyuhome() throws IOException, UiObjectNotFoundException{
		SS sr = new SS();
		//Dauto bs = new Dauto();
		SetTestCase(this.getClass().getName());
		Auto.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY);
		Auto.clickByText("发现");
		sleep(1000);
		Auto.clickByText("蜜语直播");
		sleep(6000);
		int y = UiDevice.getInstance().getDisplayHeight();
		int x = UiDevice.getInstance().getDisplayWidth();
		
		for(int i=0;i<5;i++){
			UiObject err = new UiObject(new UiSelector().className("android.widget.TextView").text("加载失败，轻触屏幕重试"));
			if(err.exists()){
				UiDevice.getInstance().click(x/2, y/2);
			}else break;
			sleep(2000);
		}
		
		//bs.getobjbytext("开通房间"); // 需要登录
		//UiDevice.getInstance().click(x/2, y/2 );
		sleep(2000);
		UiObject myhome = new UiObject(new UiSelector().className("android.widget.ListView"));
		myhome.getChild(new UiSelector().index(0)).getChild(new UiSelector().index(1)).getChild(new UiSelector().className("android.widget.LinearLayout").index(1)).click();
		
		
		sleep(1000);
		if(Auto.checkText("开通房间")){
			Log.d("LLTest", "该账号已经开通房间");
			assertTrue("没有进入开通房间界面",Auto.checkText("开通房间"));
			UiObject name = new UiObject(new UiSelector().text("请输入房间名"));
			name.setText(homename);
			sleep(1000);
			UiObject openmassage = new UiObject(new UiSelector().text("请输入房间公告"));
			openmassage.setText(homename);
			Auto.clickByText("进入房间");
		}
		sleep(5000);
		assertTrue("Keywordsmiss",Auto.checkSomeWords(keywords));
		Auto.clickByText("主持");
		sleep(1500);
		assertTrue("没有出现音效设置", Auto.checkText("音效"));
		Auto.clickByText("主持");
		sleep(1500);
		assertTrue("没有出现音效设置", !Auto.checkText("音效"));
		Auto.clickByText("点歌");
		sleep(800);
		
		UiDevice.getInstance().click(x/4, y/2 );
		UiObject clicklist =new UiObject(new UiSelector().className("android.widget.ListView")).getChild(new UiSelector().className("android.widget.RelativeLayout").index(1));
		sleep(1000);
		if(Auto.checkText("排麦"))
			Auto.clickByText("排麦");
		else{
			clicklist.click();
			UiObject paimai = new UiObject(new UiSelector().text("排麦"));
			paimai.waitForExists(60000);
			UiDevice.getInstance().pressBack();
			Auto.clickByText("已点");
			UiObject yidian =new UiObject(new UiSelector().className("android.widget.ListView")).getChild(new UiSelector().className("android.widget.RelativeLayout").index(0)).getChild(new UiSelector().className("android.widget.TextView").index(0));
			name = yidian.getText();
			Log.d("liliTest", "点歌名称:"+name);
			Auto.clickByText(name);
		}
		Auto.clickByButtonText("确认演唱");
		sleep(10000);
		Auto.clickByText("下麦");
		assertTrue("缺少无排麦信息", !Auto.checkText("下麦"));
		Auto.clickByText("点歌");
		Auto.clickByText("已点");
		UiObject longclicksong = new UiObject(new UiSelector().text(name));
		longclicksong.longClick();
		Auto.clickByText("删除");
		
		UiDevice.getInstance().pressBack();
		sleep(2000);
		Auto.clickByText("确定");
		assertTrue("Fail exit my home", Auto.checkText("我的房间"));
		//bs.killprocess("com.duomi.android");
		/*UiScrollable ls = new UiScrollable(new UiSelector().className("android.widget.ListView").scrollable(true));
		ls.scrollTextIntoView("蜜语好声音");
		sleep(1000);
		bs.getobjbytext("蜜语好声音");
		sleep(3000);
		assertTrue("没有进入密语直播",bs.checktext("发言"));
		bs.getobjbystarttext("点歌");
		bs.checktext("点歌");*/
		UiDevice.getInstance().pressHome();
	}
}
