package com.smw.bestoay;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;

import com.smw.common.util.DateUtil;
import com.smw.common.util.MD5;
import com.smw.common.util.PropertiesUtil;

/**
 * 翼支付
 * 
 * @author suen
 * @date 2016年6月27日-上午9:39:15
 * @version jdk1.8
 */
public class BestoayHandle {
	// 日志
	private static Logger logger = LoggerFactory.getLogger(BestoayHandle.class);
	
	// 商户号 由翼支付网关平台统一分配
	private static String merchantId = "";
	// 此处是商户的key
	private static String key = "";
	// 商户调用密码
	private static String merchantPwd = "";
	
	static{
		PropertiesUtil p = new PropertiesUtil("systemConfig.properties");
		merchantId=p.readProperty("merchantId");
		key=p.readProperty("key");
		merchantPwd=p.readProperty("merchantPwd");
	}

	public static void main(String[] args) throws Exception {
		String data=new Date().getTime()+"5";
		String data2=new Date().getTime()+"2";
		System.out.println(data+"  "+data2);
		/*String placeOrder = placeOrder(data,data2 
				, "510171588464785255", "10000", "10000", "0", "测试", "123", null);
		System.out.println(placeOrder);*/
	}
	/**
	 * 获取签名
	 * @param ORDERNO  订单号
	 * @param ORDERREQNO 订单请求交易流水号
	 * @param ORDERDATE 交易日期
	 * @param OURTRANSNO 翼支付交易号
	 * @param TRANSAMT 订单金额
	 * @param TRANSSTATUS 交易状态
	 * @param ENCODETYPE 签名方式
	 * @return
	 */
	public static String getSign(String ORDERNO,String ORDERREQNO,String ORDERDATE
			,String OURTRANSNO,String TRANSAMT,String TRANSSTATUS,String ENCODETYPE){
		StringBuilder sb=new StringBuilder();
		sb.append("MERCHANTID="+merchantId);
		sb.append("&ORDERNO="+ORDERNO);
		sb.append("&ORDERREQNO="+ORDERREQNO);
		sb.append("&ORDERDATE="+ORDERDATE);
		sb.append("&OURTRANSNO="+OURTRANSNO);
		sb.append("&TRANSAMT="+TRANSAMT);
		sb.append("&TRANSSTATUS="+TRANSSTATUS);
		sb.append("&ENCODETYPE="+ENCODETYPE);
		sb.append("&KEY="+key);
		return MD5.getMD5(sb.toString());
	}
	
