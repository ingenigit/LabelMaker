package com.genipos.labelmaker;

import android.util.Log;
import android.widget.Toast;

import com.example.tscdll.TSCActivity;

public class TscPrinterManager {
	
	TSCActivity TscDll;// = new TSCActivity(); 
	String devaddr;
	String devname;
	int devconnstatus=0;	//0= disconnected, 1=connected, 2=initialized
	
	TscPrinterManager()
	{
		TscDll = new TSCActivity();
	}
	public boolean setPrinter(String name, String addr)
	{
		devname = name;
		devaddr = addr;
		
		boolean connres = connectPrinter(addr);
		
		if (connres == true)
		{
			initPrinter();
		
			devconnstatus = 2;
		
		}
		
		return connres;
			
	}

	public boolean connectPrinter(String addr)
	{
		String res = TscDll.openport(addr); 

    	Log.i("BT", "Openport:"+res);
    	//if (TscDll.IsConnected)
    	if (res.equals("1"))
    	{
    		Log.i("BT", "Device is connected:");
    	
    		devconnstatus = 1;
    		return true;
    	}
    	else
    	{
    		Log.i("BT", "Device is not connected:");
    		devconnstatus = 0;
    		return false;
    	}
	}
	
	public void initPrinter()
	{
		String res;
		res = TscDll.setup(60, 40, 4, 4, 0, 2, 0);
    	Log.i("BT", "Setup:"+res);
    	res = TscDll.clearbuffer(); 
    	Log.i("BT", "clearbuffer:"+res);
    	TscDll.sendcommand("SET TEAR OFF\n");
    	
    	//String res = TscDll.downloadttf("assets/fonts/calibri.ttf");
    	//res = TscDll.downloadttf("calibri.ttf");
    	
    	////res = TscDll.downloadttf("arialnarrowspecialg1.ttf");
    	////Log.i("TscPrinterManager", "font download command result: "+res);
	}
	
	public void disconnectPrinter()
	{
		TscDll.closeport();
	}
	public boolean isPrinterConnected()
	{
		if (devconnstatus >= 1)
			return true;
		else
			return false;
	}
	
