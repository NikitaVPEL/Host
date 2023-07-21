package com.vst.host.service;

/**
* service interface 

*
* *Inherited in : {@link : @HostServiceImpl}
*
* @author snehal matke <snehal.matke@vpel.in>
* @since  31/05/2023
*/

import java.util.List;

import com.mongodb.client.FindIterable;
import com.vst.host.dto.HostDto;
import com.vst.host.dto.SettlementDto;
import com.vst.host.dto.WalletDto;
import com.vst.host.model.Host;
import com.vst.host.model.Settlement;
import com.vst.host.model.Wallet;

public interface HostServiceInterface {

	public boolean addNewHost(HostDto hostdto);

	public Host show(String hostId);

	public List<Host> showAll();

	public List<Host> showByHostFirstName(String hostFirstName);

	public List<Host> showByHostLastName(String hostLastName);

	public Host showByHostEmail(String hostEmail);
	
	public Host showByHostContactNo(String hostContactNo);

	public List<Host> showByHostCity(String hostCity);

//	public List<Host> showByHostCreatedBy(String createdBy);
//
//	public List<Host> showByHostModifiedBy(String modifiedBy);

//	public List<Host> showByFullName(String hostFirstName, String hostMiddleName, String hostLastName);

	public boolean remove(String hostId);

	public HostDto edit(String hostId, HostDto hostdto);

	// mapping methods

	public String addSettlement(String hostId, SettlementDto settlementDto);

	public String addWallet(String hostId, WalletDto walletDto);

//	public List<Settlement> getByHostIdAndSettlementDate(String hostId, String settlementDate);

	public List<Settlement> getByHostIdAndSettlementsDate(String hostId, String settlementDate);

	public Host getHostDetailsById(String hostId);
	
	public List<Settlement> getSettlementByHostId(String hostId);
	
	public void createNewHostByContactNo(String phoneNumber);
	
    public HostDto updateHostByContactNo(String hostContactNo, HostDto hostdto);
    
    public HostDto updatePasswordByContactNo(String hostContactNo, HostDto hostdto);
        
    public String getPasswordByContactNo(String hostContactNo);
    
    public String getPasswordByEmail(String hostEmail);




	


	
}
