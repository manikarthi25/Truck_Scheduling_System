package com.dc.helper;

import java.util.HashMap;
import java.util.Map;

public enum DistributedCenterTypeEnum {

	INTERNATIONAL("international", 1), REGIONAL("regional", 2), IMPORTS("imports", 3);

	private String dcTypeName;
	private Integer dcTypeId;

	public String getDcTypeName() {
		return dcTypeName;
	}

	public Integer getDcTypeId() {
		return dcTypeId;
	}

	private DistributedCenterTypeEnum(String dcTypeName, Integer dcTypeId) {
		this.dcTypeName = dcTypeName;
		this.dcTypeId = dcTypeId;
	}

	private static final Map<String, Integer> DCTYPEIDMAP = new HashMap<>();
	static {
		for (DistributedCenterTypeEnum dcType : DistributedCenterTypeEnum.values()) {
			DCTYPEIDMAP.put(dcType.dcTypeName, dcType.dcTypeId);
		}
	}

	public static Integer getDCTypeIdByDCTypeName(String dcTypeName) {
		return DCTYPEIDMAP.get(dcTypeName);
	}

	private static final Map<Integer, String> DCTYPENAMEMAP = new HashMap<>();
	static {
		for (DistributedCenterTypeEnum dcType : DistributedCenterTypeEnum.values()) {
			DCTYPENAMEMAP.put(dcType.dcTypeId, dcType.dcTypeName);
		}
	}

	public static String getDCTypeNameByDCTypeId(Integer dcTypeId) {
		return DCTYPENAMEMAP.get(dcTypeId);
	}

}
