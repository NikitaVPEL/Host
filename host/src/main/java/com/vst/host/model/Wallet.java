package com.vst.host.model;

import org.springframework.data.annotation.Id;

public class Wallet {

	@Id
	private String walletId;
	private String walletAmount;
	private String walletCurrency;
	private String walletStatus;
	private String walletType;
	private String walletPaymentType;
	private String walletHistory;
	private String createDate;
	private String modifiedDate;
	private String createdBy;
	private String modifiedBy;
	boolean isActive;
	
	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	public String getWalletAmount() {
		return walletAmount;
	}
	public void setWalletAmount(String walletAmount) {
		this.walletAmount = walletAmount;
	}
	public String getWalletCurrency() {
		return walletCurrency;
	}
	public void setWalletCurrency(String walletCurrency) {
		this.walletCurrency = walletCurrency;
	}
	public String getWalletStatus() {
		return walletStatus;
	}
	public void setWalletStatus(String walletStatus) {
		this.walletStatus = walletStatus;
	}
	public String getWalletType() {
		return walletType;
	}
	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}
	public String getWalletPaymentType() {
		return walletPaymentType;
	}
	public void setWalletPaymentType(String walletPaymentType) {
		this.walletPaymentType = walletPaymentType;
	}
	public String getWalletHistory() {
		return walletHistory;
	}
	public void setWalletHistory(String walletHistory) {
		this.walletHistory = walletHistory;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", walletAmount=" + walletAmount + ", walletCurrency=" + walletCurrency
				+ ", walletStatus=" + walletStatus + ", walletType=" + walletType + ", walletPaymentType="
				+ walletPaymentType + ", walletHistory=" + walletHistory + ", createDate=" + createDate
				+ ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", isActive=" + isActive + "]";
	}
	
	public Wallet(String walletId, String walletAmount, String walletCurrency, String walletStatus, String walletType,
			String walletPaymentType, String walletHistory, String createDate, String modifiedDate, String createdBy,
			String modifiedBy, boolean isActive) {
		super();
		this.walletId = walletId;
		this.walletAmount = walletAmount;
		this.walletCurrency = walletCurrency;
		this.walletStatus = walletStatus;
		this.walletType = walletType;
		this.walletPaymentType = walletPaymentType;
		this.walletHistory = walletHistory;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.isActive = isActive;
	}
	
	public Wallet() {
		super();
	}

	
}
