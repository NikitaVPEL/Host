package com.vst.host.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.sound.sampled.Line;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.reflect.CatchClauseSignature;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.vst.host.configuration.MongoDbConfiguration;
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
	MongoDbConfiguration mongoDbConfiguration;
	public static final Logger logger = LogManager.getLogger(HostServiceImpl.class);

	public String idGenerator() {

		Date dNow = new Date();
		SimpleDateFormat idDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String idGen = idDateFormat.format(dNow);
		return idGen;
	}

	public String dateSetter() {
		Date dNow = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String date = dateFormat.format(dNow);
		return date;
	}

	@Transactional // To avoid rollback on listed exception
	@Override
	public void add(@Valid HostDto hostDto) {

		try {
			mongoDbConfiguration.mongoClient();

			Host host = hostConverter.dtoToEntity(hostDto);
			host.setHostId("HST" + idGenerator());
			host.setCreatedDate(dateSetter());
			host.setModifiedDate(dateSetter());
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
			mongoDbConfiguration.cleanUp();
			System.out.println("finally");
		}

	}

	@SuppressWarnings("finally")
	@Transactional
	@Override
	public Host show(String hostId) {

		try {
			mongoDbConfiguration.mongoClient();

			if (!hostId.isBlank()) {
				Host obj = hostRepository.findByHostIdAndIsActiveTrue(hostId);

				try {
					if (obj != null)
						return obj;

				} catch (Exception e) {
					throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
				}
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
			throw new NotFoundException("data of given id is not available in the database");
		} finally {
			mongoDbConfiguration.cleanUp();
			System.out.println("finally");
		}
		return null;

//		if (!hostId.isBlank()) {
//			Host obj = hostRepository.findByHostIdAndIsActiveTrue(hostId);
//
//			if (obj != null) {
//				return obj;
//			} else
//				throw new NotFoundException("data of given id is not available in the database");
//
//		} else
//			throw new NotAcceptableException("entered id is null or not valid ,please enter correct id");
	}

	@Override
	public List<Host> showAll() {
		MongoCursor<Document> cursor = null;
		try {
			
			mongoDbConfiguration.mongoClient();

		List<Host> list = hostRepository.findAllByIsActiveTrue();
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new NotFoundException("entered id is null or not valid ,please enter correct id");
		}
		}finally {
				 if (cursor != null) {
				  cursor.close();
		}
	}
	}    

	@Override
	public List<Host> showByHostFirstName(String hostFirstName) {
		if (!hostFirstName.isBlank()) {
			List<Host> list = hostRepository.findByHostFirstNameAndIsActiveTrue(hostFirstName);
			if (!list.isEmpty()) {
				return list;
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else
			throw new NotAcceptableException("data of given id is not available in the database");
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
				settlement.setCreatedDate(dateSetter());
				settlement.setModifiedDate(dateSetter());
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
	public String addWallet(String hostId, WalletDto walletDto) {

		if (!hostId.isBlank()) {

			Host host = hostRepository.findByHostIdAndIsActiveTrue(hostId);

			if (host != null) {

				Wallet wallet = walletConverter.dtoToEntity(walletDto);
				Wallet wallet2 = host.getWallets();

				String walletIdFormat = "WLT" + idGenerator();

				wallet.setWalletId(wallet2.getWalletId());
				wallet.setWalletId(walletIdFormat);
				wallet.setActive(true);
				host.setWallets(wallet);
				hostRepository.save(host);
				return "done";
			} else {
				throw new NotFoundException("entered id is null or not valid ,please enter correct id");
			}
		} else {
			throw new NotAcceptableException("data of given id is not available in the database");
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
