package TestCase;

import java.io.IOException;

import lib.BaseTestCase;
import lib.Auto;
import lib.Watcher;



import com.android.uiautomator.core.UiObjectNotFoundException;

public class maincase extends BaseTestCase {
	
	Auto bs = new Auto();
	Watcher wr = new Watcher();
	public void test_01_login() throws IOException, UiObjectNotFoundException{
		D_login login = new D_login();
		SetTestCase("D_login");
			login.test_logind();
	}
	
	public void test_02_scansong() throws IOException, UiObjectNotFoundException{
		D_scansong scan = new D_scansong();
		SetTestCase("D_scansong");
				scan.test_scansong();
	}
	
	
	public void test_03_search() throws IOException, UiObjectNotFoundException{
		D_search se = new D_search ();
		SetTestCase("D_search");
				se.test_search();

	}
	
	
	public void test_04_playlocalsong() throws IOException, UiObjectNotFoundException{
		D_playlocalsong mi = new D_playlocalsong();
		SetTestCase("D_playlocalsong");
				mi.test_playsong();
	}
	
	public void test_05_markplaylist() throws IOException, UiObjectNotFoundException{
		D_markplaylist mk = new D_markplaylist();
		SetTestCase("D_markplaylist");
				mk.test_markplaylist();
	}
	
	
	
	public void test_06_playonlinesong() throws IOException, UiObjectNotFoundException{
		D_playonlinesong line = new D_playonlinesong();
		SetTestCase("D_playonlinesong");
				line.test_playonlinesong();
	}
	
	
	public void test_07_downloadsong() throws IOException, UiObjectNotFoundException{
		D_downloadsong dws = new D_downloadsong();
		SetTestCase("D_downloadsong");
				dws.test_downloadsong();
	}
	
	
//	public void test_08_miyu() throws IOException, UiObjectNotFoundException{
//		D_miyu_screencheck mi = new D_miyu_screencheck();
//		SetTestCase("D_miyu_screencheck");
//			mi.test_miyu();
//	}
	
	
	
//	public void test_09_mic() throws IOException, UiObjectNotFoundException{
//		D_miyu_music mic = new D_miyu_music();
//		SetTestCase("D_miyu_music");
//			mic.test_checkmiyumic();
//		
//	}
//	public void test_10_michome() throws IOException, UiObjectNotFoundException{
//		D_miyu_creathome micct = new D_miyu_creathome();
//		SetTestCase("D_miyu_creathome");
//			micct.test_creatmiyuhome();
//	}
	
	public void test_11_sginup() throws UiObjectNotFoundException, IOException{
		D_register sign= new D_register();
			sign.test_signin();
	}
}
