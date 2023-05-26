package com.vst.host.model;

/**
* it is a container class which contain the data of application.
*
* Inherited from : {@link : @Settlement , @Wallet}
* 
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "host")
public class Host {

	@Id
	private String hostId;
	private String hostFirstName;
	private String hostMiddleName;
	private String hostLastName;
	private String hostEmail;
	private String hostContactNo;
	private String hostAddress;
	private String hostVehicleRegistrationNo;
	private String hostVehicleChargerType;
	private String hostCity;
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive ;
	private Wallet wallets;
	private List<Settlement> settlements;
	
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	public String getHostFirstName() {
		return hostFirstName;
	}
	public void setHostFirstName(String hostFirstName) {
		this.hostFirstName = hostFirstName;
	}
	public String getHostMiddleName() {
		return hostMiddleName;
	}
	public void setHostMiddleName(String hostMiddleName) {
		this.hostMiddleName = hostMiddleName;
	}
	public String getHostLastName() {
		return hostLastName;
	}
	public void setHostLastName(String hostLastName) {
		this.hostLastName = hostLastName;
	}
	public String getHostEmail() {
		return hostEmail;
	}
	public void setHostEmail(String hostEmail) {
		this.hostEmail = hostEmail;
	}
	public String getHostContactNo() {
		return hostContactNo;
	}
	public void setHostContactNo(String hostContactNo) {
		this.hostContactNo = hostContactNo;
	}
	public String getHostAddress() {
		return hostAddress;
	}
	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}
	public String getHostVehicleRegistrationNo() {
		return hostVehicleRegistrationNo;
	}
	public void setHostVehicleRegistrationNo(String hostVehicleRegNo) {
		this.hostVehicleRegistrationNo = hostVehicleRegNo;
	}
	public String getHostVehicleChargerType() {
		return hostVehicleChargerType;
	}
	public void setHostVehicleChargerType(String hostVehicleChargerType) {
		this.hostVehicleChargerType = hostVehicleChargerType;
	}
	public String getHostCity() {
		return hostCity;
	}
	public void setHostCity(String hostCity) {
		this.hostCity = hostCity;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
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
	public Wallet getWallets() {
		return wallets;
	}
	public void setWallets(Wallet wallets) {
		this.wallets = wallets;
	}
	public List<Settlement> getSettlements() {
		return settlements;
	}
	public void setSettlements(List<Settlement> settlements) {
		this.settlements = settlements;
	}
	
	public Host(String hostId, String hostFirstName, String hostMiddleName, String hostLastName, String hostEmail,
			String hostContactNo, String hostAddress, String hostVehicleRegNo, String hostVehicleChargerType,
			String hostCity, Date createdDate, Date modifiedDate, String createdBy, String modifiedBy,
			boolean isActive, Wallet wallets, List<Settlement> settlements) {
		super();
		this.hostId = hostId;
		this.hostFirstName = hostFirstName;
		this.hostMiddleName = hostMiddleName;
		this.hostLastName = hostLastName;
		this.hostEmail = hostEmail;
		this.hostContactNo = hostContactNo;
		this.hostAddress = hostAddress;
		this.hostVehicleRegistrationNo = hostVehicleRegNo;
		this.hostVehicleChargerType = hostVehicleChargerType;
		this.hostCity = hostCity;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.isActive = isActive;
		this.wallets = wallets;
		this.settlements = settlements;
	}
	
	public Host() {
		super();
	}
	
	@Override
	public String toString() {
		return "Host [hostId=" + hostId + ", hostFirstName=" + hostFirstName + ", hostMiddleName=" + hostMiddleName
				+ ", hostLastName=" + hostLastName + ", hostEmail=" + hostEmail + ", hostContactNo=" + hostContactNo
				+ ", hostAddress=" + hostAddress + ", hostVehicleRegNo=" + hostVehicleRegistrationNo
				+ ", hostVehicleChargerType=" + hostVehicleChargerType + ", hostCity=" + hostCity + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", isActive=" + isActive + ", wallets=" + wallets + ", settlements=" + settlements
				+ ", getHostId()=" + getHostId() + ", getHostFirstName()=" + getHostFirstName()
				+ ", getHostMiddleName()=" + getHostMiddleName() + ", getHostLastName()=" + getHostLastName()
				+ ", getHostEmail()=" + getHostEmail() + ", getHostContactNo()=" + getHostContactNo()
				+ ", getHostAddress()=" + getHostAddress() + ", getHostVehicleRegNo()=" + getHostVehicleRegistrationNo()
				+ ", getHostVehicleChargerType()=" + getHostVehicleChargerType() + ", getHostCity()=" + getHostCity()
				+ ", getCreatedDate()=" + getCreatedDate() + ", getModifiedDate()=" + getModifiedDate()
				+ ", getCreatedBy()=" + getCreatedBy() + ", getModifiedBy()=" + getModifiedBy() + ", isActive()="
				+ isActive() + ", getWallets()=" + getWallets() + ", getSettlements()=" + getSettlements()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
	
}
