package com.vst.host.converter;

/**
* Host converter class is to convert the data of dto class to entity and entity class to dto to not expose the sensitive information.
*
* @author Nikita Chakole <nikita.chakole@vpel.in>
* @since  21/12/2022
*/

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.vst.host.dto.HostDto;
import com.vst.host.model.Host;


@Component
public class HostConverter {
	
	/**
	 * Usage : convert/transfer data fron dto to entity
	 * 
	 * @param HostDto
	 * @return host entity or model
	 */
	public Host dtoToEntity(HostDto hostDto) {

		Host host = new Host();
		BeanUtils.copyProperties(hostDto, host);
		return host;
	}


	/**
	 * Usage : convert/transfer data fron entity to dto
	 * 
	 * @param HostEntity
	 * @return host Dto
	 */
	public HostDto entityToDto(Host host) {
		HostDto hostDto = new HostDto();
		BeanUtils.copyProperties(host, hostDto);
		return hostDto;

	}

}
