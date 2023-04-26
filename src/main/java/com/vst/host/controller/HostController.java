package com.vst.host.controller;

/**
* Controller to accept the http Request 
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vst.host.dto.HostDto;
import com.vst.host.dto.SettlementDto;
import com.vst.host.dto.WalletDto;
import com.vst.host.model.Host;
import com.vst.host.model.Settlement;
import com.vst.host.service.HostServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("manageHost")
public class HostController {

	@Autowired
	HostServiceImpl hostServiceImpl;

	/**
	 * Usage: Add new host
	 * 
	 * HTTP method : POST and URL : manageHost/addHost
	 * 
	 * @param hostDto
	 * @return Http responce
	 */
	@PostMapping("/addHost")
	public ResponseEntity<String> addHost(@Valid @RequestBody HostDto hostDto) {
		hostServiceImpl.addNewHost(hostDto);

		log.info("HostController :: addHost : Request Body {HostDto} ");
		return new ResponseEntity<>("host data added successfully", HttpStatus.OK);
	}

	/**
	 * Usage: Get the host object/ details by host Id
	 * 
	 * HTTP method : GET and URL : manageHost/getHost
	 * 
	 * @param hostId
	 * @return Http responce and host object
	 */
	@GetMapping("/getHost")
	public ResponseEntity<Host> getHost(@RequestParam("hostId") String hostId) {
		log.info("HostController :: getHost : Request Param {HostID} ");
		return ResponseEntity.ok(hostServiceImpl.show(hostId));
	}

	/**
	 * Usage: Get/read the details of all the available Host
	 * 
	 * HTTP method : GET and URL : manageHost/getHosts
	 * 
	 * @return Http responce and list of host object
	 */
	@GetMapping("/getHosts")
	public ResponseEntity<List<Host>> getAllHost() {
		log.info("HostController :: getAllHost ");
		return ResponseEntity.ok(hostServiceImpl.showAll());
	}

	/**
	 * Usage: Get the list of host object/ details by host first name
	 * 
	 * HTTP method : GET and URL : manageHost/getHostFirstName
	 * 
	 * @param hostFirstName
	 * @return Http responce and list of host object with similar Host Name
	 */
	@GetMapping("/getHostFirstName")
	public ResponseEntity<List<Host>> getByHostFirstName(@RequestParam("hostFirstName") String hostFirstName) {
		log.info("HostController :: getByHostFirstName : Request Param {hostFirstName} ");
		return ResponseEntity.ok(hostServiceImpl.showByHostFirstName(hostFirstName));
	}

	/**
	 * Usage: Get the host object/ details by host last name
	 * 
	 * HTTP method : GET and URL : manageHost/getHostLastName
	 * 
	 * @param hostLastName
	 * @return Http responce and list of host object with similar Host last Name
	 */
	@GetMapping("/getHostLastName")
	public ResponseEntity<List<Host>> getByHostLastName(@RequestParam("hostLastName") String hostLastName) {
		log.info("HostController :: getHostLastName : Request Param {hostLastName} ");
		return ResponseEntity.ok(hostServiceImpl.showByHostLastName(hostLastName));
	}

	/**
	 * Usage: Get the host object/ details by host email
	 * 
	 * HTTP method : GET and URL : manageHost/getHostEmail
	 * 
	 * @param hostEmail
	 * @return Http responce and list of host object with similar Host email
	 */
	@GetMapping("/getHostEmail")
	public ResponseEntity<List<Host>> getByEmail(@RequestParam("hostEmail") String hostEmail) {
		log.info("HostController :: getByEmail : Request Param {hostEmail} ");

		return ResponseEntity.ok(hostServiceImpl.showByHostEmail(hostEmail));
	}

	/**
	 * Usage: Get the host object/ details by host city
	 * 
	 * HTTP method : GET and URL : manageHost/getHostCity
	 * 
	 * @param hostCity
	 * @return Http responce and list of host object with similar Host city
	 */
	@GetMapping("/getHostCity")
	public ResponseEntity<List<Host>> getByHostCity(@RequestParam("hostCity") String hostCity) {

		log.info("HostController :: getByHostCity : Request Param {hostCity} ");
		return ResponseEntity.ok(hostServiceImpl.showByHostCity(hostCity));
	}

	/**
	 * Usage: Get the host object/ details by created date
	 * 
	 * HTTP method : GET and URL : manageHost/getCreatedBy
	 * 
	 * @param createdBy
	 * @return Http responce and list of host object with similar created date
	 */
	@GetMapping("/getCreatedBy")
	public ResponseEntity<List<Host>> getByCreatedBy(@RequestParam("createdBy") String createdBy) {

		log.info("HostController :: getByCreatedBy : Request Param {createdBy} ");
		return ResponseEntity.ok(hostServiceImpl.showByHostCreatedBy(createdBy));
	}

	/**
	 * Usage: Get the host object/ details by modified date
	 * 
	 * HTTP method : GET and URL : manageHost/getModifiedBy
	 * 
	 * @param modifiedBy
	 * @return Http responce and list of host object with similar modified date
	 */
	@GetMapping("/getModifiedBy")
	public ResponseEntity<List<Host>> getByModifiedBy(@RequestParam("modifiedBy") String modifiedBy) {

		log.info("HostController :: getByModifiedBy : Request Param {modifiedBy} ");
		return ResponseEntity.ok(hostServiceImpl.showByHostModifiedBy(modifiedBy));
	}

	/**
	 * Usage: Get the host object/ details by host full name
	 * 
	 * HTTP method : GET and URL : manageHost/getHostFullName
	 * 
	 * @param hostFirstName, hostMiddleName, hostLastName
	 * @return Http responce and list of host object with similar hostFullName
	 */
	@GetMapping("/getHostFullName")
	public ResponseEntity<List<Host>> getByFullName(@RequestParam("hostFirstName") String hostFirstName,
			@RequestParam("hostMiddleName") String hostMiddleName, @RequestParam("hostLastName") String hostLastName) {

		log.info("HostController :: getByFullName : Request Param {hostFirstName}, {hostMiddleName}, {hostLastName} ");
		return ResponseEntity.ok(hostServiceImpl.showByFullName(hostFirstName, hostMiddleName, hostLastName));
	}

	/**
	 * Usage: delete the specific host using host Id
	 * 
	 * HTTP method : DELETE and URL : manageHost/deleteHost
	 * 
	 * @param hostId
	 * @return Http responce and string message "host successfully deleted"
	 */
	@DeleteMapping("/deleteHost")
	public ResponseEntity<String> deleteHost(@RequestParam("hostId") String hostId) {

		log.info("HostController :: deleteHost : Request Param {hostId} ");
		hostServiceImpl.remove(hostId);
		return new ResponseEntity<>("host successfully deleted", HttpStatus.OK);
	}

	/**
	 * Usage: update the host details
	 * 
	 * HTTP method : PUT and URL : manageHost/updateHost
	 * 
	 * @param hostId, hostDto
	 * @return Http responce and string message "Data updated successfully"
	 */
	@PutMapping("/updateHost")
	public ResponseEntity<String> updateHost(@RequestParam("hostId") String hostId, @RequestBody HostDto hostDto) {

		log.info("HostController :: updateHost : Request Body {hostDto}, Request Param {hostId} ");
		hostServiceImpl.edit(hostId, hostDto);
		return new ResponseEntity<>("Data updated successfully", HttpStatus.OK);
	}

	/**
	 * Usage: add settlement in specific host using host id
	 * 
	 * HTTP method : POST and URL : manageHost/addSettlement
	 * 
	 * @param hostId, settlementDto
	 * @return Http responce and string message "settlement addded successfully"
	 */

	@PostMapping("/addSettlement")
	public ResponseEntity<String> addSettlement(@Valid @RequestParam("hostId") String hostId,
			@RequestBody SettlementDto settlementDto) {

		log.info("HostController :: addSettlement : Request Body {settlementDto}, Request Param {hostId} ");
		hostServiceImpl.addSettlement(hostId, settlementDto);
		return new ResponseEntity<>("settlement added successfully", HttpStatus.OK);
	}

	/**
	 * Usage: add wallet or activate wallet of specific host using host id
	 * 
	 * HTTP method : POST and URL : manageHost/addWallet
	 * 
	 * @param hostId, WalletDto
	 * @return Http responce string message "wallet Added Successfully"
	 */

	@PostMapping("/addWallet")
	public ResponseEntity<String> addWallet(@RequestParam("hostId") String hostId,
			@Valid @RequestBody WalletDto walletDto) {

		log.info("HostController :: addWallet : Request Body {WalletDto}, Request Param {hostId} ");
		hostServiceImpl.addWallet(hostId, walletDto);
		return new ResponseEntity<>("wallet added successfully", HttpStatus.OK);
	}

