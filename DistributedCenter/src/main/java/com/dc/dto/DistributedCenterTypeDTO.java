package com.dc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DistributedCenterTypeDTO {

	private Integer dcTypeId;

	private String dcTypeName;

}
