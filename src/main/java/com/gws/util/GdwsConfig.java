package com.gws.util;



public interface GdwsConfig {
	
	public final static String BaseUrl=StringUtil.getNsyhInfo("newCrmUrl");
	
	public final static String loginUrl=BaseUrl+"/crmApi/loginCRMApi.do";
	
	public final static String registerUrl=BaseUrl+"/crmApi/registerMember.do";
	
	public final static String updateUrl=BaseUrl+"/crmApi/updateMember.do";
	
	public final static String vipInfoByVipCode=BaseUrl+"/crmApi/getMemberByVipCode.do";
	
	public final static String bindCard=BaseUrl+"/crmApi/bindCardCode.do";
	
	public final static String getMI=BaseUrl+"/crmApi/getMemberIntegral.do";
	
	public final static String addOrReduceMI=BaseUrl+"/crmApi/addOrReduceMemberIntegral.do";
	
	public final static String MIDetail=BaseUrl+"/crmApi/getMemberIntegralDetailList.do";
	
	public final static String MIByPhone=BaseUrl+"/crmApi/getMemberByPhone.do";
	
	public final static String MIByCardCodeOrVipCode=BaseUrl+"/crmApi/getMemberByCardCodeOrVipCode.do";
	
	public final static String MiSales=BaseUrl+"/crmApi/verification.do";
	
	public final static String cardRule="^.*$";
	
	public final static String vipRule="^5[0-9]{17}$";
	
	public final static String phoneRule="^1[34578]\\d{9}$";
	
	public final static String account=StringUtil.getNsyhInfo("gdws.crm.account");
	
	public final static  int CrmSocketTime=15000;
	
	public final static  int CrmConnectTime=15000;
	
	public final static int CrmrequestTime=15000;
	
	public final static String DeMiSales=BaseUrl+"/crmApi/returnGoods.do";

	public static final int MisSocketTime = 15000;

	public static final int MisConnectTime =15000;

	public static final int MisRequsetTime =15000;
	
	public static final String MisAccount=StringUtil.getNsyhInfo("gdws.mis.account");
	
	public static final String MisPassword=StringUtil.getNsyhInfo("gdws.mis.password");
	
	public static final String MisSynch=StringUtil.getNsyhInfo("gdws.mallid.synch");
	
	public final static String MisBaseUrl=StringUtil.getNsyhInfo("misUrl");
	
	public final static String MisLoginUrl=MisBaseUrl+"/misApi/getToken.do";
	/**old**/
	public final static String MisGetTender=MisBaseUrl+"/misApi/getPaymentTenderListByMallInfoCode.do";

	public static final String MisGetValidStore = MisBaseUrl+"/misApi/getRealShopsLease.do";
	
	public static final String MisUploadTrans=MisBaseUrl+"/misApi/uploadTranssaleTotal.do";
	
	public static final String MisStore_Item=MisBaseUrl+"/misApi/getGoodsInfoByShopsLeaseCode.do";
	
	/**获取支付方式手续费率**/
	public static final String MisGetPaymentRent = MisBaseUrl+"/misApi/getPaymentRent.do";

	/**获取业态信息*/
	public static final String GetFormatInfo=MisBaseUrl+"/misApi/getFormatsTreeList.do";
	
	/**获取商铺手续费率**/
	public static final String MisGetSalesReturnDate = MisBaseUrl+"/misApi/getSalesReturnDateList.do";
	/**获取楼宇信息 */
	public static final String GetBuildingInfo=MisBaseUrl+"/misApi/getBuildingTreeList.do";
	
	/**获取收银机信息**/
	public static final String GetMISTillids=MisBaseUrl+"/misApi/getCashRegisterList.do";
	
	/**获取收银机关联的店铺**/
	public static final String GetMISTillid_Store=MisBaseUrl+"/misApi/getCashRegisterShopsList.do";
	
	/**
	 * 连接GDPS  
	 * 
	 */
	public final static  int GdpsSocketTime=15000;
	
	public final static  int GdpsConnectTime=15000;
	
	public final static int GdpsRequestTime=15000;

	public static final String GdpsAccount=StringUtil.getNsyhInfo("gdws.gdps.account");
	
	public static final String GdpsPassword=StringUtil.getNsyhInfo("gdws.gdps.password");
	//授权编码
	public final static String GdpsEncoding =StringUtil.getNsyhInfo("gdws.gdps.encoding");
	
	public final static String GdpsUrl=StringUtil.getNsyhInfo("gdpsUrl");
	//获取token
	public final static String GdpsLoginUrl=GdpsUrl+"/gdpsApi/getTokenByGdwf.do";
	//获取优惠劵
	public final static String GdpsGetCouponUrl=GdpsUrl+"/gdpsApi/getCoupon.do";
	//验证优惠卷
	public final static String GdpsCheckCouponUrl=GdpsUrl+"/gdpsApi/verifyCouponSale.do";
	
	// 获取BASE64
	public final static String GetImageBase64 = GdpsUrl + "/gdpsApi/getPic.do";

	// 促销平台优惠券验证
	public final static String CheckPromotionCodes = GdpsUrl + "/gdpsApi/verifyCouponArrSale.do";
	
	//促销平台普通礼品券验证
	public final static String CheckPromotionCodesPtCode = GdpsUrl + "/gdpsApi/verifyCouponGift.do";
	
	
}
