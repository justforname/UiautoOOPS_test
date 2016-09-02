package TestCase;

import java.io.File;
import java.io.IOException;

import lib.BaseTestCase;
import lib.SS;
import lib.GetCpu;
import lib.GetMemory;
import lib.Auto;

import android.util.Log;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class D_downloadsong extends BaseTestCase{

	public void test_downloadsong() throws IOException,UiObjectNotFoundException {
		String TAG ="TTXX";
		SS sr = new SS();
		Auto au = new Auto();
		GetCpu cp = new GetCpu(sr.APPPACKAGE);
		cp.start();
		GetMemory gm = new GetMemory(sr.APPPACKAGE);
		gm.start();
		SetTestCase("downloadsong");
		sleep(2000);
		au.dmlog(TAG, "应用启动");
		au.launchApplication(sr.APPPACKAGE,sr.APPACTIVITY);
		au.dmlog(TAG, "点击音乐架");
		au.clickByText("音乐架");
		sleep(2000);
		// bs.getobjbytext("排行榜");
		UiObject ho = new UiObject(new UiSelector().className(
				"android.widget.HorizontalScrollView").index(0));
		UiObject img = ho.getChild(new UiSelector().className(
				"android.widget.ImageView").instance(2));
		au.dmlog(TAG, "点击排行");
		img.click();
		sleep(2000);
		au.dmlog(TAG, "点击新歌榜");
		au.clickByText("多米新歌榜");
		sleep(3000);
		assertTrue("Not into new song list", au.checkText("多米新歌榜"));
		au.clickByText("更多");
		au.clickByText("批量下载");
		au.clickByText("全选");
		au.clickByText("下载");
		au.dmlog(TAG, "点击下载");
		au.clickByText("高品质优先");
		au.pressBack(3);
		au.clickByText("我的音乐");
		au.clickByText("下载歌曲");
		au.dmlog(TAG, "进入下载界面");
		au.clickByText("正在下载");
		UiCollection dwlist = new UiCollection(new UiSelector().className(
				"android.widget.ListView").index(0));
		int cell = dwlist.getChildCount();
		Log.d("liliTest", "The download song view number:" + cell);
		assertTrue("Download list empty", cell > 1);
		// bs.getobjbytext("清空全部");
		au.dmlog(TAG, "点击清空下载");
		au.clickByButtonText("清空全部");
		int cell2 = dwlist.getChildCount();
		Log.d("liliTest", "clear number:" + cell2);
		//sleep(60000);
		File file = new File("/mnt/sdcard/DUOMI/down/");
		String[] a = file.list();
		for (int i = 0; i < a.length; i++)
			Log.d("liliTest", "number:" + a[i]);
		//assertTrue("Check download path /sdcard/DUOMI/down--failed",file.list().length > 0);
		sleep(2000);
		cp.thread_stop();
		gm.thread_stop();
		UiDevice.getInstance().pressHome();
		

	}

}
