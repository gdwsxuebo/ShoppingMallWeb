package com.smw.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smw.common.util.DateUtil;

/**
 * 翼支付
 * @author suen
 * @date 2016年6月27日-上午11:20:09
 * @version  jdk1.8
 */
@Entity
@Table(name="bestoay")
public class Bestoay implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5833068161205310368L;
	
	/**
	 * 客户端条形码
	 */
	@Id
	@Column(precision = 30, scale = 0)
	private BigDecimal barcode;
	
	/**
	 * 订单号
	 */
	@Column(nullable=false,length=30)
	private String orderNo=DateUtil.getCurrRanStr();
	
	/**
	 * 订单请求交易流水号
	 */
	@Column(nullable=false,length=30)
	private String orderReqNo=DateUtil.getCurrRanStr();
	
	/**
	 * 创建日期
	 */
	@Column(nullable=false)
	private Date createDate=new Date();
	
	/**
	 * 订单总金额
	 */
	@Column(precision = 10, scale = 2)
	private BigDecimal orderAmt;
	
	/**
	 * 产品金额
	 */
	@Column(precision = 10, scale = 2)
	private BigDecimal productAmt;
	
	/**
	 * 附加金额
	 */
	@Column(precision = 10, scale = 2)
	private BigDecimal attachAmt;
	
	/**
	 * 商品名称，多个以“、”隔开
	 */
	@Column(length=256)
	private String xfItemNames;
	
	/**
	 * 商铺号
	 */
	@Column(length=36)
	private String xfStoreCode;
	
	/**
	 * 交易状态
	 */
	@Column(length=1)
	private Character transStatus='A';
	
	/**
	 * 关联的销售单号
	 */
	@Column(length=50)
	private String ssTxdocno;
	
	/**
	 * 翼支付交易号
	 */
	@Column(length=30)
	private String ourTransNo;
	
	/**
	 * 退款标识 0代表未退款 1 已退款2部分退款 3已冲正
	 */
	@Column(length=1)
	private String refundFlag="0";
	
	/**
	 * 客户条码消费时的支付手机号，中间4位被隐去，用*代替如180****2687
	 */
	@Column(nullable=true,length=11)
	private String customerId;
	
	public BigDecimal getBarcode() {
		return barcode;
	}

	public void setBarcode(BigDecimal barcode) {
		this.barcode = barcode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderReqNo() {
		return orderReqNo;
	}

	public void setOrderReqNo(String orderReqNo) {
		this.orderReqNo = orderReqNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}

	public BigDecimal getProductAmt() {
		return productAmt;
	}

	public void setProductAmt(BigDecimal productAmt) {
		this.productAmt = productAmt;
	}

	public BigDecimal getAttachAmt() {
		return attachAmt;
	}

	public void setAttachAmt(BigDecimal attachAmt) {
		this.attachAmt = attachAmt;
	}
	
	public String getXfItemNames() {
		return xfItemNames;
	}

	public void setXfItemNames(String xfItemNames) {
		this.xfItemNames = xfItemNames;
	}

	public String getXfStoreCode() {
		return xfStoreCode;
	}

	public void setXfStoreCode(String xfStoreCode) {
		this.xfStoreCode = xfStoreCode;
	}

	public Character getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(Character transStatus) {
		this.transStatus = transStatus;
	}

	public String getSsTxdocno() {
		return ssTxdocno;
	}

	public void setSsTxdocno(String ssTxdocno) {
		this.ssTxdocno = ssTxdocno;
	}

	public String getOurTransNo() {
		return ourTransNo;
	}

	public void setOurTransNo(String ourTransNo) {
		this.ourTransNo = ourTransNo;
	}

	public String getRefundFlag() {
		return refundFlag;
	}

	public void setRefundFlag(String refundFlag) {
		this.refundFlag = refundFlag;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
