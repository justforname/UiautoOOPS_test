package lib;

import android.os.RemoteException;
import android.util.Log;
import lib.Auto;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class BaseTestCase extends UiAutomatorTestCase {
	public SS ds;
	String name;

	public void SetTestCase(String casename){
		this.name=casename;
		//Log.d("YYY", name);
	}
	
	public BaseTestCase() {
		try {
			UiDevice.getInstance().wakeUp();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void setUp()throws Exception
		{
			super.setUp();
			ds = new SS();
			
		}
	
	protected void tearDown() throws Exception{
		Thread.sleep(2000);
		super.tearDown();
		Auto.takescreen(name);
		//Log.d("YYY", "TT"+name);
		Auto.killProcess(ds.APPPACKAGE);
		//UiDevice.getInstance().pressHome();
		//sleep(2000);
		
	}
	
	

}
