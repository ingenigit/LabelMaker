package com.genipos.labelmaker;



import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppEnv extends Application{

	//ProductManager gProductMgr;
	
	TscPrinterManager gTscPrintMgr;
	
	/*AppEnv()
	{
		
	}*/
	public void Init()
	{
		//gProductMgr = new ProductManager(this);
		
		gTscPrintMgr = new TscPrinterManager();
	}
	
	/*public ProductManager getProductManager()
	{
		return gProductMgr;
	}*/
	
	public TscPrinterManager getTscPrinterManager()
	{
		return gTscPrintMgr;
	}
	
	public String getLabelHeader()
	{
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		
		String labelHeader = sharedPref.getString("prefLabelHeader", "");
		
		return labelHeader;
	}
	
	public String getLabelFooter()
	{
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		
		String labelFooter = sharedPref.getString("prefLabelFooter", "");
		
		return labelFooter;
	}
	
	public String getMfgSite()
	{
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		
		String labelHeader = sharedPref.getString("prefMfgLoc", "");
		
		return labelHeader;
	}
	
	public String getLicNo()
	{
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		
		String labelFooter = sharedPref.getString("prefFssaiLicNo", "");
		
		return labelFooter;
	}
}
