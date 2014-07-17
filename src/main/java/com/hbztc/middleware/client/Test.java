package com.hbztc.middleware.client;

import com.hbztc.middleware.util.HttpClientHelper;

public class Test {
	private static ObjectPool objPool = new ObjectPool();

	static {
		objPool.createPool();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Ctest(objPool));
			t.start();
		}
	}
}

class Ctest implements Runnable {
	ObjectPool objPool = null;

	public Ctest(ObjectPool objPool) {
		this.objPool = objPool;
	}

	@Override
	public void run() {
		int i = 0;
		String reqXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
				+ "<message>\n"
				+ "  <head>\n"
				+ "   <menuid>#menuid#</menuid>\n"
				+ "    <process_code>cli_channels_qry_login</process_code>\n"
				+ "    <verify_code>#verify_code#</verify_code>\n"
				+ "    <req_time>20140618090912</req_time>\n"
				+ "    <req_seq>#req_seq#</req_seq>\n"
				+ "    <unicontact>#unicontact#</unicontact>\n"
				+ "    <testflag>#testflag#</testflag>\n"
				+ "    <route>\n"
				+ "      <route_type>1</route_type>\n"
				+ "      <route_value>15002735378</route_value>\n"
				+ "    </route>\n"
				+ "    <channelinfo>\n"
				+ "      <operatorid>#operatorid#</operatorid>\n"
				+ "      <channelid>#channelid#</channelid>\n"
				+ "      <unitid>#unitid#</unitid>\n"
				+ "    </channelinfo>\n"
				+ "  </head>\n"
				+ "  <Body>\n"
				+ "    <cli_channels_qry_login>\n"
				+ "      <tagset>\n"
				+ "        <TELNUM>15002735378</TELNUM>\n"
				+ "        <PASSWORD>860708</PASSWORD>\n"
				+ "        <ISCHECKPASS>1</ISCHECKPASS>\n"
				+ "        <process_code>cli_channels_qry_login</process_code>\n"
				+ "      </tagset>\n" + "    </cli_channels_qry_login>\n"
				+ "  </Body>\n" + "</message>";
		while (true) {
			HttpClientHelper httpClientHelper = (HttpClientHelper) objPool.getObject();
			httpClientHelper.post(reqXml);
			objPool.returnObject(httpClientHelper);
			System.out.println("bCode : " + httpClientHelper.getBt_Cookie());
		}
	}
}