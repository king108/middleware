package com.hbztc.middleware.controller;
/**
 * 描述：增值业务统一查询
 * @author Mark
 * @date 2014-6-30
 */
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/spinfo")
public class SpinfoController{
	private static Logger logger = LoggerFactory
			.getLogger(SpinfoController.class);

	@RequestMapping("qrySpinfo")
	@ResponseBody
	public String qryspinfo(HttpServletRequest request,
			HttpServletResponse response) throws HttpException, IOException {
	
		return 
				"{\n" +
				"\"code\": \"200\",\n" + 
				"\"info\": \"success\",\n" + 
				"\"list\": [\n" + 
				"        {\n" + 
				"             \"Col1\": \"21\",\n" + 
				"              \"Col2\": \"698026\",\n" + 
				"              \"Col3\": \"中国移动\",\n" + 
				"              \"Col5\": \"YDWB\",\n" + 
				"              \"Col6\": \"移动微博\",\n" + 
				"              \"Col7\": \"41\",\n" + 
				"              \"Col8\": \"0\",\n" + 
				"              \"Col9\": \"0\",\n" + 
				"              \"Col10\": \"2011-12-27 04:34:28\",\n" + 
				"              \"Col11\": \"2013-01-18 18:40:26\",\n" + 
				"              \"Col12\": \"1\",\n" + 
				"              \"Col13\": \"MBLG\",\n" + 
				"              \"Col14\": \"1\",\n" + 
				"              \"Col15\": \"08\",\n" + 
				"              \"Col16\": \"中国移动\",\n" + 
				"              \"Col17\": \"移动微博\",\n" + 
				"              \"Col20\": \"G\",\n" + 
				"              \"Col21\": \"0\",\n" + 
				"              \"Col23\": \"270\",\n" + 
				"              \"Col25\": \"1\",\n" + 
				"              \"Col26\": \"0元\",\n" + 
				"              \"Col27\": \"0\",\n" + 
				"              \"Col28\": \"DelEndImmed\"\n" + 
				"},\n" + 
				"        {\n" + 
				"\"Col1\": \"21\",\n" + 
				"              \"Col2\": \"698026\",\n" + 
				"              \"Col3\": \"中国移动\",\n" + 
				"              \"Col5\": \"YDWB\",\n" + 
				"              \"Col6\": \"移动微博\",\n" + 
				"              \"Col7\": \"41\",\n" + 
				"              \"Col8\": \"0\",\n" + 
				"              \"Col9\": \"0\",\n" + 
				"              \"Col10\": \"2011-12-27 04:34:28\",\n" + 
				"              \"Col11\": \"2013-01-18 18:40:26\",\n" + 
				"              \"Col12\": \"1\",\n" + 
				"              \"Col13\": \"MBLG\",\n" + 
				"              \"Col14\": \"1\",\n" + 
				"              \"Col15\": \"08\",\n" + 
				"              \"Col16\": \"中国移动\",\n" + 
				"              \"Col17\": \"移动微博\",\n" + 
				"              \"Col20\": \"G\",\n" + 
				"              \"Col21\": \"0\",\n" + 
				"              \"Col23\": \"270\",\n" + 
				"              \"Col25\": \"1\",\n" + 
				"              \"Col26\": \"0元\",\n" + 
				"              \"Col27\": \"0\",\n" + 
				"              \"Col28\": \"DelEndImmed\"\n" + 
				"}]\n" + 
				"}";
	}

	private String createXml(String tel) {
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head><menuid/><process_code>cli_mmobile_qry_spinfo</process_code><verify_code/><req_time>20140604111932</req_time><req_seq/><unicontact/><testflag/><route><route_type>1</route_type><route_value>15002735378</route_value></route><channelinfo><operatorid/><channelid>bsacWap</channelid><unitid>bsacWap</unitid></channelinfo></head><Body><cli_mmobile_qry_spinfo><tagset><TELNUM>"+tel+"</TELNUM><queryspid/><process_code>cli_mmobile_qry_spinfo</process_code><queryhismodle/><sn/><regtype/></tagset></cli_mmobile_qry_spinfo></Body></message>";
	}

