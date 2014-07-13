package com.hbztc.middleware.client;

import com.hbztc.middleware.util.HttpClientHelper;

public class Test {
	private static ObjectPool objPool = new ObjectPool();
	static{
		objPool.createPool();
	}
	public static void main(String[] args) {

		for(int i = 0;i<20;i++){
			Thread t = new Thread(new Ctest(objPool));
			t.start();
		}
	}
	


}
class Ctest implements Runnable{
	ObjectPool objPool  =null;
	public Ctest(ObjectPool objPool ){
		this.objPool = objPool;
	}
	@Override
	public void run() {
		int i =0;
		String reqXml = 
				"<?xml version=\"1.0\" encoding=\"GBK\" ?><message>\n" +
				"<head version=\"1.0\">\n" + 
				"<menuid>#menuid#</menuid>\n" + 
				"<process_code>cli_mmobile_qry_feeblance</process_code>\n" + 
				"<verify_code>#verify_code#</verify_code>\n" + 
				"<req_time>20140618091008</req_time>\n" + 
				"<req_seq>#req_seq#</req_seq>\n" + 
				"<unicontact>#unicontact#</unicontact>\n" + 
				"<testflag>#testflag#</testflag><route>\n" + 
				"<route_type>1</route_type>" +
				"<route_value>15002735378</route_value>\n" + 
				"</route><channelinfo>\n" + 
				"<operatorid>#operatorid#</operatorid>\n" + 
				"<channelid>#channelid#</channelid>\n" + 
				"<unitid>#unitid#</unitid></channelinfo>\n" + 
				"</head><Body>" +
				"<cli_mmobile_qry_feeblance>" +
				"<tagset>\n" + 
				"<TELNUM>15002735378</TELNUM>" +
				"<FLAG>0</FLAG>\n" + 
				"<process_code>cli_mmobile_qry_feeblance</process_code>\n" + 
				"</tagset></cli_mmobile_qry_feeblance>\n" + 
				"</Body></message>";
		while(true){
			
				HttpClientHelper httpClientHelper = (HttpClientHelper)objPool.getObject();
				httpClientHelper.post(reqXml);
				objPool.returnObject(httpClientHelper);
				System.out.println("bCode : "+httpClientHelper.getBt_Cookie());
		}
		
		
	}
	
}