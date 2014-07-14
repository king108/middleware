package com.hbztc.middleware.client;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hbztc.middleware.util.HttpClientHelper;
import com.hbztc.middleware.util.JsonMapper;
import com.hbztc.middleware.util.XMLUtil;

public class PostInterfaceTest extends TestCase {
	private static final String TestURL = "http://171.0.0.18:6315/liuL/rs/hb/agent";
	private static final String ProductURL = "https://120.202.17.126:12520/liuL/rs/hb/agent";

	private static final String _200 = "200";
	private static final String CODE = "code";
	private static final String path = "d:\\hbztc\\";
	private static Logger logger = LoggerFactory.getLogger(PostInterfaceTest.class);
	private static ObjectPool objPool = new ObjectPool();
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		objPool.createPool();
		PropertyConfigurator.configure(this.getClass().getResource(
				"log4j.properties"));
	}

	// 查询余额
	public void testFeeblance() {
		String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message>\n"
				+ "<head version=\"1.0\">\n" + "<menuid>#menuid#</menuid>\n"
				+ "<process_code>cli_mmobile_qry_feeblance</process_code>\n"
				+ "<verify_code>#verify_code#</verify_code>\n"
				+ "<req_time>20140618091008</req_time>\n"
				+ "<req_seq>#req_seq#</req_seq>\n"
				+ "<unicontact>#unicontact#</unicontact>\n"
				+ "<testflag>#testflag#</testflag><route>\n"
				+ "<route_type>1</route_type>"
				+ "<route_value>15002735378</route_value>\n"
				+ "</route><channelinfo>\n"
				+ "<operatorid>#operatorid#</operatorid>\n"
				+ "<channelid>#channelid#</channelid>\n"
				+ "<unitid>#unitid#</unitid></channelinfo>\n" + "</head><Body>"
				+ "<cli_mmobile_qry_feeblance>" + "<tagset>\n"
				+ "<TELNUM>15002735378</TELNUM>" + "<FLAG>0</FLAG>\n"
				+ "<process_code>cli_mmobile_qry_feeblance</process_code>\n"
				+ "</tagset></cli_mmobile_qry_feeblance>\n"
				+ "</Body></message>";

		String returnXml = postReq(reqXml);
		try {
			logger.info("feeblance xml : " + returnXml);
			XMLUtil xmlUtil = new XMLUtil();
			JsonMapper mapper = JsonMapper.nonDefaultMapper();
			Map<String, Object> resutlMap = xmlUtil.parseXmlResult(returnXml,
					"/message/head/retinfo");
			String returnCode = String.valueOf(resutlMap.get(CODE));
			if (returnCode.equals(_200)) {
				xmlUtil.parseXmlData(resutlMap, returnXml,
						"/message/Body/cli_mmobile_qry_feeblance/tagset");
			}
			logger.info(mapper.toJson(resutlMap));
			Document document = DocumentHelper.parseText(returnXml);
			write(document, path + "feeblance.xml");

		} catch (DocumentException e) {
			logger.error(" feeblance error");
		} catch (IOException e) {
			logger.error(" feeblance io error");
		}
	}

	// 查询积分
	public void testQrySubsScore() {
		String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message><head version=\"1.0\">\n"
				+ "<menuid>#menuid#</menuid>\n"
				+ "<process_code>cli_mmobile_QrySubsScoreSimple</process_code>\n"
				+ "<verify_code>#verify_code#</verify_code>\n"
				+ "<req_time>20140618090912</req_time>\n"
				+ "<req_seq>#req_seq#</req_seq>\n"
				+ "<unicontact>#unicontact#</unicontact>\n"
				+ "<testflag>#testflag#</testflag>\n"
				+ "<route>\n"
				+ "<route_type>1</route_type>\n"
				+ "<route_value>15002735378</route_value>\n"
				+ "</route>\n"
				+ "<channelinfo>\n"
				+ "<operatorid>#operatorid#</operatorid>\n"
				+ "<channelid>#channelid#</channelid>\n"
				+ "<unitid>#unitid#</unitid>\n"
				+ "</channelinfo>\n"
				+ "</head>\n"
				+ "<Body>\n"
				+ "<cli_mmobile_QrySubsScoreSimple><tagset>\n"
				+ "<TELNUM>15002735378</TELNUM></tagset>\n"
				+ "</cli_mmobile_QrySubsScoreSimple>\n" + "</Body></message>";

		String returnXml = postReq(reqXml);
		try {
			logger.info("QrySubsScore xml : " + returnXml);
			XMLUtil xmlUtil = new XMLUtil();
			JsonMapper mapper = JsonMapper.nonDefaultMapper();
			Map<String, Object> resutlMap = xmlUtil.parseXmlResult(returnXml,
					"/message/head/retinfo");
			String returnCode = String.valueOf(resutlMap.get(CODE));
			if (returnCode.equals(_200)) {
				xmlUtil.parseXmlData(resutlMap, returnXml,
						"/message/Body/cli_mmobile_QrySubsScoreSimple/tagset");
			}
			logger.info(mapper.toJson(resutlMap));
			Document document = DocumentHelper.parseText(returnXml);
			write(document, path + "QrySubsScore.xml");

		} catch (DocumentException e) {
			logger.error(" QrySubsScore error");
		} catch (IOException e) {
			logger.error(" QrySubsScore io error");
		}
	}

	// 查询语音/短信余量：
	public void testPrivused() {
		String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\" ?>\n"
				+ "<message>\n"
				+ "<head version=\"1.0\"><menuid>#menuid#</menuid>\n"
				+ "<process_code>cli_mmobile_qry_privused</process_code>\n"
				+ "<verify_code>#verify_code#</verify_code>\n"
				+ "<req_time>20140618091132</req_time><req_seq>#req_seq#</req_seq>\n"
				+ "<unicontact>#unicontact#</unicontact>\n"
				+ "<testflag>#testflag#</testflag>\n"
				+ "<route><route_type>1</route_type>\n"
				+ "<route_value>15002735378</route_value></route>\n"
				+ "<channelinfo><operatorid>#operatorid#</operatorid>\n"
				+ "<channelid>#channelid#</channelid>\n"
				+ "<unitid>#unitid#</unitid></channelinfo>\n" + "</head>\n"
				+ "<Body>\n" + "<cli_mmobile_qry_privused><tagset>\n"
				+ "<TELNUM>15002735378</TELNUM>\n"
				+ "<BILLCYCLE>201406</BILLCYCLE>\n"
				+ "<process_code>cli_mmobile_qry_privused</process_code>\n"
				+ "</tagset></cli_mmobile_qry_privused>\n" + "</Body>\n"
				+ "</message>";
		String returnXml = postReq(reqXml);
		try {
			logger.info("Privused xml : " + returnXml);
			XMLUtil xmlUtil = new XMLUtil();
			JsonMapper mapper = JsonMapper.nonDefaultMapper();
			Map<String, Object> resutlMap = xmlUtil.parseXmlResult(returnXml,
					"/message/head/retinfo");
			String returnCode = String.valueOf(resutlMap.get(CODE));
			if (returnCode.equals(_200)) {
				xmlUtil.parseXmlData(resutlMap, returnXml,
						"/message/Body/cli_mmobile_qry_privused/crset");
			}
			logger.info(mapper.toJson(resutlMap));
			Document document = DocumentHelper.parseText(returnXml);
			write(document, path + "Privused.xml");

		} catch (DocumentException e) {
			logger.error(" Privused error");
		} catch (IOException e) {
			logger.error(" Privused io error");
		}
	}

	// 查询GPRS流量：
	public void testGprsflux() {
		String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message>\n"
				+ "<head version=\"1.0\">\n" + "<menuid>#menuid#</menuid>\n"
				+ "<process_code>cli_net_gprsflux_used_2014</process_code>\n"
				+ "<verify_code>#verify_code#</verify_code>\n"
				+ "<req_time>20140618091700</req_time>\n"
				+ "<req_seq>#req_seq#</req_seq>\n"
				+ "<unicontact>#unicontact#</unicontact>\n"
				+ "<testflag>#testflag#</testflag>\n"
				+ "<route><route_type>1</route_type>\n"
				+ "<route_value>15802775695</route_value></route>\n"
				+ "<channelinfo>\n" + "<operatorid>#operatorid#</operatorid>\n"
				+ "<channelid>#channelid#</channelid>\n"
				+ "<unitid>#unitid#</unitid>\n" + "</channelinfo>\n"
				+ "</head>\n" + "<Body><cli_net_gprsflux_used_2014>\n"
				+ "<tagset>" + "<TELNUM>15802775695</TELNUM>"
				+ "<GROUPID></GROUPID>\n"
				+ "<process_code>cli_net_gprsflux_used_2014</process_code>"
				+ "</tagset>\n" + "</cli_net_gprsflux_used_2014>\n"
				+ "</Body></message>";

		String returnXml = postReq(reqXml);
		try {
			logger.info("Gprsflux xml : " + returnXml);
			XMLUtil xmlUtil = new XMLUtil();
			JsonMapper mapper = JsonMapper.nonDefaultMapper();
			Map<String, Object> resutlMap = xmlUtil.parseXmlResult(returnXml,
					"/message/head/retinfo");
			String returnCode = String.valueOf(resutlMap.get(CODE));
			if (returnCode.equals(_200)) {
				xmlUtil.parseXmlData(resutlMap, returnXml,
						"/message/Body/cli_net_gprsflux_used_2014/crset");
			}
			logger.info(mapper.toJson(resutlMap));
			Document document = DocumentHelper.parseText(returnXml);
			write(document, path + "Gprsflux.xml");

		} catch (DocumentException e) {
			logger.error(" Gprsflux error");
		} catch (IOException e) {
			logger.error(" Gprsflux io error");
		}
	}

	// 查询用户信息
	public void testCustomerInfo() {
		String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\" ?>\n"
				+ "<message><head version=\"1.0\">\n"
				+ "<menuid/>\n"
				+ "<process_code>cli_channels_qry_QryCustomerInfoByTel</process_code>\n"
				+ "<verify_code/>\n"
				+ "<req_time>20140618091245</req_time>\n"
				+ "<req_seq>#req_seq#</req_seq>\n"
				+ "<unicontact/>\n"
				+ "<testflag>#testflag#</testflag>\n"
				+ "<route><route_type>1</route_type>\n"
				+ "<route_value>15002735378</route_value></route>\n"
				+ "<channelinfo>\n"
				+ "<operatorid>#operatorid#</operatorid>\n"
				+ "<channelid>#channelid#</channelid>\n"
				+ "<unitid>#unitid#</unitid>\n"
				+ "</channelinfo>\n"
				+ "</head>\n"
				+ "<Body>\n"
				+ "<cli_channels_qry_QryCustomerInfoByTel>\n"
				+ "<tagset>\n"
				+ "<ISCHECKPASS>false</ISCHECKPASS>\n"
				+ "<TELNUM>15002735378</TELNUM>\n"
				+ "<process_code>cli_channels_qry_QryCustomerInfoByTel</process_code>\n"
				+ "<PASSWORD></PASSWORD>\n" + "</tagset>\n"
				+ "</cli_channels_qry_QryCustomerInfoByTel>\n"
				+ "</Body></message>";
		String returnXml = postReq(reqXml);
		try {
			logger.info("CustomerInfo xml : " + returnXml);
			XMLUtil xmlUtil = new XMLUtil();
			JsonMapper mapper = JsonMapper.nonDefaultMapper();
			Map<String, Object> resutlMap = xmlUtil.parseXmlResult(returnXml,
					"/message/head/retinfo");
			String returnCode = String.valueOf(resutlMap.get(CODE));
			if (returnCode.equals(_200)) {
				xmlUtil.parseXmlData(resutlMap, returnXml,
						"/message/Body/cli_channels_qry_QryCustomerInfoByTel/tagset");
			}
			logger.info(mapper.toJson(resutlMap));
			Document document = DocumentHelper.parseText(returnXml);
			write(document, path + "CustomerInfo.xml");

		} catch (DocumentException e) {
			logger.error(" CustomerInfo error");
		} catch (IOException e) {
			logger.error(" CustomerInfo io error");
		}
	}

	// 查询用户停机状态
	public void testUserinfo() {
		String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message>\n"
				+ "<head version=\"1.0\"><menuid>#menuid#</menuid>\n"
				+ "<process_code>cli_channels_qry_userinfo</process_code>\n"
				+ "<verify_code>#verify_code#</verify_code>\n"
				+ "<req_time>20140618091433</req_time>\n"
				+ "<req_seq>#req_seq#</req_seq>\n"
				+ "<unicontact>#unicontact#</unicontact>\n"
				+ "<testflag>#testflag#</testflag>\n" + "<route>\n"
				+ "<route_type>1</route_type>\n"
				+ "<route_value>15002735378</route_value>\n" + "</route>\n"
				+ "<channelinfo>\n" + "<operatorid>#operatorid#</operatorid>\n"
				+ "<channelid>#channelid#</channelid>\n"
				+ "<unitid>#unitid#</unitid>\n" + "</channelinfo>\n"
				+ "</head>\n" + "<Body>\n" + "<cli_channels_qry_userinfo>\n"
				+ "<tagset>\n" + "<TELNUM>15002735378</TELNUM>\n"
				+ "</tagset>\n" + "</cli_channels_qry_userinfo>\n"
				+ "</Body>\n" + "</message>";

		String returnXml = postReq(reqXml);
		try {
			logger.info("Userinfo xml : " + returnXml);
			XMLUtil xmlUtil = new XMLUtil();
			JsonMapper mapper = JsonMapper.nonDefaultMapper();
			Map<String, Object> resutlMap = xmlUtil.parseXmlResult(returnXml,
					"/message/head/retinfo");
			String returnCode = String.valueOf(resutlMap.get(CODE));
			if (returnCode.equals(_200)) {
				xmlUtil.parseXmlData(resutlMap, returnXml,
						"/message/Body/cli_channels_qry_userinfo/tagset");
			}
			logger.info(mapper.toJson(resutlMap));
			Document document = DocumentHelper.parseText(returnXml);
			write(document, path + "Userinfo.xml");

		} catch (DocumentException e) {
			logger.error(" Userinfo error");
		} catch (IOException e) {
			logger.error(" Userinfo io error");
		}
	}

	// 增值业务查询
	public void testSpinfo() {
		String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n"
				+ "<message>\n"
				+ "  <head version=\"1.0\">\n"
				+ "    <menuid>#menuid#</menuid>\n"
				+ "    <process_code>cli_mmobile_qry_spinfo</process_code>\n"
				+ "    <verify_code>#verify_code#</verify_code>\n"
				+ "    <req_time>20140618091008</req_time>\n"
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
				+ "    <cli_mmobile_qry_spinfo>\n"
				+ "      <tagset>\n"
				+ "        <TELNUM>15002735378</TELNUM>\n"
				+ "        <queryspid></queryspid>\n"
				+ "        <process_code>cli_mmobile_qry_spinfo</process_code>\n"
				+ "        <menuid>cancelSpInfo</menuid>\n"
				+ "        <queryhismodle></queryhismodle>\n"
				+ "        <sn></sn>\n" + "        <regtype></regtype>\n"
				+ "      </tagset>\n" + "    </cli_mmobile_qry_spinfo>\n"
				+ "  </Body>\n" + "</message>";

		String returnXml = postReq(reqXml);
		try {
			logger.info("Spinfo xml : " + returnXml);
			XMLUtil xmlUtil = new XMLUtil();
			JsonMapper mapper = JsonMapper.nonDefaultMapper();
			Map<String, Object> resutlMap = xmlUtil.parseXmlResult(returnXml,
					"/message/head/retinfo");
			String returnCode = String.valueOf(resutlMap.get(CODE));
			if (returnCode.equals(_200)) {
				xmlUtil.parseXmlData(resutlMap, returnXml,
						"/message/Body/cli_mmobile_qry_spinfo/crset");
			}
			logger.info(mapper.toJson(resutlMap));
			Document document = DocumentHelper.parseText(returnXml);
			write(document, path + "Spinfo.xml");

		} catch (DocumentException e) {
			logger.error(" Spinfo error");
		} catch (IOException e) {
			logger.error(" Spinfo io error");
		}
	}

	// 随机短信码下发
	public void testSendsmsinfo() {
		String reqXml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n"
				+ "<message>\n"
				+ "  <head version=\"1.0\">\n"
				+ "   <menuid>#menuid#</menuid>\n"
				+ "    <process_code>cli_mmobile_busi_sendsmsinfo</process_code>\n"
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
				+ " </head>\n"
				+ "  <Body>\n"
				+ "    <cli_mmobile_busi_sendsmsinfo>\n"
				+ "      <tagset>\n"
				+ "        <TELNUM>15002735378</TELNUM>\n"
				+ "        <SMPARAM>1尊敬的湖北移动客户，您的本次登陆门户网站的的随机密码为：772024,五分钟有效</SMPARAM>\n"
				+ "        <process_code>cli_mmobile_busi_sendsmsinfo</process_code>\n"
				+ "      </tagset>\n" + "    </cli_mmobile_busi_sendsmsinfo>\n"
				+ " </Body>\n" + "</message>";

		String returnXml = postReq(reqXml);
		try {
			logger.info("Sendsmsinfo xml : " + returnXml);
			XMLUtil xmlUtil = new XMLUtil();
			JsonMapper mapper = JsonMapper.nonDefaultMapper();
			Map<String, Object> resutlMap = xmlUtil.parseXmlResult(returnXml,
					"/message/head/retinfo");

			logger.info(mapper.toJson(resutlMap));
			Document document = DocumentHelper.parseText(returnXml);
			write(document, path + "Sendsmsinfo.xml");

		} catch (DocumentException e) {
			logger.error(" Sendsmsinfo error");
		} catch (IOException e) {
			logger.error(" Sendsmsinfo io error");
		}
	}

	// 登陆鉴权
	public void testLogin() {
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
				+ "      <route_value>13476197487</route_value>\n"
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
				+ "        <TELNUM>13476197487</TELNUM>\n"
				+ "        <PASSWORD>123456</PASSWORD>\n"
				+ "        <ISCHECKPASS>1</ISCHECKPASS>\n"
				+ "        <process_code>cli_channels_qry_login</process_code>\n"
				+ "      </tagset>\n" + "    </cli_channels_qry_login>\n"
				+ "  </Body>\n" + "</message>";

		String returnXml = postReq(reqXml);
		try {
			logger.info("Login xml : " + returnXml);
			XMLUtil xmlUtil = new XMLUtil();
			JsonMapper mapper = JsonMapper.nonDefaultMapper();
			Map<String, Object> resutlMap = xmlUtil.parseXmlResult(returnXml,
					"/message/head/retinfo");
			String returnCode = String.valueOf(resutlMap.get(CODE));
			if (returnCode.equals(_200)) {
				xmlUtil.parseXmlData(resutlMap, returnXml,
						"/message/Body/cli_channels_qry_login/tagset");
			}
			logger.info(mapper.toJson(resutlMap));
			Document document = DocumentHelper.parseText(returnXml);
			write(document, path + "Login.xml");
		} catch (DocumentException e) {
			logger.error(" Login error");
		} catch (IOException e) {
			logger.error(" Login io error");
		}
	}

	// 信用星级查询
	public void testUserStarServAndLevel() {
		String reqXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
				+ "<message>\n"
				+ "  <head>\n"
				+ "   <menuid>#menuid#</menuid>\n"
				+ "    <process_code>cli_net_busi_Atsv_QueryUserStarServAndLevel</process_code>\n"
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
				+ "    <cli_net_busi_Atsv_QueryUserStarServAndLevel>\n"
				+ "      <tagset>\n"
				+ "        <TELNUM>15002735378</TELNUM>\n"
				+ "        <process_code>cli_net_busi_Atsv_QueryUserStarServAndLevel</process_code>\n"
				+ "      </tagset>\n"
				+ "    </cli_net_busi_Atsv_QueryUserStarServAndLevel>\n"
				+ "  </Body>\n" + "</message>";

		String returnXml = postReq(reqXml);
		try {
			logger.info("UserStarServAndLevel xml : " + returnXml);
			XMLUtil xmlUtil = new XMLUtil();
			JsonMapper mapper = JsonMapper.nonDefaultMapper();
			Map<String, Object> resutlMap = xmlUtil.parseXmlResult(returnXml,
					"/message/head/retinfo");
			String returnCode = String.valueOf(resutlMap.get(CODE));
			if (returnCode.equals(_200)) {
				xmlUtil.parseXmlData(resutlMap, returnXml,
						"/message/Body/cli_net_busi_Atsv_QueryUserStarServAndLevel/tagset");
			}
			logger.info(mapper.toJson(resutlMap));
			Document document = DocumentHelper.parseText(returnXml);
			write(document, path + "UserStarServAndLevel.xml");
		} catch (DocumentException e) {
			logger.error(" UserStarServAndLevel error");
		} catch (IOException e) {
			logger.error(" UserStarServAndLevel io error");
		}
	}

	public void testAll() {
		testLogin();
		testCustomerInfo();
		testFeeblance();
		testGprsflux();
		testPrivused();
		testQrySubsScore();
		testSpinfo();
		testUserinfo();
		testUserStarServAndLevel();
//		testSendsmsinfo();
	}

	public void write(Document document, String xmlName) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new FileWriter(xmlName), format);
		writer.write(document);
		writer.close();
	}

	public String postReq(String reqXml){
		HttpClientHelper helper = (HttpClientHelper)objPool.getObject();
		String returnXMl =  helper.post(reqXml);
		logger.info("<<<"+ helper.getBt_Cookie() +">>>" + helper);
		objPool.returnObject(helper);
		return returnXMl;
	}

}
