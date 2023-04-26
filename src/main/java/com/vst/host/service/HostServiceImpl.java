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
import com.vst.host.exception.HostException;
import com.vst.host.exception.MethodFailureException;
import com.vst.host.exception.NotAcceptableException;
import com.vst.host.exception.NotFoundException;
import com.vst.host.model.Host;
import com.vst.host.model.Settlement;
import com.vst.host.model.Wallet;
import com.vst.host.repository.HostRepository;
import com.vst.host.utils.Utility;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

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
	public void addNewHost(@Valid HostDto hostDto) {
		logger.info("HostServiceImpl :: addNewHost : execution Started");
		try {
			if (!dataSource.isClosed()) {
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
				hostRepository.save(host);
				logger.info("HostServiceImpl :: addNewHost : execution ended");
			} else {

			}
		} catch (Exception e) {
			HostException exception = new HostException();
			e.printStackTrace();
			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");

		} finally {
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
			if (!hostId.isBlank()) {
				Host host = hostRepository.findByHostIdAndIsActiveTrue(utility.sanitize(hostId));

				if (host != null) {

					Wallet wallet = walletConverter.dtoToEntity(walletDto);

					wallet.setWalletId("WLT"+ utility.idGenerator());
					wallet.setCreatedDate(utility.dateSetter());
					wallet.setModifiedDate(utility.dateSetter());
					wallet.setActive(true);
					host.setWallets(wallet);
					hostRepository.save(host);
					logger.info("HostServiceImpl :: addWallet : execution ended");
					return "done";
				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else {
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
			}
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
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
				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else {
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
			}
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
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
			if (!hostId.isBlank()) {
				Host obj = hostRepository.findByHostIdAndIsActiveTrue(utility.sanitize(hostId));
				if (obj != null) {
					
					logger.info("HostServiceImpl :: showHost : execution ended");
					return obj;
				} else {
					throw new NotAcceptableException("data of given id is not available in the database");
				}
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} catch (Exception e) {

			HostException exception = new HostException();
			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("its a not found exception");
		} finally {
		}
	}

	/**
	 * Usage: Get/read the details of all the available Host
	 * 
	 * @return list of host objects
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
			} else {
				throw new NotFoundException("data of given id is not available in the database");
			}
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
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
			if (!hostFirstName.isBlank()) {
				List<Host> list = hostRepository.findByHostFirstNameAndIsActiveTrue(utility.sanitize(hostFirstName));
				if (!list.isEmpty()) {
					
					logger.info("HostServiceImpl :: showByHostFirstName : execution ended");
					return list;
				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
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
			if (!hostLastName.isBlank()) {
				List<Host> list = hostRepository.findByHostLastNameAndIsActiveTrue(utility.sanitize(hostLastName));
				if (!list.isEmpty()) {
					
					logger.info("HostServiceImpl :: showByHostLastName : execution ended");
					return list;
				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
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
			if (!hostEmail.isBlank()) {
				List<Host> list = hostRepository.findByHostEmailAndIsActiveTrue(hostEmail);
				if (!list.isEmpty()) {
					
					logger.info("HostServiceImpl :: showByHostEmail : execution ended");
					return list;
				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
		}
	}

	/**
	 * Usage: Get the host object/ details by created date
	 * 
	 * @param createdBy
	 * @return list of host object with similar created date
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public List<Host> showByHostCreatedBy(String createdBy) {
		logger.info("HostServiceImpl :: createdBy : execution started");

		try {
			if (!createdBy.isBlank()) {
				List<Host> list = hostRepository.findByCreatedByAndIsActiveTrue(utility.sanitize(createdBy));
				if (!list.isEmpty()) {
					logger.info("HostServiceImpl :: createdBy : execution ended");
					return list;
				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
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
			if (!hostCity.isBlank()) {
				List<Host> list = hostRepository.findByHostCityAndIsActiveTrue(utility.sanitize(hostCity));
				if (!list.isEmpty()) {
					logger.info("HostServiceImpl :: showByHostCity : execution ended");
					return list;
				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
		}
	}

	/**
	 * Usage: Get the host object/ details by modified date
	 * 
	 * @param modifiedBy
	 * @return list of host object with similar modified date
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public List<Host> showByHostModifiedBy(String modifiedBy) {
		logger.info("HostServiceImpl :: showByHostModifiedBy : execution started");

		try {
			if (!modifiedBy.isBlank()) {
				List<Host> list = hostRepository.findByModifiedByAndIsActiveTrue(utility.sanitize(modifiedBy));
				if (!list.isEmpty()) {
					logger.info("HostServiceImpl :: showByHostModifiedBy : execution ended");
					return list;
				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
		}
	}

	/**
	 * Usage: Get the host object/ details by host full name
	 * 
	 * @param hostFirstName, hostMiddleName, hostLastName
	 * @return list of host object with similar hostFullName
	 * @exception : throw : {@link @HostException} if any error occure while the
	 *              code.
	 * @exception : throw : {@link @NotFoundException} if parameter is blank.
	 * @exception : throW : {@link @NotAcceptableException} if object is null.
	 */
	@Transactional
	@Override
	public List<Host> showByFullName(String hostFirstName, String hostMiddleName, String hostLastName) {
		logger.info("HostServiceImpl :: showByFullName : execution started");

		try {
			String fullName = hostFirstName;
			if (hostMiddleName != null && !hostMiddleName.isEmpty()) {
				fullName += " " + hostMiddleName;
			}
			fullName += " " + hostLastName;
			
			return hostRepository.findByHostFirstNameAndHostMiddleNameAndHostLastNameAndIsActiveTrue(utility.sanitize(hostFirstName),
					utility.sanitize(hostMiddleName), utility.sanitize(hostLastName));
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
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
	public void remove(String hostId) {
		logger.info("HostServiceImpl :: removeHost : execution started");

		try {
			if (!hostId.isBlank()) {
				Host host = hostRepository.findByHostIdAndIsActiveTrue(utility.sanitize(hostId));
				if (host != null) {
					host.setActive(false);
					hostRepository.save(host);
					logger.info("HostServiceImpl :: removeHost : execution ended");

				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
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
			Host host = hostConverter.dtoToEntity(hostdto);
			if (!hostId.isBlank()) {

				Host obj = hostRepository.findByHostIdAndIsActiveTrue(utility.sanitize(hostId));
				if (obj != null) {

					if (host.getHostFirstName() != null && hostdto.getHostFirstName().isBlank())
						obj.setHostFirstName(hostdto.getHostFirstName());

					if (host.getHostMiddleName() != null && hostdto.getHostMiddleName().isBlank())
						obj.setHostMiddleName(hostdto.getHostMiddleName());

					if (host.getHostLastName() != null && hostdto.getHostLastName().isBlank())
						obj.setHostLastName(hostdto.getHostLastName());

					if (host.getHostEmail() != null && hostdto.getHostEmail().isBlank())
						obj.setHostEmail(hostdto.getHostEmail());

					if (host.getHostContactNo() != null && hostdto.getHostContactNo().isBlank())
						obj.setHostVehicleChargerType(hostdto.getHostContactNo());

					if (host.getHostAddress() != null && hostdto.getHostAddress().isBlank())
						obj.setHostAddress(hostdto.getHostAddress());

					if (host.getHostVehicleRegistrationNo() != null && hostdto.getHostVehicleRegistrationNo().isBlank())
						obj.setHostVehicleRegistrationNo(hostdto.getHostVehicleRegistrationNo());

					if (host.getHostVehicleChargerType() != null && hostdto.getHostVehicleChargerType().isBlank())
						obj.setHostVehicleChargerType(hostdto.getHostVehicleChargerType());

					if (host.getHostCity() != null && hostdto.getHostCity().isBlank())
						obj.setHostCity(hostdto.getHostCity());

					if (hostdto.getCreatedBy() != null && hostdto.getCreatedBy().isBlank())
						obj.setCreatedBy(hostdto.getCreatedBy());

					if (hostdto.getModifiedBy() != null && hostdto.getModifiedBy().isBlank())
						obj.setModifiedBy(hostdto.getModifiedBy());

					obj.setModifiedDate(utility.dateSetter());

					hostRepository.save(obj);
					logger.info("HostServiceImpl :: editHost : execution ended");
					return hostConverter.entityToDto(obj);
				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else {
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
			}
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
		}

	}

//	@Override
//	public List<Settlement> getByHostIdAndSettlementDate(String hostId, String settlementDate) {
//
//		Criteria matchCriteria = Criteria.where("_id").is(hostId);
//		Criteria matchCriteria2 = Criteria.where("settlements.settlementDate").is(settlementDate);
//		GroupOperation groupOperation = Aggregation.group("_id").push("settlements").as("settlements");
//
//		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(matchCriteria),
//				Aggregation.unwind("settlements"), Aggregation.match(matchCriteria2), groupOperation,
//				Aggregation.project("_id", "settlements"));
//
//		Host host = mongoTemplate.aggregate(aggregation, "host", Host.class).getUniqueMappedResult();
//		List<Settlement> settlements = host.getSettlements();
//		System.out.println(settlements);
//		return settlements;
//	}

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
			if (!hostId.isBlank() && hostId != null) {
				Host host = hostRepository.findBySettlementMatching(utility.sanitize(hostId), settlementDate);

				if (host != null) {
					List<Settlement> settlements = host.getSettlements();
					logger.info("HostServiceImpl :: getByHostIdAndSettlementsDate : execution ended");
					return settlements;
				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else {
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
			}
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
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
				} else {
					throw new NotFoundException("data of given id is not available in the database");
				}
			} else {
				throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
			}
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.error(exception);
			throw new MethodFailureException("something went wrong");
		} finally {
		}
	}

}
