package com.truck.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.truck.dto.TruckDTO;
import com.truck.dto.TruckTypeDTO;
import com.truck.exception.TruckException;
import com.truck.service.ITruckService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TruckControllerTest {

	private TruckController truckController;

	private ITruckService mockTruckService;

	@Before
	public void setUp() {
		truckController = new TruckController();
		mockTruckService = EasyMock.createMock(ITruckService.class);

		ReflectionTestUtils.setField(truckController, "truckService", mockTruckService);
	}

	@Test
	public void testAddTruck_Create() throws TruckException {
		TruckDTO truckDTO = getTruckDTO();
		EasyMock.expect(mockTruckService.addTruck(truckDTO)).andReturn(truckDTO).times(1);
		EasyMock.replay(mockTruckService);

		ResponseEntity<TruckDTO> truckResponce = truckController.addTruck(truckDTO);

		Assert.assertEquals(201, truckResponce.getStatusCodeValue());

		Assert.assertEquals(truckDTO, truckResponce.getBody());

		EasyMock.verify(mockTruckService);
	}

	@Test
	public void testAddTruck_Conflict() throws TruckException {
		TruckDTO truckDTO = getTruckDTO();
		EasyMock.expect(mockTruckService.addTruck(truckDTO)).andReturn(null).times(1);
		EasyMock.replay(mockTruckService);

		ResponseEntity<TruckDTO> truckResponce = truckController.addTruck(truckDTO);

		Assert.assertEquals(409, truckResponce.getStatusCodeValue());

		EasyMock.verify(mockTruckService);
	}

	@Test
	public void testAddTruck_Throw_TruckException() throws TruckException {
		TruckDTO truckDTO = getTruckDTO();
		truckDTO.setTruckName(null);
		EasyMock.expect(mockTruckService.addTruck(truckDTO)).andThrow(new TruckException()).times(1);
		EasyMock.replay(mockTruckService);

		Assert.assertThrows(TruckException.class, () -> truckController.addTruck(truckDTO));

		EasyMock.verify(mockTruckService);
	}

	@Test
	public void testGetAllTrucks_OK() {

		List<TruckDTO> truckList = new ArrayList<>();
		truckList.add(getTruckDTO());

		EasyMock.expect(mockTruckService.getAllTrucks()).andReturn(truckList);
		EasyMock.replay(mockTruckService);

		ResponseEntity<List<TruckDTO>> actualTuckResponse = truckController.getAllTrucks();

		Assert.assertEquals(200, actualTuckResponse.getStatusCodeValue());
		Assert.assertEquals(truckList, actualTuckResponse.getBody());

		EasyMock.verify(mockTruckService);

	}

	@Test
	public void testGetAllTrucks_NoContent() {

		List<TruckDTO> truckList = new ArrayList<>();

		EasyMock.expect(mockTruckService.getAllTrucks()).andReturn(truckList);
		EasyMock.replay(mockTruckService);

		ResponseEntity<List<TruckDTO>> actualTuckResponse = truckController.getAllTrucks();

		Assert.assertEquals(204, actualTuckResponse.getStatusCodeValue());

		EasyMock.verify(mockTruckService);

	}

	@Test
	public void testSearchTruckById_Ok() throws TruckException {

		TruckDTO truckDTO = getTruckDTO();

		EasyMock.expect(mockTruckService.searchTruckById(EasyMock.anyInt())).andReturn(truckDTO);
		EasyMock.replay(mockTruckService);

		ResponseEntity<TruckDTO> actualTuckResponse = truckController.searchTruckById(1);

		Assert.assertEquals(200, actualTuckResponse.getStatusCodeValue());
		Assert.assertEquals(truckDTO, actualTuckResponse.getBody());

		EasyMock.verify(mockTruckService);

	}

	@Test
	public void testSearchTruckById_NoContent() throws TruckException{

		EasyMock.expect(mockTruckService.searchTruckById(EasyMock.anyInt())).andReturn(null);
		EasyMock.replay(mockTruckService);

		ResponseEntity<TruckDTO> actualTuckResponse = truckController.searchTruckById(1);

		Assert.assertEquals(204, actualTuckResponse.getStatusCodeValue());

		EasyMock.verify(mockTruckService);

	}

	@Test
	public void testUpdateTruck_Ok() throws TruckException {
		TruckDTO truckDTO = getTruckDTO();
		EasyMock.expect(mockTruckService.updateTruck(truckDTO)).andReturn(truckDTO).times(1);
		EasyMock.replay(mockTruckService);

		ResponseEntity<TruckDTO> truckResponce = truckController.updateTruck(truckDTO);

		Assert.assertEquals(200, truckResponce.getStatusCodeValue());
		Assert.assertEquals(truckDTO, truckResponce.getBody());

		EasyMock.verify(mockTruckService);
	}

	@Test
	public void testUpdateTruck_NoContent() throws TruckException {

		TruckDTO truckDTO = getTruckDTO();
		EasyMock.expect(mockTruckService.updateTruck(truckDTO)).andReturn(null).times(1);
		EasyMock.replay(mockTruckService);

		ResponseEntity<TruckDTO> truckResponce = truckController.updateTruck(truckDTO);

		Assert.assertEquals(204, truckResponce.getStatusCodeValue());

		EasyMock.verify(mockTruckService);
	}

	@Test
	public void testUpdateTruck_Throw_TruckException() throws TruckException {
		TruckDTO truckDTO = null;
		EasyMock.expect(mockTruckService.updateTruck(truckDTO)).andThrow(new TruckException()).times(1);
		EasyMock.replay(mockTruckService);

		Assert.assertThrows(TruckException.class, () -> truckController.updateTruck(truckDTO));

		EasyMock.verify(mockTruckService);
	}

	@Test
	public void testDeleteTruckById_Ok() {

		TruckDTO truckDTO = getTruckDTO();

		EasyMock.expect(mockTruckService.deleteTruckById(EasyMock.anyInt())).andReturn(truckDTO);
		EasyMock.replay(mockTruckService);

		ResponseEntity<TruckDTO> actualTuckResponse = truckController.deleteTruckById(1);

		Assert.assertEquals(200, actualTuckResponse.getStatusCodeValue());
		Assert.assertEquals(truckDTO, actualTuckResponse.getBody());

		EasyMock.verify(mockTruckService);

	}

	@Test
	public void testDeleteTruckById_NoContent() {

		EasyMock.expect(mockTruckService.deleteTruckById(EasyMock.anyInt())).andReturn(null);
		EasyMock.replay(mockTruckService);

		ResponseEntity<TruckDTO> actualTuckResponse = truckController.deleteTruckById(1);

		Assert.assertEquals(204, actualTuckResponse.getStatusCodeValue());

		EasyMock.verify(mockTruckService);

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
		truckDTO.setTruckNumber(Long.valueOf(111));
		truckDTO.setTruckTypeDTO(truckTypeDTO);
		return truckDTO;

	}

}
