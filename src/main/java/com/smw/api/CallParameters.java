package com.smw.api;

/**
@project: GDWS
@author:文豪 E-mail:wenhaoboy@gmail.com
@version QQ:403665586   2016-04-16
API接口调用类型
*/
public class CallParameters {

	/**
	 * 验证店铺是否有效
	 */
	public static final String VALID_STORE ="VALID_STORE" ;
	
	/**
	 * 用户登录
	 */
	public static final String XFSTAFF_LOGIN="XFSTAFF_LOGIN";
	
	/**
	 * 平板更新状态
	 */
	public static final String XFTILLID_STATE="XFTILLID_STATE";
	
	/**
	 * 销售总结
	 */
	public static final String SALES_SUMMARY="SALES_SUMMARY";
	
	/**
	 * 获取商场信息
	 */
	public static final String GET_MALL="GET_MALL";
	
	/**
	 * 获取商铺信息
	 */
	public static final String GET_STORE="GET_STORE";
	
	/**
	 * 获取付款方式
	 */
	public static final String GET_TENDER="GET_TENDER";
	
	/**
	 * 获取员工数量
	 */
	public static final String GET_STAFF_COUNT="GET_STAFF_COUNT";
	
	/**
	 * 获取商品信息数量
	 */
	public static final String GET_XFITEM="GET_XFITEM";
	
	/**
	 * 保存销售记录
	 */
	public static final String SAVE_SALES_SUMMARY="SAVE_SALES_SUMMARY";
	
	/**
	 * 获取店铺下的员工信息集合
	 */
	public static final String GET_XFSTAFF_BY_STORECODE="GET_XFSTAFF_BY_STORECODE";

	/**
	 * 获取销售数据
	 */
	public static final String GET_SALES_CONSUME="GET_SALES_CONSUME";

	/**
	 * 获取会员信息
	 */
	public static final String GET_VIP_INFO="GET_VIP_INFO";
	
	/**
	 * 验证许可证是否过时
	 */
	public static final String VERIFY_LICENSE="VERIFY_LICENSE";
	
	/**
	 * 验证是否满足促销规则
	 */
	public static final String VERIFICATION_PROMOTIONRULE="VERIFICATION_PROMOTIONRULE";
	
	/**
	 * 判断促销优惠劵
	 */
	public static final String VERIFICATION_PROMOTIONSTAMP="VERIFICATION_PROMOTIONSTAMP";
	
	/**
	 * 翼支付，统一下单
	 */
	public static final String BESTOAY_PLACEORDER="BESTOAY_PLACEORDER";
	
	/**
	 * 翼支付，查询订单
	 */
	public static final String BESTOAY_QUERYORDER="BESTOAY_QUERYORDER";
	
	/**
	 * 翼支付，退款
	 */
	public static final String BESTOAY_COMMONREFUND="BESTOAY_COMMONREFUND";
	
	/**
	 * 获取小票设置
	 */
	public static final String GET_PRINTRECEIPT="GET_PRINTRECEIPT";
	
	/**
	 * 销售的付款方式补齐
	 */
	public static final String SALESSUMMARY_SALESTEND_COMPLETE="SALESSUMMARY_SALESTEND_COMPLETE";
	
	/**
	 * 退货权限
	 */
	public static final String RETURN_GOODS_AUTHORITY="RETURN_GOODS_AUTHORITY";
	
	/**
	 * 获取老的销售数据
	 */
	public static final String GET_OLD_SALESSUMMARY="GET_OLD_SALESSUMMARY";
	
	/**
	 * 获取新的销售数据
	 */
	public static final String GET_NEW_SALESSUMMARY="GET_NEW_SALESSUMMARY";
	
	/**
	 * 获取客显文件路径
	 */
	public static final String GET_GUEST_SHOWFILE="GET_GUEST_SHOWFILE";
	
	/**
	 * 获取最新APK
	 */
	public static final String GET_CPOS_APK="GET_CPOS_APK";
	
	/**
	 * 修改密码
	 */
	public static final String STAFF_EDIT_PWD="STAFF_EDIT_PWD";
	/**
	 * 通过收银机ID获取店铺信息
	 */
	public static final String GET_STORE_TILLID="GET_STORE_TILLID";

	public static final String WIFI_PROBE = "WIFI_PROBE";
	/**
	 * 获取收银机公用配置信息
	 */
	public static final String GET_POS_CONFIG="GET_POS_CONFIG"; 
	/**
	 * 员工、支付方式、商品、收银机配置信息数据跟新状态
	 */
	public static final String DATA_REVISION="DATA_REVISION";
	
	/**
	 * 收银机权限
	 */
	public static final String TILL_AUTH = "TILL_AUTH";
	
	/**
	 * 支付方式手续费率
	 * */
	public static final String PAYMENT_RENT="PAYMENT_RENT_DEL";

	public static final String GET_CPOS_UPDATE = "GET_CPOS_UPDATE";
	
	
	/**
	 * 获取小票的logo和二维码
	 */
	public static final String GET_PRINTRECEIPT_LOGOEWM="GET_PRINTRECEIPT_LOGOEWM";
	
	/**
	 *
	 * 获取支付平台地址
	 */
	public static final String GET_GDWS_PAYURL="GET_GDWS_PAYURL";
	
}