package com.truck.helper;

import java.util.HashMap;
import java.util.Map;

public enum TruckTypeEnum {

	STRAIGHT_TRUCK("straight_truck", 1), FLAT("flat", 2), CONESTOGA("conestoga", 3), REFRIGERATED("refrigerated", 4);
	;

	private String truckTypeName;
	private Integer truckTypeId;

	public String getTruckTypeName() {
		return truckTypeName;
	}

	public Integer getTruckTypeId() {
		return truckTypeId;
	}

	private TruckTypeEnum(String truckTypeName, Integer truckTypeId) {
		this.truckTypeName = truckTypeName;
		this.truckTypeId = truckTypeId;
	}

	private static final Map<String, Integer> TRUCKTYPEIDMAP = new HashMap<>();
	static {
		for (TruckTypeEnum truckType : TruckTypeEnum.values()) {
			TRUCKTYPEIDMAP.put(truckType.truckTypeName, truckType.truckTypeId);
		}
	}

	public static Integer getTrcukTypeIdByDCTruckTypeName(String truckTypeName) {
		return TRUCKTYPEIDMAP.get(truckTypeName);
	}

	private static final Map<Integer, String> TRUCKTYPENAMEMAP = new HashMap<>();
	static {
		for (TruckTypeEnum truckType : TruckTypeEnum.values()) {
			TRUCKTYPENAMEMAP.put(truckType.truckTypeId, truckType.truckTypeName);
		}
	}

	public static String getTruckTypeNameByTruckTypeId(Integer truckTypeId) {
		return TRUCKTYPENAMEMAP.get(truckTypeId);
	}

}
