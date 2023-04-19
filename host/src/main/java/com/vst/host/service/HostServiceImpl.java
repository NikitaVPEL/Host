package com.vst.host.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.vst.host.converter.HostConverter;
import com.vst.host.converter.SettlementConverter;
import com.vst.host.converter.WalletConverter;
import com.vst.host.dto.HostDto;
import com.vst.host.dto.SettlementDto;
import com.vst.host.dto.WalletDto;
import com.vst.host.exception.HostException;
import com.vst.host.exception.NotAcceptableException;
import com.vst.host.exception.NotFoundException;
import com.vst.host.model.Host;
import com.vst.host.model.Settlement;
import com.vst.host.model.Wallet;
import com.vst.host.repository.HostRepository;
import com.vst.host.utils.IdAndDateGenerator;
import com.zaxxer.hikari.HikariConfig;
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
	HostSequenceGeneratorService hostSequenceGeneratorService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	public HikariDataSource dataSource;

	private ThreadPoolExecutor threadPool;

	IdAndDateGenerator idAndDateGenerator = new IdAndDateGenerator();

	public void openConnection() {
		try{
			if (dataSource.isClosed()) {
				logger.info("DB connection open............");
				dataSource.getConnection();
			}
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage(), "cannot connect to database");
		}
	}

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

	@Transactional // To avoid rollback on listed exception
	@Override
	public void add(@Valid HostDto hostDto) {

		try {
			openConnection();

			Host host = hostConverter.dtoToEntity(hostDto);
			host.setHostId("HST" + idAndDateGenerator.idGenerator());
			host.setCreatedDate(idAndDateGenerator.dateSetter());
			host.setModifiedDate(idAndDateGenerator.dateSetter());
			host.setActive(true);

			host.setWallets(new Wallet());
			host.setSettlements(new ArrayList<Settlement>());
			hostRepository.save(host);

		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.info(exception);

		} finally {
			closeDataSource();
		}
	}

	@Transactional
	@Override
	public Host show(String hostId) {
		try {
			if (!hostId.isBlank()) {
				openConnection();

				Host obj = hostRepository.findByHostIdAndIsActiveTrue(hostId);
				if (obj != null) {
					return obj;
				} else {
					throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
				}
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
			logger.info(exception);
		} finally {
			closeDataSource();
		}
		throw new NotFoundException("something went wrong..");
	}

	@Transactional
	@Override
	public List<Host> showAll() {
		try {
			openConnection();

			List<Host> list = hostRepository.findAllByIsActiveTrue();
			if (!list.isEmpty()) {
				return list;
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.info(exception);
		} finally {
			closeDataSource();
		}
		throw new NotFoundException("Something went wrong..");
	}

	@Transactional
	@Override
	public List<Host> showByHostFirstName(String hostFirstName) {
		try {
			openConnection();

			if (!hostFirstName.isBlank()) {
				List<Host> list = hostRepository.findByHostFirstNameAndIsActiveTrue(hostFirstName);
				if (!list.isEmpty()) {
					return list;
				} else {
					throw new NotFoundException("entered id is null or not valid ,please enter correct id");
				}
			} else
				throw new NotAcceptableException("data of given id is not available in the database");
		} catch (HostException exception) {

			exception.setErrorCode("404");
			exception.setStatusCode(null);
			exception.setStatus(null);
			exception.setMethodName("HOST servive: add method");
			exception.setLineNumber("Line No 86");
			exception.setFunctionality("add the host");
			exception.setMessage(null);
			logger.info(exception);
		} finally {
			closeDataSource();
		}
		throw new NotFoundException("Something went wrong..");
	}

	@Override
	public List<Host> showByHostLastName(String hostLastName) {
		if (!hostLastName.isBlank()) {
			List<Host> list = hostRepository.findByHostLastNameAndIsActiveTrue(hostLastName);
			if (!list.isEmpty()) {
				return list;
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else
			throw new NotAcceptableException("data of given id is not available in the database");
	}

	@Override
	public List<Host> showByHostEmail(String hostEmail) {
		if (!hostEmail.isBlank()) {
			List<Host> list = hostRepository.findByHostEmailAndIsActiveTrue(hostEmail);
			if (!list.isEmpty()) {
				return list;
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else
			throw new NotAcceptableException("data of given id is not available in the database");
	}

	@Override
	public List<Host> showByHostVehicleRegistrationNo(String hostVehicleRegistrationNo) {
		if (!hostVehicleRegistrationNo.isBlank()) {
			List<Host> list = hostRepository.findByHostVehicleRegistrationNoAndIsActiveTrue(hostVehicleRegistrationNo);
			if (!list.isEmpty()) {
				return list;
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else
			throw new NotAcceptableException("data of given id is not available in the database");
	}

	@Override
	public List<Host> showByHostVehicleChargerType(String hostVehicleChargerType) {
		if (!hostVehicleChargerType.isBlank()) {

			List<Host> list = hostRepository.findByHostVehicleChargerTypeAndIsActiveTrue(hostVehicleChargerType);
			if (!list.isEmpty()) {
				return list;
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else
			throw new NotAcceptableException("data of given id is not available in the database");
	}

	@Override
	public List<Host> showByHostCreatedBy(String createdBy) {
		if (!createdBy.isBlank()) {
			List<Host> list = hostRepository.findByCreatedByAndIsActiveTrue(createdBy);
			if (!list.isEmpty()) {
				return list;
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else
			throw new NotAcceptableException("data of given id is not available in the database");
	}

	@Override
	public List<Host> showByHostCity(String hostCity) {
		if (!hostCity.isBlank()) {
			List<Host> list = hostRepository.findByHostCityAndIsActiveTrue(hostCity);
			if (!list.isEmpty()) {
				return list;
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else
			throw new NotAcceptableException("data of given id is not available in the database");
	}

	@Override
	public List<Host> showByHostModifiedBy(String modifiedBy) {
		if (!modifiedBy.isBlank()) {
			List<Host> list = hostRepository.findByModifiedByAndIsActiveTrue(modifiedBy);
			if (!list.isEmpty()) {
				return list;
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else
			throw new NotAcceptableException("data of given id is not available in the database");

	}

	@Override
	public List<Host> showByFullName(String hostFirstName, String hostMiddleName, String hostLastName) {
		String fullName = hostFirstName;
		if (hostMiddleName != null && !hostMiddleName.isEmpty()) {
			fullName += " " + hostMiddleName;
		}
		fullName += " " + hostLastName;
		return hostRepository.findByHostFirstNameAndHostMiddleNameAndHostLastNameAndIsActiveTrue(hostFirstName,
				hostMiddleName, hostLastName);
	}

	@Override
	public void remove(String hostId) {
		if (!hostId.isBlank()) {
			Host host = hostRepository.findByHostIdAndIsActiveTrue(hostId);
			if (host != null) {
				host.setActive(false);
				hostRepository.save(host);
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else
			throw new NotAcceptableException("data of given id is not available in the database");
	}

	@Override
	public HostDto edit(String hostId, HostDto hostdto) {
		Host host = hostConverter.dtoToEntity(hostdto);
		if (!hostId.isBlank()) {

			Host obj = hostRepository.findByHostIdAndIsActiveTrue(hostId);
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

				if (host.getCreatedDate() != null && hostdto.getCreatedDate().isBlank())
					obj.setCreatedDate(hostdto.getCreatedDate());

				if (hostdto.getModifiedDate() != null && hostdto.getModifiedDate().isBlank())
					obj.setModifiedDate(hostdto.getModifiedDate());

				if (hostdto.getCreatedBy() != null && hostdto.getCreatedBy().isBlank())
					obj.setCreatedBy(hostdto.getCreatedBy());

				if (hostdto.getModifiedBy() != null && hostdto.getModifiedBy().isBlank())
					obj.setModifiedBy(hostdto.getModifiedBy());

				hostRepository.save(obj);
				return hostConverter.entityToDto(obj);
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else {
			throw new NotAcceptableException("data of given id is not available in the database");
		}

	}

	@Override
	public String addSettlement(String hostId, SettlementDto settlementDto) {

		if (!hostId.isBlank()) {
			Host obj = hostRepository.findByHostIdAndIsActiveTrue(hostId);

			if (obj != null) {

				Settlement settlement = settlementConverter.dtoToEntity(settlementDto);

				settlement.setSettlementId(hostSequenceGeneratorService.getGeneratedId());
				settlement.setSettlementUTR(UUID.randomUUID().toString());
				settlement.setCreatedDate(idAndDateGenerator.dateSetter());
				settlement.setModifiedDate(idAndDateGenerator.dateSetter());
				settlement.setActive(true);
				obj.getSettlements().add(settlement);
				hostRepository.save(obj);
				return "done";
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else {
			throw new NotAcceptableException("data of given id is not available in the database");
		}
	}

	@Override
	public void addWallet(String hostId, WalletDto walletDto) {

		try {

			if (!hostId.isBlank()) {

				Host host = hostRepository.findByHostIdAndIsActiveTrue(hostId);

				if (host != null) {

					Wallet wallet = walletConverter.dtoToEntity(walletDto);
					Wallet wallet2 = host.getWallets();

					String walletIdFormat = "WLT" + idAndDateGenerator.idGenerator();

					wallet.setWalletId(wallet2.getWalletId());
					wallet.setWalletId(walletIdFormat);
					wallet.setActive(true);
					host.setWallets(wallet);
					hostRepository.save(host);
				} else {
					throw new NotFoundException("entered id is null or not valid ,please enter correct id");
				}
			} else {
				throw new NotAcceptableException("data of given id is not available in the database");
			}
		} catch (Exception e) {

		} finally {

		}
	}

	@Override
	public List<Settlement> getByHostIdAndSettlementDate(String hostId, String settlementDate) {

		Criteria matchCriteria = Criteria.where("_id").is(hostId);
		Criteria matchCriteria2 = Criteria.where("settlements.settlementDate").is(settlementDate);
		GroupOperation groupOperation = Aggregation.group("_id").push("settlements").as("settlements");

		Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(matchCriteria),
				Aggregation.unwind("settlements"), Aggregation.match(matchCriteria2), groupOperation,
				Aggregation.project("_id", "settlements"));

		Host host = mongoTemplate.aggregate(aggregation, "host", Host.class).getUniqueMappedResult();
		List<Settlement> settlements = host.getSettlements();
		System.out.println(settlements);
		return settlements;
	}

	@Override
	public List<Settlement> getByHostIdAndSettlementsDate1(String hostId, String settlementDate) {

		Host host = hostRepository.findBySettlementMatching(hostId, settlementDate);
		List<Settlement> settlements = host.getSettlements();
		return settlements;
	}

	@Override
	public Host getHostDetailsById(String hostId) {
		Host host = hostRepository.findByHostId(hostId);
		return host;
	}

}
