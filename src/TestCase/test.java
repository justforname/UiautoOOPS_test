package TestCase;

import java.io.IOException;

import lib.BaseTestCase;
import lib.GetCpu;
import lib.GetMemory;
import lib.TakeScreen;
import lib.Getlogcat;

import android.util.Log;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class test extends UiAutomatorTestCase{
	
	public void testDemo(){
		Getlogcat ch = new Getlogcat();
		
		ch.runget();
		
		GetMemory gm = new GetMemory("com.duomi.android");
		gm.runstart();
		GetCpu cp = new GetCpu("com.duomi.android");
		cp.runstart();
//		
//		Log.d("TTXX", "1");
//		sleep(10000);
//		gm.thread_stop();
//		cp.thread_stop();
//		UiObject kk = null;
		//kk.getChild((new UiSelector()).className(android/widget/Switch.getName()));
		Log.d("NNNN", "Stop");
		sleep(9999999l);
		ch.stop_thread();
		//catchlog ct = new catchlog();
		//Dbase bs =new Dbase();
		//ct.runget();
		//bs.takescreen("ha");
		//sleep(1000000);
		//ct.stop_thread();
	}
	
}
