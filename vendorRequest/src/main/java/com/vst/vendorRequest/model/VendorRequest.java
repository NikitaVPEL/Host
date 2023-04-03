package com.vst.vendorRequest.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "VendorRequest")
public class VendorRequest {
	
	@Id
    private String	vendorRequestId;
	@Indexed
    private String vendorRequestHostId;
	@Indexed
    private String vendorRequestType;
    private String vendorRequestNoOfChargersRequired;
	@Indexed
    private String vendorRequestLocation;
    private String vendorRequestStatus;
	@Indexed
    private String vendorRequestDate;
    private String createdDate;
    private String modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private boolean isActive = true;

}