	public boolean isPrinterInitialized()
	{
		if (devconnstatus == 2)
			return true;
		else
			return false;
	}
	
	
	public void PrintLabel(LabelInfo label)
	{
		if (isPrinterInitialized() == false)
		{
			Log.i("TscPrinterManager", "printer not initialized");
			return;
		}
		
		TscDll.clearbuffer();
		
		//String  header = "Suruchi Bakery";
		//String  footer = "Customer Care: 9999999999";
		
		int prinfolen = label.prodinfo.length();
		String proinfo1;
		String proinfo2;
		String proinfo3;
		if (prinfolen > 44)
		{
			proinfo1 = label.prodinfo.substring(0, 44);

			if (prinfolen > 88)
			{
				proinfo2 = label.prodinfo.substring(44, 88);
				proinfo3 = label.prodinfo.substring(88, prinfolen);
			}
			else
			{
				proinfo2 = label.prodinfo.substring(44, prinfolen);
				proinfo3 = "";
			}
		}
		else
		{
			proinfo1 = label.prodinfo;
			proinfo2 = "";
			proinfo3 = "";
		}
		
		//Print product name

//		TscDll.printerfont(20, 15, "3", 0, 1, 1, label.title);
//		TscDll.printerfont(20, 45, "2", 0, 1, 1, label.prodname);
//		TscDll.printerfont(20, 65, "2", 0, 1, 1, label.price);
//		TscDll.barcode(20, 85, "128", 30, 1, 0,3,3, label.barcode);
//		TscDll.printerfont(20, 150, "2", 0, 1, 1, label.footer);
		TscDll.printerfont(20, 15, "4", 0, 1, 1, label.title);
		TscDll.printerfont(20, 55, "3", 0, 1, 1, label.prodname);
		TscDll.printerfont(20, 85, "1", 0, 1, 1, proinfo1);
		TscDll.printerfont(20, 100, "1", 0, 1, 1, proinfo2);
		TscDll.printerfont(20, 115, "1", 0, 1, 1, proinfo3);
		TscDll.printerfont(20, 130, "2", 0, 1, 1, "Mfg Dt:"+label.mfgdate);
		TscDll.printerfont(250, 130, "2", 0, 1, 1, "Use by:"+label.expdate);
		TscDll.printerfont(20, 150, "1", 0, 1, 1, "Net Wt.:"+label.netweight+label.wtunit);
		TscDll.printerfont(250, 150, "1", 0, 1, 1, "Batch No:"+label.batchno);
		TscDll.printerfont(20, 165, "2", 0, 1, 1, "Price:Rs"+label.price);
		TscDll.printerfont(250, 170, "1", 0, 1, 1,"[Include All Taxes]");
    	TscDll.barcode(20, 185, "128", 30, 1, 0, 3, 3, label.barcode);
    	//TscDll.printerfont(340, 170, "2", 0, 1, 1, "Batch No");
    	//TscDll.printerfont(340, 200, "2", 0, 1, 1, label.batchno);
    	TscDll.printerfont(20, 250, "2", 0, 1, 1, "Mfg At:"+label.mfgsite);
    	TscDll.printerfont(20, 270, "2", 0, 1, 1, "Fssai Lic No: "+label.licno);
    	TscDll.printerfont(20, 295, "2", 0, 1, 1, label.footer);
    	//TscDll.printerfont(50, 250, "3", 0, 1, 1, "123456789");
    	///Log.i("BT", "sendcommand4:"+res);
    	//TscDll.printerfont(0, 0, "3", 0, 1, 1, "987654321"); 
    	//String status = TscDll.status(); 
    	//Log.i("BT", "status:"+status);
    	//text1.setText(status); 
    	///TscDll.sendcommand("PRINT 3,1\n"); 
    	
    	//TscDll.sendcommand("BOX 10,50, 460, 260, 2\n"); 
    	TscDll.sendcommand("BAR 1,50, 480, 3\n"); 
    	TscDll.sendcommand("BAR 1,290, 480, 3\n"); 
    	
    	TscDll.printlabel(label.count, 1); 
	}

/*
	public void PrintLabel(LabelInfo label)
	{
		if (isPrinterInitialized() == false)
		{
			Log.i("TscPrinterManager", "printer not initialized");
			return;
		}
		
		TscDll.clearbuffer();
		
		//String  header = "Suruchi Bakery";
		//String  footer = "Customer Care: 9999999999";
		
		int prinfolen = label.prodinfo.length();
		String proinfo1;
		String proinfo2;
		if (prinfolen > 32)
		{
			proinfo1 = label.prodinfo.substring(0, 32);
			proinfo2 = label.prodinfo.substring(32, prinfolen);
		}
		else
		{
			proinfo1 = label.prodinfo;
			proinfo2 = "";
		}
		
		
		
		//Print product name
		TscDll.sendcommand("TEXT 20,10,\"arialnarrowspecialg1.ttf\",0,18,18,\"SURUCHI BAKERY\"\n");//(20, 10, "4", 0, 1, 1, );
		TscDll.sendcommand("TEXT 20,50,\"arialnarrowspecialg1.ttf\",0,18,18,\"Bread\"\n");//(20, 50, "3", 0, 1, 1, label.prodname);
		TscDll.sendcommand("TEXT 20,80,\"arialnarrowspecialg1.ttf\",0,15,15,\"Ingerients: Milk, Sugar, Flour\"\n");//(20, 80, "1", 0, 1, 1, proinfo1);
		TscDll.printerfont(20, 100, "1", 0, 1, 1, proinfo2);
		//TscDll.printerfont(20, 120, "2", 0, 1, 1, "Price:Rs"+label.price+ " [Include All Taxes]");
		//TscDll.printerfont(20, 140, "1", 0, 1, 1, "Mfg:"+label.mfgdate+ "  Use by:"+label.expdate);
    	TscDll.barcode(20, 170, "128", 30, 1, 0, 3, 3, label.barcode); 
    	///TscDll.printerfont(340, 170, "2", 0, 1, 1, "Batch No");
    	////TscDll.printerfont(340, 200, "2", 0, 1, 1, label.batchno);
    	TscDll.printerfont(20, 250, "2", 0, 1, 1, "Mfg At: "+label.mfgsite);
    	TscDll.sendcommand("TEXT 20,270,\"arialnarrowspecialg1.ttf\",0,12,12,\"Fssai Lic No: 012345678901234567890\"\n");//TscDll.printerfont(20, 270, "2", 0, 1, 1, "Fssai Lic No: "+label.licno);
    	//TscDll.printerfont(20, 290, "2", 0, 1, 1, label.footer);
    	//TscDll.printerfont(50, 250, "3", 0, 1, 1, "123456789");
    	///Log.i("BT", "sendcommand4:"+res);
    	//TscDll.printerfont(0, 0, "3", 0, 1, 1, "987654321"); 
    	//String status = TscDll.status(); 
    	//Log.i("BT", "status:"+status);
    	//text1.setText(status); 
    	///TscDll.sendcommand("PRINT 3,1\n"); 
    	
    	//TscDll.sendcommand("BOX 10,50, 460, 260, 2\n"); 
    	
    	TscDll.printlabel(label.count, 1); 
	}
*/
}
