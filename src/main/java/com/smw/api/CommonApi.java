package com.smw.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.smw.common.des.UnEncryptData;
import com.smw.common.util.BasePageResultVo;
import com.smw.common.util.ResponseCode;
import com.smw.common.util.StringUtil;
import com.smw.common.util.StructureUtil;
import com.smw.common.util.aes.AES;
import com.smw.common.util.rsa.RSAHandle;
import com.smw.interceptor.exception.BaseException;

/**
 * @project: GDWS
 * @author:文豪 E-mail:wenhaoboy@gmail.com
 * @version QQ:403665586 2016-04-16
 */
@Controller
@RequestMapping("/")
public class CommonApi extends BaseException {
	// 日志
	private Logger logger = LoggerFactory.getLogger(CommonApi.class);

	/**
	 * 接口同一业务逻辑层
	 */
	@Resource(name = "webapiServiceManage")
	private HandlETC webapiServiceImpl;

	/**
	 * 
	 * @author wenhao 统一公用对外接口 YHBS -用户标识 、XTLB-系统标识
	 *         、DYLX-调用类型、DATA-jsonstring数据
	 */
	@RequestMapping(value = "/webapi",method=RequestMethod.POST)
	@ResponseBody
	public BasePageResultVo getRquestAPI(String YHBS, String XTLB, String DYLX, String DATA,
			HttpServletRequest request) {
		BasePageResultVo remes = null;
		try {
			if (YHBS == null || YHBS.length() < 1 || XTLB == null || XTLB.length() < 1 || DYLX == null
					|| DYLX.length() < 1) {
				String[] status = { ResponseCode.BASE_ERROR, "API请求错误", "", StructureUtil.getUrl(request) };
				remes = StructureUtil.returnObject(null, status);
				return remes;
			}
			//解码
			//YHBS = new String(Base64.decodeBase64(YHBS));
			//XTLB = new String(Base64.decodeBase64(XTLB));
			//DYLX = new String(Base64.decodeBase64(DYLX));
			
			//替换、解码、解密
			//YHBS = new String(UnEncryptData.createUnEncryptData(Base64.decodeBase64(YHBS)));
			//XTLB = new String(UnEncryptData.createUnEncryptData(Base64.decodeBase64(XTLB)));
			//DYLX = new String(UnEncryptData.createUnEncryptData(Base64.decodeBase64(DYLX)));
			
			//销售不解密，因为销售数据量过大解密会失败！
			if (!StringUtil.isNUllStr(DATA) && !CallParameters.SAVE_SALES_SUMMARY.equals(DYLX)) {
				//DATA = new String(Base64.decodeBase64(DATA),"UTF-8");
				//DATA = new String(UnEncryptData.createUnEncryptData(Base64.decodeBase64(DATA)),"UTF-8");
			}

			// 判断接口请求是否合法
			if (!YHBS.equals("E84E7273764D2D77E9FC4E9C521E8C3750")) {
				String[] status = { ResponseCode.BASE_ERROR, "用户验证错误", "",
						""/* StructureUtil.getUrl(request) */ };
				remes = StructureUtil.returnObject(null, status);
				return remes;
			}
			// HandlETC h = new HandlETC(gwTranssalestotalServiceImpl);
			// remes = h.verification(DYLX,DATA,request) ;

			remes = webapiServiceImpl.verification(DYLX, DATA, request);
			return remes;
		} catch (Exception e) {
			String[] status = { ResponseCode.SYS_ERROR, "系统错误！", "", "" };
			remes = StructureUtil.returnObject(null, status);
			e.printStackTrace();
			// 输出日志
			logger.error("接口" + DYLX + " " + e.getMessage());
			return remes;
		}
	}
}
