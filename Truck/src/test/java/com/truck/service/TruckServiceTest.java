package com.truck.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.truck.dto.TruckDTO;
import com.truck.dto.TruckTypeDTO;
import com.truck.entity.TruckEO;
import com.truck.entity.TruckTypeEO;
import com.truck.exception.TruckException;
import com.truck.helper.MapperUtils;
import com.truck.repository.TruckRepo;
import com.truck.repository.TruckTypeRepo;
import com.truck.service.impl.TruckService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TruckServiceTest {

	private TruckService truckService;

	@Mock
	private TruckRepo mockTruckRepo;

	@Mock
	private TruckTypeRepo mockTruckTypeRepo;

	@Before
	public void setUp() {

		truckService = new TruckService();

		mockTruckRepo = EasyMock.createMock(TruckRepo.class);
		mockTruckTypeRepo = EasyMock.createMock(TruckTypeRepo.class);

		ReflectionTestUtils.setField(truckService, "truckRepo", mockTruckRepo);
		ReflectionTestUtils.setField(truckService, "truckTypeRepo", mockTruckTypeRepo);
	}

	@Test
	public void testAddTruck_Should_Add_Truck() throws TruckException {

		TruckDTO truckDTO = getTruckDTO();
		MapperUtils mapperUtils = new MapperUtils();

		TruckEO truckEO = mapperUtils.mapToEO(truckDTO);
		TruckTypeEO truckTypeEO = new TruckTypeEO();
		truckTypeEO.setTruckTypeId(1);
		truckTypeEO.setTruckTypeName("straight_truck");
		truckEO.setTruckTypeEO(truckTypeEO);

		EasyMock.expect(mockTruckRepo.save(truckEO)).andReturn(truckEO).times(1);
		EasyMock.replay(mockTruckRepo);

		List<TruckTypeEO> truckTypeEOList = new ArrayList<>();
		truckTypeEOList.add(truckEO.getTruckTypeEO());

		EasyMock.expect(mockTruckTypeRepo.findAll()).andReturn(truckTypeEOList);
		EasyMock.replay(mockTruckTypeRepo);

		TruckDTO actualResult = truckService.addTruck(truckDTO);

		Assert.assertEquals(truckDTO, actualResult);

		EasyMock.verify(mockTruckRepo);
		EasyMock.verify(mockTruckTypeRepo);

	}

	@Test
	public void testAddTruck_Throw_TruckException() throws TruckException {

		TruckDTO truckDTO = getTruckDTO();
		MapperUtils mapperUtils = new MapperUtils();

		TruckEO truckEO = mapperUtils.mapToEO(truckDTO);
		TruckTypeEO truckTypeEO = new TruckTypeEO();
		truckTypeEO.setTruckTypeId(1);
		truckTypeEO.setTruckTypeName("abc");
		truckEO.setTruckTypeEO(truckTypeEO);

		EasyMock.expect(mockTruckRepo.save(truckEO)).andReturn(truckEO).times(1);
		EasyMock.replay(mockTruckRepo);

		List<TruckTypeEO> truckTypeEOList = new ArrayList<>();
		truckTypeEOList.add(truckEO.getTruckTypeEO());

		EasyMock.expect(mockTruckTypeRepo.findAll()).andReturn(truckTypeEOList);
		EasyMock.replay(mockTruckTypeRepo);

		Assert.assertThrows(TruckException.class, () -> truckService.addTruck(truckDTO));

	}

	@Test
	public void testGetAllTrucks() {

		TruckDTO truckDTO = getTruckDTO();
		MapperUtils mapperUtils = new MapperUtils();

		TruckEO truckEO = mapperUtils.mapToEO(truckDTO);
		TruckTypeEO truckTypeEO = new TruckTypeEO();
		truckTypeEO.setTruckTypeId(1);
		truckTypeEO.setTruckTypeName("straight_truck");
		truckEO.setTruckTypeEO(truckTypeEO);

		List<TruckEO> truckEOList = new ArrayList<>();
		truckEOList.add(truckEO);

		EasyMock.expect(mockTruckRepo.findAll()).andReturn(truckEOList).times(1);
		EasyMock.replay(mockTruckRepo);

		Optional<TruckTypeEO> optionalTruckTypeEO = Optional.of(truckTypeEO);

		EasyMock.expect(mockTruckTypeRepo.findById(1)).andReturn(optionalTruckTypeEO);
		EasyMock.replay(mockTruckTypeRepo);

		List<TruckDTO> truckDTOList = new ArrayList<>();
		truckDTOList.add(truckDTO);

		List<TruckDTO> actualResult = truckService.getAllTrucks();

		Assert.assertEquals(truckDTOList, actualResult);

		EasyMock.verify(mockTruckRepo);
		EasyMock.verify(mockTruckTypeRepo);

	}

	@Test
	public void testSearchTruckById() {

		TruckDTO truckDTO = getTruckDTO();

		MapperUtils mapperUtils = new MapperUtils();

		TruckEO truckEO = mapperUtils.mapToEO(truckDTO);
		TruckTypeEO truckTypeEO = new TruckTypeEO();
		truckTypeEO.setTruckTypeId(1);
		truckTypeEO.setTruckTypeName("straight_truck");
		truckEO.setTruckTypeEO(truckTypeEO);

		Optional<TruckEO> optionalTruckEO = Optional.of(truckEO);

		EasyMock.expect(mockTruckRepo.findById(EasyMock.anyInt())).andReturn(optionalTruckEO).times(1);
		EasyMock.replay(mockTruckRepo);

		Optional<TruckTypeEO> optionalTruckTypeEO = Optional.of(truckTypeEO);

		EasyMock.expect(mockTruckTypeRepo.findById(1)).andReturn(optionalTruckTypeEO);
		EasyMock.replay(mockTruckTypeRepo);

		TruckDTO actualResult = truckService.searchTruckById(1);

		Assert.assertEquals(truckDTO, actualResult);

		EasyMock.verify(mockTruckRepo);
		EasyMock.verify(mockTruckTypeRepo);

	}

	private TruckDTO getTruckDTO() {

		TruckDTO truckDTO = new TruckDTO();
		TruckTypeDTO truckTypeDTO = new TruckTypeDTO();

		truckTypeDTO = new TruckTypeDTO();
		truckTypeDTO.setTruckTypeId(1);
		truckTypeDTO.setTruckTypeName("straight_truck");

		truckDTO = new TruckDTO();
		truckDTO.setTruckId(1);
		truckDTO.setCreatedBy("mani");
		truckDTO.setCreatedTS(LocalDateTime.now());
		truckDTO.setLastUpdatedBy("Karthi");
		truckDTO.setLastUpdatedTS(LocalDateTime.now());
		truckDTO.setTruckName("Walmart");
		truckDTO.setTruckNumber(111);
		truckDTO.setTruckTypeDTO(truckTypeDTO);
		return truckDTO;

	}

}