	/**
	 * 统一下单
	 * 
	 * @param orderNo
	 *            订单号 由商户平台提供，数字或字母组成
	 * @param orderReqNo
	 *            订单请求交易流水号 由商户平台提供，数字或字母组成
	 * @param barcode
	 *            条形码号 商户POS扫描用户客户端条形码
	 * @param orderAmt
	 *            订单总金额 单位：分 订单总金额 = 产品金额+附加金额
	 * @param productAmt
	 *            产品金额 单位：分
	 * @param attachAmt
	 *            附加金额 单位：分
	 * @param goodsName
	 *            商品名称 商品信息
	 * @param storeId
	 *            门店号
	 * @param backUrl
	 *            后台返回地址 商户提供的用于异步接收交易返回结果的后台url，商户可根据需要填写
	 * @return
	 * @throws Exception
	 */
	public static String placeOrder(String orderNo, String orderReqNo, String barcode, String orderAmt,
			String productAmt, String attachAmt, String goodsName, String storeId, String backUrl) {
		try {
			// 商户号 由翼支付网关平台统一分配
			// private static String merchantId = "043101180050000";
			// 订单号 由商户平台提供，数字或字母组成
			// String orderNo = "1433734609522";
			// 订单请求交易流水号 由商户平台提供，数字或字母组成
			// String orderReqNo = "14337346095221";
			// 订单日期 yyyyMMddhhmmss
			String orderDate = DateUtil.format(new Date(), "yyyyMMddhhmmss");
			// 条形码号 商户POS扫描用户客户端条形码
			// String barcode = "512582454851204002";
			// 订单总金额 单位：分 订单总金额 = 产品金额+附加金额
			// String orderAmt = "1";

			StringBuilder sb = new StringBuilder();// 组装mac加密明文串
			sb.append("MERCHANTID=").append(merchantId);
			sb.append("&ORDERNO=").append(orderNo);
			sb.append("&ORDERREQNO=").append(orderReqNo);
			sb.append("&ORDERDATE=").append(orderDate);
			sb.append("&BARCODE=").append(barcode);
			sb.append("&ORDERAMT=").append(orderAmt);
			sb.append("&KEY=").append(key);// 此处是商户的key

			String mac = MD5.getMD5(sb.toString());// 进行md5加密(商户自己封装MD5加密工具类，此处只提供参考)

			Map<String, String> param = new HashMap<String, String>();// 组装请求参数
			// 商户号
			param.put("merchantId", merchantId);
			// 子商户号
			param.put("subMerchantId", merchantId);
			// 条形码号
			param.put("barcode", barcode);
			// 订单号
			param.put("orderNo", orderNo);
			// 订单请求交易流水号
			param.put("orderReqNo", orderReqNo);
			// 订单日期
			param.put("orderDate", orderDate);
			// 渠道 默认填：05
			param.put("channel", "05");
			// 业务类型 默认填：0001
			param.put("busiType", "0001");
			// 注意 ：文档没说明
			param.put("TransType", "B");
			// 订单总金额 单位：分 订单总金额 = 产品金额+附加金额
			param.put("orderAmt", orderAmt);
			// 产品金额 单位：分
			param.put("productAmt", productAmt);
			// 附加金额 单位：分
			param.put("attachAmt", attachAmt);
			// 商品名称 商品信息
			param.put("goodsName", goodsName);
			// 门店号
			param.put("storeId", storeId);
			// 后台返回地址 商户提供的用于异步接收交易返回结果的后台url，商户可根据需要填写
			if (backUrl != null) {
				param.put("backUrl", backUrl);
			}
			// 分账信息 商户需要在结算时进行分账情况，需填写此字段，详情见接口说明
			param.put("ledgerDetail", "");
			// 附加信息 商户附加信息
			param.put("attach", "");
			// MAC校验域 采用标准的MD5算法，由商户实现
			param.put("mac", mac);

			// 创建信任证书
			CloseableHttpClient httpClient = createSSLClientDefault();
			HttpPost httpPost = null;
			CloseableHttpResponse response = null;
			try {
				httpPost = new HttpPost("https://webpaywg.bestpay.com.cn/barcode/placeOrder");
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
				httpPost.setConfig(RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build());
				response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				int statusCode = response.getStatusLine().getStatusCode();
				if (HttpStatus.SC_OK == statusCode) {// 如果响应码是200
					return EntityUtils.toString(entity);
				}
			} finally {
				if (response != null) {
					response.close();
				}
				if (httpPost != null) {
					httpPost.releaseConnection();
				}
				httpClient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("翼支付统一下单 " + e.getMessage());
		}
		return null;
	}

	/**
	 * 查询订单
	 * 
	 * @param orderNo
	 *            订单号 由商户平台提供，数字或字母组成
	 * @param orderReqNo
	 *            订单请求交易流水号 由商户平台提供，数字或字母组成
	 * @param orderDate
	 *            订单交易日期
	 * @return
	 * @throws Exception
	 */
	public static String queryOrder(String orderNo, String orderReqNo, String orderDate) {
		try {
			StringBuilder sb = new StringBuilder();// 组装mac加密明文串
			sb.append("MERCHANTID=").append(merchantId);
			sb.append("&ORDERNO=").append(orderNo);
			sb.append("&ORDERREQNO=").append(orderReqNo);
			sb.append("&ORDERDATE=").append(orderDate);
			sb.append("&KEY=").append(key);// 此处是商户的key

			String mac = MD5.getMD5(sb.toString());// 进行md5加密(商户自己封装MD5加密工具类，此处只提供参考)

			Map<String, String> param = new HashMap<String, String>();// 组装请求参数，参数名大小写敏感

			param.put("merchantId", merchantId);
			param.put("orderNo", orderNo);
			param.put("orderReqNo", orderReqNo);
			param.put("orderDate", orderDate);
			param.put("mac", mac);

			// 创建信任证书
			CloseableHttpClient httpClient = createSSLClientDefault();
			HttpPost httpPost = null;
			CloseableHttpResponse response = null;
			try {
				// 发起HTTP的POST请求
				httpPost = new HttpPost("https://webpaywg.bestpay.com.cn/query/queryOrder");
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// UTF8+URL编码
				httpPost.setEntity(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
				httpPost.setConfig(RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build());
				response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				int statusCode = response.getStatusLine().getStatusCode();
				if (HttpStatus.SC_OK == statusCode) {// 如果响应码是200
					return EntityUtils.toString(entity);
				}
			} finally {
				if (response != null) {
					response.close();
				}
				if (httpPost != null) {
					httpPost.releaseConnection();
				}
				httpClient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("翼支付查询 " + e.getMessage());
		}
		return null;
	}

	/**
	 * 退款
	 * 
	 * @param oldOrderNo
	 *            原扣款订单号
	 * @param oldOrderReqNo
	 *            原订单请求支付流水号
	 * @param refundReqNo
	 *            退款流水号
	 * @param transAmt
	 *            退款交易金额 单位为分，小于等于原订单金额
	 * @param ledgerDetail
	 *            分账明细 分账规则：父商户可以全额退款，子商户的分账退款金额必须小于支付分账金额，分账金额不能为0。
	 * @return
	 * @throws Exception
	 */
	public static String commonRefund(String oldOrderNo, String oldOrderReqNo, String refundReqNo, String transAmt,
			String ledgerDetail){
		try {
			// 退款请求日期
			String refundReqDate = DateUtil.format(new Date(), "yyyyMMdd");
			// 渠道 01：WEB 02：WAP 04：语音 05：客户端
			String channel = "05";

			StringBuilder sb = new StringBuilder();// 组装mac加密明文串
			sb.append("MERCHANTID=").append(merchantId);
			sb.append("&MERCHANTPWD=").append(merchantPwd);
			sb.append("&OLDORDERNO=").append(oldOrderNo);
			sb.append("&OLDORDERREQNO=").append(oldOrderReqNo);
			sb.append("&REFUNDREQNO=").append(refundReqNo);
			sb.append("&REFUNDREQDATE=").append(refundReqDate);
			sb.append("&TRANSAMT=").append(transAmt);
			sb.append("&LEDGERDETAIL=").append(ledgerDetail);
			sb.append("&KEY=").append(key);// 此处是商户的key

			String mac = MD5.getMD5(sb.toString());// 进行md5加密(商户自己封装MD5加密工具类，此处只提供参考)

			Map<String, String> param = new HashMap<String, String>();// 组装请求参数

			param.put("merchantId", merchantId);
			param.put("merchantPwd", merchantPwd);
			param.put("oldOrderNo", oldOrderNo);
			param.put("oldOrderReqNo", oldOrderReqNo);
			param.put("refundReqNo", refundReqNo);
			param.put("refundReqDate", refundReqDate);
			param.put("transAmt", transAmt);
			param.put("channel", channel);
			if (ledgerDetail!=null) {
				param.put("ledgerDetail", ledgerDetail);
			}
			param.put("mac", mac);

			// 创建信任证书
			CloseableHttpClient httpClient = createSSLClientDefault();
			HttpPost httpPost = null;
			CloseableHttpResponse response = null;
			try {
				httpPost = new HttpPost("https://webpaywg.bestpay.com.cn/refund/commonRefund");
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
				httpPost.setConfig(RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build());
				response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				int statusCode = response.getStatusLine().getStatusCode();
				if (HttpStatus.SC_OK == statusCode) {// 如果响应码是200
					return EntityUtils.toString(entity);
				}
			} finally {
				if (response != null) {
					response.close();
				}
				if (httpPost != null) {
					httpPost.releaseConnection();
				}
				httpClient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("翼支付退款 "+e.getMessage());
		}
		return null;
	}

	// 创建链接
	public static CloseableHttpClient createSSLClientDefault() throws Exception {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new AllTrustStrategy()).build();
		SSLConnectionSocketFactory sslSf = new SSLConnectionSocketFactory(sslContext);
		return HttpClients.custom().setSSLSocketFactory(sslSf).build();
	}

	// 加载证书
	private static class AllTrustStrategy implements TrustStrategy {

		public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			return true;
		}
	}

}
