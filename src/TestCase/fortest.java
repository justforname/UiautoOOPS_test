package TestCase;

import com.android.uiautomator.core.UiObjectNotFoundException;

import lib.BaseTestCase;
import lib.Oops;

public class fortest extends BaseTestCase{
	Oops op = new Oops();
	public void test_sentsms() throws UiObjectNotFoundException{
		String [] aw ={"haha","like","good"};
		
		while(true){
			for(int i=0;i<aw.length;i++){
			op.sendComment(aw[i]);
			}
		}
		
	}
	
}
