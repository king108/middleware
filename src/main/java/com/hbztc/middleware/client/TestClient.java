package com.hbztc.middleware.client;

import com.hbztc.middleware.util.HttpClientHelper;

public class TestClient {
	private static final String TestURL = "http://171.0.0.18:6315/liuL/rs/hb/agent";
	
	public static void main(String[] args) {
//		String reqXml = 
//				"<?xml version=\"1.0\" encoding=\"GBK\" ?><message>\n" +
//				"<head version=\"1.0\">\n" + 
//				"<menuid>#menuid#</menuid>\n" + 
//				"<process_code>cli_net_gprsflux_used_2014</process_code>\n" + 
//				"<verify_code>#verify_code#</verify_code>\n" + 
//				"<req_time>20140618091700</req_time>\n" + 
//				"<req_seq>#req_seq#</req_seq>\n" + 
//				"<unicontact>#unicontact#</unicontact>\n" + 
//				"<testflag>#testflag#</testflag>\n" + 
//				"<route><route_type>1</route_type>\n" + 
//				"<route_value>15802775695</route_value></route>\n" + 
//				"<channelinfo>\n" + 
//				"<operatorid>#operatorid#</operatorid>\n" + 
//				"<channelid>#channelid#</channelid>\n" + 
//				"<unitid>#unitid#</unitid>\n" + 
//				"</channelinfo>\n" + 
//				"</head>\n" + 
//				"<Body><cli_net_gprsflux_used_2014>\n" + 
//				"<tagset>" +
//				"<TELNUM>15802775695</TELNUM>" +
//				"<GROUPID></GROUPID>\n" + 
//				"<process_code>cli_net_gprsflux_used_2014</process_code>" +
//				"</tagset>\n" + 
//				"</cli_net_gprsflux_used_2014>\n" + 
//				"</Body></message>";
//		String returnMsg = "";
//		try {
//			URL url = new URL(TestURL);
//			URLConnection con = url.openConnection();
//			HttpURLConnection httpUrlConnection = (HttpURLConnection) con;  
////			httpUrlConnection.setConnectTimeout(30000);  
////			httpUrlConnection.setReadTimeout(30000);
//			httpUrlConnection.setUseCaches(false);  
//			httpUrlConnection.setDoOutput(true);
//			httpUrlConnection.setRequestProperty("Pragma:", "no-cache");
//			httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
//			httpUrlConnection.setRequestProperty("Content-Type", "text/xml");
//			httpUrlConnection.setRequestProperty("Cookie", "liulapp=ll123654");
//			httpUrlConnection.setRequestProperty("Content-Type", "text/xml; charset=GBK");
//		
////			httpUrlConnection.connect();
////			httpUrlConnection.getHeaderFields();
//			OutputStreamWriter out = new OutputStreamWriter(httpUrlConnection.getOutputStream(), "GBK");
//			out.write(new String(reqXml.getBytes()));
//			out.flush();
//			out.close();
//			BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "GBK"));
//			String line = "";
//			for (line = br.readLine(); line != null; line = br.readLine()) {
//				returnMsg = returnMsg + line;
//			}
//			br.close();
//			System.out.println(returnMsg);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}catch(SocketTimeoutException se){
//			se.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
