package com.gws.pojo;

import java.math.BigDecimal;

public class GwIntegral {
	private String 	bonusearn;			//本次销售的积分	
	private String 	traceno	;		//销售流水号	
	private 	String currentbonus;			//会员当前积分	
	private String	bonusredeem		;	//本次换购积分	
	private BigDecimal	bonus2amount	;	//	金额	
	public String getBonusearn() {
		return bonusearn;
	}
	public GwIntegral setBonusearn(String bonusearn) {
		this.bonusearn = bonusearn;return this;
	}
	public String getTraceno() {
		return traceno;
	}
	public GwIntegral setTraceno(String traceno) {
		this.traceno = traceno;return this;
	}
	public String getCurrentbonus() {
		return currentbonus;
	}
	public GwIntegral setCurrentbonus(String currentbonus) {
		this.currentbonus = currentbonus;return this;
	}
	public String getBonusredeem() {
		return bonusredeem;
	}
	public GwIntegral setBonusredeem(String bonusredeem) {
		this.bonusredeem = bonusredeem;return this;
	}
	public BigDecimal getBonus2amount() {
		return bonus2amount;
	}
	public GwIntegral setBonus2amount(BigDecimal bonus2amount) {
		this.bonus2amount = bonus2amount;return this;
	}
	
	
}
