package com.vst.host.service;

/**
* Service layer to write the business logic and throw the exception. 
*
* Inherited from : {@link : @HostServiceInterface }
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.vst.host.converter.HostConverter;
import com.vst.host.converter.SettlementConverter;
import com.vst.host.converter.WalletConverter;
import com.vst.host.dto.HostDto;
import com.vst.host.dto.SettlementDto;
import com.vst.host.dto.WalletDto;
import com.vst.host.exception.DataBaseClosedException;
import com.vst.host.exception.HostException;
import com.vst.host.exception.InValidDataException;
import com.vst.host.exception.InValidIdExcepetion;
import com.vst.host.exception.NotAcceptableException;
import com.vst.host.exception.NotFoundException;
import com.vst.host.model.Host;
import com.vst.host.model.Settlement;
import com.vst.host.model.Wallet;
import com.vst.host.repository.HostRepository;
import com.vst.host.utils.Utility;
import com.zaxxer.hikari.HikariDataSource;


@Service
public class HostServiceImpl implements HostServiceInterface {

	@Autowired
	HostRepository hostRepository;

	@Autowired
	HostConverter hostConverter;

	@Autowired
	SettlementConverter settlementConverter;

	@Autowired
	WalletConverter walletConverter;

	@Autowired
	private MongoTemplate mongoTemplate;

	Utility utility = new Utility();

	@Autowired
	public HikariDataSource dataSource;

	/**
	 * to open the database connection
	 */
	public void openConnection() {
		try {
			if (dataSource.isClosed()) {
				logger.info("DB connection open............");
				dataSource.getConnection();
			}
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage(), "cannot connect to database");
		}
	}

	/**
	 * to close the database connection
	 */
	public void closeDataSource() {
		try {
			if (!dataSource.isClosed()) {
				logger.info("DB connection closed .............");
				dataSource.close();
			}
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage(), "cannot close the DB Connection..........");
		}
	}

	public static final Logger logger = LogManager.getLogger(HostServiceImpl.class);

	/**
	 * Usage: Add new host
	 * 
	 * @param hostDto
	 * @return void(nothing)
	 * @exception : throw {@link @HostException} if any error occure while adding
	 *              new host
	 */

	@Transactional // To avoid rollback on listed exception
	@Override
	public boolean addNewHost(@Valid HostDto hostDto) {
		logger.info("HostServiceImpl :: addNewHost : execution Started");
		try {
			if (!dataSource.isClosed()) {
				if (hostDto != null) {

					Host host = hostConverter.dtoToEntity(hostDto);
					host.setHostId("HST" + utility.idGenerator());
					host.setCreatedDate(utility.dateSetter());
					host.setModifiedDate(utility.dateSetter());
					host.setHostFirstName(utility.toTitleCase(host.getHostFirstName()));
					host.setHostMiddleName(utility.toTitleCase(host.getHostMiddleName()));
					host.setHostLastName(utility.toTitleCase(host.getHostLastName()));
					host.setHostCity(utility.toTitleCase(host.getHostCity()));
					host.setActive(true);

					host.setWallets(new Wallet());
					host.setSettlements(new ArrayList<Settlement>());
					if (hostRepository.save(host) != null) {
						logger.info("HostServiceImpl :: addNewHost : execution ended");
						return true;

					} else
						throw new InValidDataException("Something went wrong, Please try Again");

				} else
					throw new InValidDataException("Host Data Cannot Be Empty. Please Check and Try Again");

			} else
				throw new DataBaseClosedException("please open database");

		} catch (InValidDataException e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidDataException(e.getLocalizedMessage());

		} catch (DataBaseClosedException e) {
			logger.error(e.getLocalizedMessage());
			throw new DataBaseClosedException(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"show the host details by Host Id", e.getLocalizedMessage());

			throw new HostException("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"show the host details by Host Id", e.getLocalizedMessage());
		}
	}

	/**
	 * Usage: add wallet or activate wallet of specific host using host id
	 * 
	 * @param hostId, walletDto
	 * @return string message "wallet addded successfully"
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public String addWallet(String hostId, WalletDto walletDto) {
		logger.info("HostServiceImpl :: addWallet : execution started");
		try {
			if (!hostId.isBlank() && hostId != null) {
				if (walletDto != null) {

					Host host = hostRepository.findByHostIdAndIsActiveTrue(utility.sanitize(hostId));
					if (host != null) {

						Wallet wallet = walletConverter.dtoToEntity(walletDto);

						wallet.setWalletId("WLT" + utility.idGenerator());
						wallet.setCreatedDate(utility.dateSetter());
						wallet.setModifiedDate(utility.dateSetter());
						wallet.setActive(true);
						host.setWallets(wallet);
						hostRepository.save(host);
						logger.info("HostServiceImpl :: addWallet : execution ended");
						return "done";
					} else {
						throw new NotFoundException("Host not available, please check and try again");
					}
				} else {
					throw new InValidDataException("Please ensure that you have provided a valid Data and try again.");
				}
			} else {
				throw new InValidIdExcepetion("entered id is null or not valid ,please enter correct id");
			}

		} catch (InValidDataException e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidDataException(e.getLocalizedMessage());

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (InValidIdExcepetion e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidIdExcepetion(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"add / activate Host wallet", e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"Add or activate Host wallet", e.getLocalizedMessage());
		}

	}

	/**
	 * Usage: add settlement in specific host using host id
	 * 
	 * @param hostId, settlementDto
	 * @return string message "settlement addded successfully"
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public String addSettlement(String hostId, SettlementDto settlementDto) {
		logger.info("HostServiceImpl :: addSettlement : execution Started");
		try {
			if (!hostId.isBlank()) {
				if (settlementDto != null) {

					Host obj = hostRepository.findByHostIdAndIsActiveTrue(utility.sanitize(hostId));

					if (obj != null) {

						Settlement settlement = settlementConverter.dtoToEntity(settlementDto);

						settlement.setSettlementId("STL" + utility.idGenerator());
						settlement.setSettlementUTR(UUID.randomUUID().toString());
						settlement.setCreatedDate(utility.dateSetter());
						settlement.setModifiedDate(utility.dateSetter());
						settlement.setActive(true);
						obj.getSettlements().add(settlement);
						hostRepository.save(obj);
						logger.info("HostServiceImpl :: addSettlement : execution ended");
						return "done";
					} else
						throw new NotFoundException("data of given id is not available in the database");

				} else
					throw new InValidDataException("Settlement details cannot be null or invalid details");

			} else
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");

		} catch (InValidDataException e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidDataException(e.getLocalizedMessage());

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (NotAcceptableException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotAcceptableException(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"add settlements in host using host id", e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"add settlement in host using host id", e.getLocalizedMessage());
		}
	}

	/**
	 * Usage: delete the specific host using host Id
	 * 
	 * @param hostId
	 * @return string message "host successfully deleted"
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public boolean remove(String hostId) {
		logger.info("HostServiceImpl :: removeHost : execution started");

		try {
			if (!hostId.isBlank() && hostId != null) {
				Host host = hostRepository.findByHostIdAndIsActiveTrue(utility.sanitize(hostId));
				if (host != null) {
					host.setActive(false);
					if (hostRepository.save(host) != null) {

						logger.info("HostServiceImpl :: removeHost : execution ended");
						return true;
					} else
						throw new InValidDataException("host is not removed, please try again.");

				} else
					throw new NotFoundException("Host not avavilable, please check and try again");

			} else
				throw new InValidIdExcepetion("Invalid ID. The ID provided is not valid. Please check and try again.");

		} catch (InValidDataException e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidDataException(e.getLocalizedMessage());

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (InValidIdExcepetion e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidIdExcepetion(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"remove host using host id", e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"remove host using host id", e.getLocalizedMessage());
		}
	}

	/**
	 * Usage: update the host details
	 * 
	 * @param hostId, hostDto
	 * @return string message "Data updated successfully"
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public HostDto edit(String hostId, HostDto hostdto) {
		logger.info("HostServiceImpl :: editHost : execution started");
		try {
			if (!hostId.isBlank() && hostId != null) {
				if (hostdto != null) {

					Host host = hostConverter.dtoToEntity(hostdto);
					Host obj = hostRepository.findByHostIdAndIsActiveTrue(utility.sanitize(hostId));
					if (obj != null) {

						boolean flag = false;

						if (host.getHostFirstName() != null && hostdto.getHostFirstName().isBlank()) {
							obj.setHostFirstName(hostdto.getHostFirstName());
							flag = true;
						}

						if (host.getHostMiddleName() != null && hostdto.getHostMiddleName().isBlank()) {
							obj.setHostMiddleName(hostdto.getHostMiddleName());
							flag = true;
						}

						if (host.getHostLastName() != null && hostdto.getHostLastName().isBlank()) {
							obj.setHostLastName(hostdto.getHostLastName());
							flag = true;
						}

						if (host.getHostEmail() != null && hostdto.getHostEmail().isBlank()) {
							obj.setHostEmail(hostdto.getHostEmail());
							flag = true;
						}

						if (host.getHostContactNo() != null && hostdto.getHostContactNo().isBlank()) {
							obj.setHostVehicleChargerType(hostdto.getHostContactNo());
							flag = true;
						}

						if (host.getHostAddress() != null && hostdto.getHostAddress().isBlank()) {
							obj.setHostAddress(hostdto.getHostAddress());
							flag = true;
						}

						if (host.getHostVehicleRegistrationNo() != null
								&& hostdto.getHostVehicleRegistrationNo().isBlank()) {
							obj.setHostVehicleRegistrationNo(hostdto.getHostVehicleRegistrationNo());
							flag = true;
						}

						if (host.getHostVehicleChargerType() != null && hostdto.getHostVehicleChargerType().isBlank()) {
							obj.setHostVehicleChargerType(hostdto.getHostVehicleChargerType());
							flag = true;
						}

						if (host.getHostCity() != null && hostdto.getHostCity().isBlank()) {
							obj.setHostCity(hostdto.getHostCity());
							flag = true;
						}

						if (hostdto.getCreatedBy() != null && hostdto.getCreatedBy().isBlank()) {
							obj.setCreatedBy(hostdto.getCreatedBy());
							flag = true;
						}

						if (hostdto.getModifiedBy() != null && hostdto.getModifiedBy().isBlank()) {
							obj.setModifiedBy(hostdto.getModifiedBy());
							flag = true;
						}

						obj.setModifiedDate(utility.dateSetter());
						if (flag == true) {
							if (hostRepository.save(obj) != null) {

								logger.info("HostServiceImpl :: editHost : execution ended");
								return hostConverter.entityToDto(obj);
							} else
								throw new InValidDataException("Host Not Updated.Please Check and Try Again");
						} else
							throw new InValidIdExcepetion("Please Check Enterd Data. And try Again");

					} else
						throw new NotFoundException("Host Not Aavailable. Please Check and Try Again");

				} else
					throw new InValidDataException("please provide Host details");

			} else
				throw new InValidIdExcepetion("entered id is null or not valid ,please enter correct id");

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (InValidIdExcepetion e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidIdExcepetion(e.getLocalizedMessage());

		} catch (InValidDataException e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidDataException(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"update host details using host id", e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"update Host detsils using host id", e.getLocalizedMessage());
		}

	}

	/**
	 * Usage: Get the host object/ details by host Id
	 * 
	 * @param hostId
	 * @return host object
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public Host show(String hostId) {
		logger.info("HostServiceImpl :: ShowHost : execution Started");
		try {
			if (!hostId.isBlank() && hostId != null) {
				Host obj = hostRepository.findByHostIdAndIsActiveTrue(hostId);
				if (obj != null) {
					logger.info("HostServiceImpl :: showHost : execution ended");
					return obj;
				} else
					throw new NotFoundException("Host not available, please check and try again.");

			} else
				throw new InValidIdExcepetion("Invalid ID. The ID provided is not valid. Please check and try again.");

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (InValidIdExcepetion e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidIdExcepetion(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(), "get host all details",
					e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(), "get Host All detsils",
					e.getLocalizedMessage());
		}
	}

	/**
	 * Usage: Get/read the details of all the available Host
	 * 
	 * @return list of host objects
	 * @throws HostException
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public List<Host> showAll() {
		logger.info("HostServiceImpl :: showAll : execution Started");
		try {
			List<Host> list = hostRepository.findAllByIsActiveTrue();
			if (!list.isEmpty()) {

				logger.info("HostServiceImpl :: showAll : execution ended");
				return list;
			} else
				throw new NotFoundException("data of given id is not available in the database");

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"Get list of all active host", e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"Get list of all active host", e.getLocalizedMessage());
		}
	}

	/**
	 * Usage: Get the list of host object/ details by host first name
	 * 
	 * @param hostFirstName
	 * @return list of host object with similar Host Name
	 * 
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public List<Host> showByHostFirstName(String hostFirstName) {
		logger.info("HostServiceImpl :: showByHostFirstName : execution Started");
		try {
			if (!hostFirstName.isBlank() && hostFirstName != null) {
				List<Host> list = hostRepository.findByHostFirstNameAndIsActiveTrue(utility.sanitize(hostFirstName));
				if (!list.isEmpty()) {

					logger.info("HostServiceImpl :: showByHostFirstName : execution ended");
					return list;
				} else
					throw new NotFoundException("Host not avavilable, please check and try again");

			} else
				throw new InValidIdExcepetion("Invalid ID. The ID provided is not valid. Please check and try again.");

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (InValidIdExcepetion e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidIdExcepetion(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get host by host first name", e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get Host by host first name", e.getLocalizedMessage());
		}
	}

	/**
	 * Usage: Get the host object/ details by host last name
	 * 
	 * @param hostLastName
	 * @return list of host object with similar Host last Name
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public List<Host> showByHostLastName(String hostLastName) {
		logger.info("HostServiceImpl :: showByHostLastName : execution Started");
		try {
			if (!hostLastName.isBlank() && hostLastName != null) {
				List<Host> list = hostRepository.findByHostLastNameAndIsActiveTrue(utility.sanitize(hostLastName));
				if (!list.isEmpty()) {

					logger.info("HostServiceImpl :: showByHostLastName : execution ended");
					return list;
				} else
					throw new NotFoundException("Host not avavilable, please check and try again");

			} else
				throw new InValidIdExcepetion("Invalid ID. The ID provided is not valid. Please check and try again.");

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (InValidIdExcepetion e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidIdExcepetion(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get host by host last name", e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get Host by host last name", e.getLocalizedMessage());
		}
	}

	/**
	 * Usage: Get the host object/ details by host email
	 * 
	 * @param hostEmail
	 * @return list of host object with similar Host email
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public List<Host> showByHostEmail(String hostEmail) {
		logger.info("HostServiceImpl :: showByHostEmail : execution started");
		try {
			if (!hostEmail.isBlank() && hostEmail != null) {
				List<Host> list = hostRepository.findByHostEmailAndIsActiveTrue(hostEmail);
				if (!list.isEmpty()) {

					logger.info("HostServiceImpl :: showByHostEmail : execution ended");
					return list;
				} else
					throw new NotFoundException("Host not avavilable, please check and try again");

			} else
				throw new InValidIdExcepetion("Invalid ID. The ID provided is not valid. Please check and try again.");

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (InValidIdExcepetion e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidIdExcepetion(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get host by host Email id", e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get Host by host Email id", e.getLocalizedMessage());
		}
	}

	/**
	 * Usage: Get the host object/ details by host city
	 * 
	 * @param hostCity
	 * @return list of host object with similar Host city
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public List<Host> showByHostCity(String hostCity) {
		logger.info("HostServiceImpl :: showByHostCity : execution started");

		try {
			if (!hostCity.isBlank() && hostCity != null) {
				List<Host> list = hostRepository.findByHostCityAndIsActiveTrue(utility.sanitize(hostCity));
				if (!list.isEmpty()) {
					logger.info("HostServiceImpl :: showByHostCity : execution ended");
					return list;
				} else
					throw new NotFoundException("Host not avavilable, please check and try again");

			} else
				throw new InValidIdExcepetion("Invalid ID. The ID provided is not valid. Please check and try again.");

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (InValidIdExcepetion e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidIdExcepetion(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(), "get host by host City",
					e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(), "get Host by host City",
					e.getLocalizedMessage());
		}
	}

	/**
	 * Usage: Get the list of settlement object/ details by host id and settlement
	 * date
	 * 
	 * @param hostId, settlementDate
	 * @return list of settlement with same date of specific host
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public List<Settlement> getByHostIdAndSettlementsDate(String hostId, String settlementDate) {
		logger.info("HostServiceImpl :: getByHostIdAndSettlementsDate : execution started");
		try {
			if (!hostId.isBlank() && hostId != null && !settlementDate.isBlank() && settlementDate != null) {

				Host host = hostRepository.findBySettlementMatching(utility.sanitize(hostId), settlementDate);

				if (host != null) {
					List<Settlement> settlements = host.getSettlements();
					if (!settlements.isEmpty()) {
						logger.info("HostServiceImpl :: getByHostIdAndSettlementsDate : execution ended");
						return settlements;

					} else
						throw new NotFoundException("settlements not found, please check and try again");

				} else
					throw new NotFoundException("Host not avavilable, please check and try again");

			} else
				throw new InValidIdExcepetion("Invalid ID. The ID provided is not valid. Please check and try again.");

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (InValidIdExcepetion e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidIdExcepetion(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get settlement by hostId and settlement date", e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get settlement by hostId and settlement date", e.getLocalizedMessage());
		}

	}

	/**
	 * Usage: Get the host object/ details by host id without settlement and wallet
	 * details
	 * 
	 * @param hostId
	 * @return host object without host settlement and host wallet details
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public Host getHostDetailsById(String hostId) {
		logger.info("HostServiceImpl :: getHostDetailsById : execution started");
		try {
			if (!hostId.isBlank() && hostId != null) {
				Host host = hostRepository.findByHostId(utility.sanitize(hostId));

				if (host != null) {
					logger.info("HostServiceImpl :: getHostDetailsById : execution ended");
					return host;
				} else
					throw new NotFoundException("Host not avavilable, please check and try again");

			} else
				throw new InValidIdExcepetion("Invalid ID. The ID provided is not valid. Please check and try again.");

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (InValidIdExcepetion e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidIdExcepetion(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get only host deatils by host id", e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get only host details by host id", e.getLocalizedMessage());
		}
	}

	/**
	 * Usage: Get list of settlement using host id details
	 * 
	 * @param hostId
	 * @return settlement list
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public List<Settlement> getSettlementByHostId(String hostId) {
		logger.info("HostServiceImpl :: getSettlementByHostId : execution Started");
		try {
			if (hostId != null && !hostId.isBlank()) {
				Host host = hostRepository.findSettlementByHostId(hostId);
				if (host != null) {

					List<Settlement> settlements = host.getSettlements();
					List<Settlement> finaList = new ArrayList<>();
					if (!settlements.isEmpty()) {
						for (Settlement settlement : settlements) {
							if (settlement.isActive() == true) {
								finaList.add(settlement);
							}
						}
						if (!finaList.isEmpty()) {
							logger.info("HostServiceImpl :: getSettlementByHostId : execution ended");
							return finaList;
						} else
							throw new NotFoundException(
									"Settlements Not Found, settlements with provided Id does not exist");

					} else
						throw new InValidDataException(
								"Settlements Not Found, settlements with provided Id does not exist");
				} else
					throw new NotFoundException("Host not Found, please provide correct Host Id and try again");

			} else
				throw new InValidIdExcepetion("Invalid ID. The ID provided is not valid. Please check and try again.");

		} catch (NotFoundException e) {
			logger.error(e.getLocalizedMessage());
			throw new NotFoundException(e.getLocalizedMessage());

		} catch (InValidIdExcepetion e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidIdExcepetion(e.getLocalizedMessage());

		} catch (InValidDataException e) {
			logger.error(e.getLocalizedMessage());
			throw new InValidDataException(e.getLocalizedMessage());

		} catch (Exception e) {

			logger.error("HST001", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get all settlement deatils of specific by host id", e.getLocalizedMessage());

			throw new HostException("502", "ManageHost", e.getStackTrace()[0].getClassName(),
					e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber(),
					"get all settlements details of specific by host id", e.getLocalizedMessage());
		}

	}

}
