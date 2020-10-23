package com.po.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class PurchaseOrder {

	private String poNumber;

	private LocalDate poDate;

	private String poAddress;

	private Long orderQty;

	private Integer upcNumber;

	private String itemName;

	private Integer poLineNumber;

	private String createdBy;

	private LocalDateTime createdTS;

	private String lastUpdatedBy;

	private LocalDateTime lastUpdatedTS;
}
