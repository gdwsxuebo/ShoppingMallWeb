package com.gws.pojo;

public class Item {
	private String style;//商品id
	private String desci;//商品名字
	private String storecode;//商品所属店铺
	private String itemOrgid;
	private String isInvalid;
	
	public String getItemOrgid() {
		return itemOrgid;
	}
	public Item setItemOrgid(String itemOrgid) {
		this.itemOrgid = itemOrgid;return this;
	}
	public String getIsInvalid() {
		return isInvalid;
	}
	public Item setIsInvalid(String isInvalid) {
		this.isInvalid = isInvalid;return this;
	}
	public String getStyle() {
		return style;
	}
	public Item setStyle(String style) {
		this.style = style;return this;
	}
	public String getDesci() {
		return desci;
	}
	public Item setDesci(String desci) {
		this.desci = desci;return this;
	}

	public String getStorecode() {
		return storecode;
	}
	public Item setStorecode(String storecode) {
		this.storecode = storecode;return this;
	}
	public boolean equals(Object obj) {
		if(!(obj instanceof Item)) return false;
		Item i=(Item) obj;
		return i.desci.equals(this.desci) && i.storecode.equals(this.storecode)&& i.itemOrgid.equals(this.storecode) ?true:false;
	}

	
}
