package com.gws.pojo;

public class Tender {
	private String tendercode;//支付方式编码
	private String tenderdesc;//支付方式名称
	private String isInvalid;
	
	public String getIsInvalid() {
		return isInvalid;
	}
	public Tender setIsInvalid(String isInvalid) {
		this.isInvalid = isInvalid;return this;
	}
	public String getTendercode() {
		return tendercode;
	}
	public Tender setTendercode(String tendercode) {
		this.tendercode = tendercode;return this;
	}
	public String getTenderdesc() {
		return tenderdesc;
	}
	public Tender setTenderdesc(String tenderdesc) {
		this.tenderdesc = tenderdesc;return this;
	}
	public boolean equals(Object obj) {
		if(!(obj instanceof Tender)) return false;
		Tender t=(Tender) obj;
		return t.tenderdesc.equals(this.tenderdesc) ?true:false;
	}

	
}