//	@GetMapping("/getSettlement1")
//	public ResponseEntity<List<Settlement>> getBySettlementDate(@RequestParam("hostId") String hostId,
//			@RequestParam("getSettlementByDate") String settlementDate) {
//
//		return ResponseEntity.ok(hostServiceImpl.getByHostIdAndSettlementDate(hostId, settlementDate));
//	}

	/**
	 * Usage: Get the list of settlement object/ details by host id and settlement
	 * date
	 * 
	 * HTTP method : GET and URL : manageHost/getSettlementByDate
	 * 
	 * @param hostId, settlementDate
	 * @return Http responce and list of settlement with same date of specific host
	 */

	@GetMapping("/getSettlementByDate")
	public List<Settlement> getSettlementsByDate(@RequestParam("hostId") String hostId,
			@RequestParam("settlementByDate") String settlementDate) {

		log.info("HostController :: getSettlementsByDate : Request Param {hostId}{settlementDate} ");
		List<Settlement> settlements = hostServiceImpl.getByHostIdAndSettlementsDate(hostId, settlementDate);
		return settlements;
	}

	/**
	 * Usage: Get the host object/ details by host id without settlement and wallet
	 * details
	 * 
	 * HTTP method : GET and URL : manageHost/getHostDetails
	 * 
	 * @param hostId
	 * @return Http responce and host object without host settlement and host wallet
	 *         details
	 */

	@GetMapping("/getHostDetails")
	public ResponseEntity<Host> getHostDetails(@RequestParam("hostId") String hostId) {

		log.info("HostController :: getHostDetails : Request Param {hostId} ");
		return ResponseEntity.ok(hostServiceImpl.getHostDetailsById(hostId));
	}

}
