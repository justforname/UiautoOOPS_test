package TestCase;

import java.io.IOException;

import com.android.uiautomator.core.UiObjectNotFoundException;

import lib.Auto;
import lib.BaseTestCase;
import lib.Oops;
import lib.SS;

public class OOPS_Delete_haibao extends BaseTestCase{
	SS sr = new SS();
	Oops op =new Oops();
	public void test_delete() throws IOException, UiObjectNotFoundException{
		String haibaoname = "";
		Auto.launchApplication(sr.APPPACKAGE, sr.APPACTIVITY,"广场");
		op.haibaomore(haibaoname); //点击海报的更多
		Auto.clickByText("删除");
		Auto.clickByText("确定");
		assertFalse("Delete haibao Fail"+haibaoname, Auto.checkText(haibaoname));
		
		
	}
}