	public String getDemoXml() {
		String demoXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head version=\"1.0\"><menuid/><process_code>cli_mmobile_qry_spinfo</process_code><verify_code/><resp_time>20140604111939</resp_time><sequence><req_seq/><operation_seq/></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[ Processing the request succeeded!]]></retmsg></retinfo></head><Body><cli_mmobile_qry_spinfo><crset><row><Col1>21</Col1><Col2>698026</Col2><Col3>中国移动</Col3><Col4/><Col5>YDWB</Col5><Col6>移动微博</Col6><Col7>41</Col7><Col8>0</Col8><Col9>0</Col9><Col10>2011-12-2704: 34: 28</Col10><Col11>2013-01-1818: 40: 26</Col11><Col12>1</Col12><Col13>MBLG</Col13><Col14>1</Col14><Col15>08</Col15><Col16>中国移动</Col16><Col17>移动微博</Col17><Col18/><Col19/><Col20>G</Col20><Col21>0</Col21><Col22/><Col23>270</Col23><Col24/><Col25>1</Col25><Col26>0元</Col26><Col27>0</Col27><Col28>DelEndImmed</Col28></row><row><Col1>21</Col1><Col2>801234</Col2><Col3>中国移动</Col3><Col4>10658000</Col4><Col5>112434</Col5><Col6>手机报-ChinaDaily</Col6><Col7>05</Col7><Col8>2</Col8><Col9>5000</Col9><Col10>2013-03-1915: 19: 46</Col10><Col11>2013-03-1915: 19: 46</Col11><Col12>1</Col12><Col13>DSMP</Col13><Col14>1</Col14><Col15>08</Col15><Col16>中国移动</Col16><Col17>中国日报社提供的双语新闻类产品</Col17><Col18/><Col19>10086</Col19><Col20>G</Col20><Col21>0</Col21><Col22/><Col23>270</Col23><Col24/><Col25>2</Col25><Col26>5元/月</Col26><Col27>0</Col27><Col28>DelEndImmed</Col28></row><row><Col1>21</Col1><Col2>701001</Col2><Col3>中国移动</Col3><Col4/><Col5>700137325000</Col5><Col6>游戏玩家特惠版</Col6><Col7>28</Col7><Col8>2</Col8><Col9>0</Col9><Col10>2014-01-0722: 06: 21</Col10><Col11>2014-01-0722: 06: 22</Col11><Col12>1</Col12><Col13>CMGP</Col13><Col14>1</Col14><Col15>03</Col15><Col16>中国移动</Col16><Col17>游戏玩家特惠版</Col17><Col18/><Col19>-1</Col19><Col20/><Col21>0</Col21><Col22/><Col23>270</Col23><Col24/><Col25>3</Col25><Col26>0元/月</Col26><Col27>0</Col27><Col28>DelEndImmed</Col28></row><row><Col1>22</Col1><Col2>CMCC</Col2><Col3>中国移动</Col3><Col4/><Col5>FetionMember</Col5><Col6>飞信会员</Col6><Col7/><Col8>元/月</Col8><Col9>5000</Col9><Col10>2012-08-0100: 27: 59</Col10><Col11>2012-08-0100: 27: 59</Col11><Col12>1</Col12><Col13/><Col14>1</Col14><Col15/><Col16>中国移动</Col16><Col17/><Col18/><Col19>10086</Col19><Col20/><Col21/><Col22/><Col23>270</Col23><Col24/><Col25>4</Col25><Col26>5元/月</Col26><Col27>1</Col27><Col28>DelEndImmed</Col28></row><row><Col1>22</Col1><Col2>CMCC</Col2><Col3>中国移动</Col3><Col4/><Col5>G130102</Col5><Col6>音乐俱乐部高级会员</Col6><Col7/><Col8>元/月</Col8><Col9>5000</Col9><Col10>2014-02-2820: 01: 44</Col10><Col11>2014-02-2820: 01: 44</Col11><Col12>1</Col12><Col13/><Col14>1</Col14><Col15/><Col16>中国移动</Col16><Col17/><Col18/><Col19>10086</Col19><Col20/><Col21/><Col22/><Col23>270</Col23><Col24/><Col25>5</Col25><Col26>5元/月</Col26><Col27>1</Col27><Col28>DelEndCycle</Col28></row><row><Col1>22</Col1><Col2>CMCC</Col2><Col3>中国移动</Col3><Col4/><Col5>SELFRING</Col5><Col6>彩铃</Col6><Col7/><Col8/><Col9/><Col10>2014-03-2020: 53: 28</Col10><Col11>2014-03-2020: 53: 28</Col11><Col12>1</Col12><Col13/><Col14>1</Col14><Col15/><Col16>中国移动</Col16><Col17/><Col18/><Col19>10086</Col19><Col20/><Col21/><Col22/><Col23>270</Col23><Col24/><Col25>6</Col25><Col26>6元/月</Col26><Col27>0</Col27><Col28>DelEndImmed</Col28></row><row><Col1>22</Col1><Col2>CMCC</Col2><Col3>中国移动</Col3><Col4/><Col5>CMMPAY</Col5><Col6>手机支付</Col6><Col7/><Col8>元</Col8><Col9>0</Col9><Col10>2012-06-2814: 19: 07</Col10><Col11>2012-06-2814: 19: 07</Col11><Col12>1</Col12><Col13/><Col14>1</Col14><Col15/><Col16>中国移动</Col16><Col17/><Col18/><Col19>10086</Col19><Col20/><Col21/><Col22/><Col23>270</Col23><Col24/><Col25>7</Col25><Col26>0元</Col26><Col27>0</Col27><Col28>DelEndImmed</Col28></row><row><Col1>22</Col1><Col2>CMCC</Col2><Col3>中国移动</Col3><Col4/><Col5>150</Col5><Col6>GPRS业务</Col6><Col7/><Col8>包月</Col8><Col9>0</Col9><Col10>2013-11-1100: 00: 00</Col10><Col11>2013-11-1100: 00: 00</Col11><Col12>1</Col12><Col13/><Col14>1</Col14><Col15/><Col16>中国移动</Col16><Col17/><Col18/><Col19>10086</Col19><Col20/><Col21/><Col22/><Col23>270</Col23><Col24/><Col25>8</Col25><Col26>0元/月</Col26><Col27>1</Col27><Col28>DelEndImmed</Col28></row><row><Col1>22</Col1><Col2>CMCC</Col2><Col3>中国移动</Col3><Col4/><Col5>FREEPIM</Col5><Col6>免费和通讯录服务</Col6><Col7/><Col8>包月</Col8><Col9>0</Col9><Col10>2014-03-2622: 09: 47</Col10><Col11>2014-03-2622: 09: 47</Col11><Col12>1</Col12><Col13/><Col14>1</Col14><Col15/><Col16>中国移动</Col16><Col17/><Col18/><Col19>10086</Col19><Col20/><Col21/><Col22/><Col23>270</Col23><Col24/><Col25>9</Col25><Col26>0元/月</Col26><Col27>0</Col27><Col28>DelEndImmed</Col28></row><row><Col1>32</Col1><Col2>CMCC</Col2><Col3>中国移动</Col3><Col4/><Col5>pg.vo.vpmn.xy</Col5><Col6>校园V网</Col6><Col7/><Col8/><Col9/><Col10>2012-09-1615: 51: 24</Col10><Col11>2012-09-1615: 51: 24</Col11><Col12>1</Col12><Col13/><Col14>1</Col14><Col15/><Col16>中国移动</Col16><Col17/><Col18/><Col19>10086</Col19><Col20/><Col21/><Col22/><Col23>270</Col23><Col24/><Col25>10</Col25><Col26>V网</Col26><Col27>0</Col27><Col28/></row><row><Col1>1</Col1><Col2>917340</Col2><Col3>杭州诚智天扬科技有限公司</Col3><Col4>106584838</Col4><Col5>-ZHDXJLB</Col5><Col6>短信俱乐部</Col6><Col7>04</Col7><Col8>0</Col8><Col9>0</Col9><Col10>2014-05-2222: 45: 04</Col10><Col11>2014-05-2222: 45: 05</Col11><Col12>1</Col12><Col13>DSMP</Col13><Col14>1</Col14><Col15>08</Col15><Col16>中国移动</Col16><Col17/><Col18/><Col19/><Col20>L</Col20><Col21>0</Col21><Col22/><Col23>270</Col23><Col24>由中国移动代收费</Col24><Col25>11</Col25><Col26>0元</Col26><Col27>0</Col27><Col28>DelEndImmed</Col28></row></crset></cli_mmobile_qry_spinfo></Body></message>";
		return demoXml;
	}
}
