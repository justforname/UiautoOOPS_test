package TestCase;

import java.io.IOException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

import lib.Auto;
import lib.BaseTestCase;
import lib.SS;

public class OOPS_FatieTest extends BaseTestCase{
	SS sr = new SS();
	String videolink = "http://v.youku.com/v_show/id_XMTMzMTQyNTUzNg==.html";
	String searchword = "ni";
	public void test_fatei() throws IOException, UiObjectNotFoundException{
		Auto.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY,"广场");
		Auto.clickByText("偶的");
		Auto.swipe("up");
		sleep(1000);
		Auto.clickByText("查看全部");
		Auto.scrollTextIntoView("测试咔咔咔");
		Auto.getViewByClassWithInstance("android.widget.FrameLayout", 5).click(); //点击底部添加“+”按钮
		Auto.getViewByClassWithInstance("android.widget.FrameLayout", 6).click(); //点击发帖按钮
		Auto.inPutWords("android.widget.EditText", 0, "Test_Test");
		Auto.inPutWords("android.widget.EditText", 1, "Test_TestTest_TestTest_TestTest_TestTest_TestTest_Test");
		//sleep(1000);
		Auto.clickByText("图片");
		Auto.getViewByClassWithInstance("android.widget.ImageView", 1).click(); //添加图片
		for(int i=0;i<3;i++){
			sleep(500);
			Auto.getViewByClassWithInstance("android.widget.ImageView", i+1).click(); //选择图片
		}
		Auto.clickByText("确认");
		//添加音乐
		Auto.clickByText("音乐");
		Auto.getViewByClassWithInstance("android.widget.ImageView", 1).click(); //添加音乐
		Auto.inPutWords("android.widget.EditText", 2, searchword);
		Auto.clickByText("搜索");
		Auto.getViewByClassWithInstance("android.support.v7.widget.RecyclerView", 0).getChild(new UiSelector().className("android.widget.RelativeLayout").index(0)).click();
		Auto.clickByText("确定");
		//添加视频
		Auto.clickByText("视频");
		Auto.getViewByClassWithInstance("android.widget.ImageView", 1).click(); //添加视频
		Auto.inPutWords("android.widget.EditText", 2, videolink);
		Auto.clickByText("添加");
		//添加海报
		Auto.clickByText("海报");
		Auto.getViewByClassWithInstance("android.widget.ImageView", 1).click(); //添加海报
		Auto.getViewByClassWithInstance("android.support.v7.widget.RecyclerView", 0).getChild(new UiSelector().className("android.widget.RelativeLayout").index(0)).click();
		Auto.clickByText("确定");
		//添加行程
		
		
		Auto.clickByText("下一步");
		Auto.clickByText("话题");
		//new UiObject(new UiSelector().className("android.widget.TextView").text("行程").instance(1)).click();
		Auto.clickByText("福利");
		Auto.clickByText("发布");
	}
	
	public void xingcheng() throws UiObjectNotFoundException{
		Auto.clickByText("行程");
		Auto.getViewByClassWithInstance("android.widget.ImageView", 1).click(); //添加行程
		Auto.inPutWords("android.widget.EditText", 2, "TEST_TEST");
		Auto.clickByText("输入行程开始时间");
		Auto.clickByText("29");
		Auto.clickByText("确定");
		Auto.clickByText("输入行程开始时间");
		Auto.clickByText("Done");
		Auto.inPutWords("android.widget.EditText", 5, "Bejing_changyan");
		Auto.inPutWords("android.widget.EditText", 6, "Song_song_song");
		Auto.clickByText("添加");
	}
	
}
