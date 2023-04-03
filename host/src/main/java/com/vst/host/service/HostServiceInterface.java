package com.vst.host.service;

import java.util.List;

import com.vst.host.dto.HostDto;
import com.vst.host.dto.SettlementDto;
import com.vst.host.dto.WalletDto;
import com.vst.host.model.Host;
import com.vst.host.model.Settlement;
import com.vst.host.model.Wallet;

public interface HostServiceInterface {

	public String add(HostDto hostdto);

	public Host show(String hostId);

	public List<Host> showAll();

	public List<Host> showByHostFirstName(String hostFirstName);

	public List<Host> showByHostLastName(String hostLastName);

	public List<Host> showByHostEmail(String hostEmail);

	public List<Host> showByHostVehicleRegNo(String hostVehicleRegNo);

	public List<Host> showByHostVehicleChargerType(String hostVehicleChargerType);

	public List<Host> showByHostCreatedBy(String createdBy);

	public List<Host> showByHostCity(String hostCity);

	public List<Host> showByHostModifiedBy(String modifiedBy);

	public List<Host> showByFullName(String hostFirstName, String hostMiddleName, String hostLastName);

	public void remove(String hostId);

	public HostDto edit(String hostId, HostDto hostdto);

	// mapping methods

	public String addSettlement(String hostId, SettlementDto settlementDto);

	public String addWallet(String hostId, WalletDto walletDto);

	public List<Settlement> getByHostIdAndSettlementDate(String hostId, String settlementDate);

	public List<Settlement> getByHostIdAndSettlementsDate1(String hostId, String settlementDate);

	Host getHostDetailsById(String hostId);
}
