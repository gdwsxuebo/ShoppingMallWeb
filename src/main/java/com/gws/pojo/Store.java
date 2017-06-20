package com.gws.pojo;

public class Store {
private String mallid;//购物中心ID
private String storecode;//店铺编码
private String storename;//店铺名称
private String isInvalid;

public String getIsInvalid() {
	return isInvalid;
}
public Store setIsInvalid(String isInvalid) {
	this.isInvalid = isInvalid;return this;
}
public String getMallid() {
	return mallid;
}
public Store setMallid(String mallid) {
	this.mallid = mallid;return this;
}
public String getStorecode() {
	return storecode;
}
public Store setStorecode(String storecode) {
	this.storecode = storecode;return this;
}
public String getStorename() {
	return storename;
}
public Store setStorename(String storename) {
	this.storename = storename;return this;
}
public boolean equals(Object obj) {
	if(!(obj instanceof Store)) return false;
	Store store=(Store) obj;
	return store.mallid.equals(this.mallid) && store.storename.equals(this.storename)?true:false;
}

public static void main(String[] args) {
	Store s1=new Store().setStorecode("1");
	Store s2=new Store().setStorecode("1");
	System.out.println(s1.hashCode()+"  "+s2.hashCode());
	if(s1.equals(s2)) System.out.println("1");
	else System.out.println("2");
 }
}
