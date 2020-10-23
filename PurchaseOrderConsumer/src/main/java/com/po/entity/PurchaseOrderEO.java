package com.po.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "po")
public class PurchaseOrderEO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "po_number", unique = true, nullable = false)
	private String poNumber;

	@Column(name = "po_date", nullable = false)
	private LocalDate poDate;

	@Column(name = "po_address")
	private String poAddress;

	@Column(name = "orderqty", nullable = false)
	private Long orderQty;

	@Column(name = "upc_number", nullable = false)
	private Integer upcNumber;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "po_line_number", nullable = false)
	private Integer poLineNumber;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_ts")
	private LocalDateTime createdTS;

	@Column(name = "last_updated_by")
	private String lastUpdatedBy;

	@Column(name = "last_updated_ts")
	private LocalDateTime lastUpdatedTS;

}
