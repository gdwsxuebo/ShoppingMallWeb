package com.gws.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 收银机公有配置
 */
@Entity
@Table(name = "pos_common_conifg")
public class PosCommonConfigModel implements Serializable {
	private static final long serialVersionUID = -1862237745244571445L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "ssl_on", nullable = false, length = 1)
	private String SSL_ON; // 0:专网 1：公网
	@Column(name = "ip_pos", nullable = false, length = 30)
	private String ipPos; // cpos IP
	@Column(name = "port", nullable = false, length = 12)
	private String port;// PORT 端口值
	@Column(name = "term_info", nullable = false, length = 255)
	private String term_info;// 终端机型
	@Column(name = "baud_rate", nullable = false, length = 12)
	private String baudRate;// 波特率
	@Column(name = "ssl_sn", nullable = false, length = 39)
	private String ssl_sn;// 硬件序列号
	@Column(name = "ssl_cert", nullable = false, length = 255)
	private String ssl_cert;// 数字证书路径
	@Column(name = "tpdu", nullable = false, length = 255)
	private String tpdu;// TPDU值
	@Column(name = "devpath", nullable = false, length = 255)
	private String devPath;// pos串口号

	@Column(name = "conid", nullable = false, length = 32)
	private String conid; // 数据库识别码 自留-----
	@Column(name = "utime", nullable = false, length = 32)
	private String utime;// 修改或创建时间 ------

//	@Column(name = "screen_style", nullable = false, length = 1)
//	private String screenStyle;// 单双屏幕选择

	// 后期扩展字段预留 现没有用
	@Column(name = "exparam1", nullable = true, length = 255)
	private String exparam1;// 状态 ----
	@Column(name = "exparam2", nullable = true, length = 255)
	private String exparam2;// 状态-----

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSSL_ON() {
		return SSL_ON;
	}

	public void setSSL_ON(String sSL_ON) {
		SSL_ON = sSL_ON;
	}

	public String getIpPos() {
		return ipPos;
	}

	public void setIpPos(String ipPos) {
		this.ipPos = ipPos;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTerm_info() {
		return term_info;
	}

	public void setTerm_info(String term_info) {
		this.term_info = term_info;
	}

	public String getBaudRate() {
		return baudRate;
	}

	public void setBaudRate(String baudRate) {
		this.baudRate = baudRate;
	}

	public String getSsl_sn() {
		return ssl_sn;
	}

	public void setSsl_sn(String ssl_sn) {
		this.ssl_sn = ssl_sn;
	}

	public String getSsl_cert() {
		return ssl_cert;
	}

	public void setSsl_cert(String ssl_cert) {
		this.ssl_cert = ssl_cert;
	}

	public String getTpdu() {
		return tpdu;
	}

	public void setTpdu(String tpdu) {
		this.tpdu = tpdu;
	}

	public String getDevPath() {
		return devPath;
	}

	public void setDevPath(String devPath) {
		this.devPath = devPath;
	}

	public String getConid() {
		return conid;
	}

	public void setConid(String conid) {
		this.conid = conid;
	}

	public String getUtime() {
		return utime;
	}

	public void setUtime(String utime) {
		this.utime = utime;
	}

	public String getExparam1() {
		return exparam1;
	}

	public void setExparam1(String exparam1) {
		this.exparam1 = exparam1;
	}

	public String getExparam2() {
		return exparam2;
	}

	public void setExparam2(String exparam2) {
		this.exparam2 = exparam2;
	}

}
