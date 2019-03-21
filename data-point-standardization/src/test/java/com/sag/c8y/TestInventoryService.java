package com.sag.c8y;


import com.google.common.base.Optional;
import com.sag.c8y.inseego.service.c8y.IInventoryService;
import com.sag.c8y.inseego.service.c8y.InventoryService;
import com.sag.c8y.inseego.util.C8yUtil;

public class TestInventoryService {

	public static void main(String[] args) {
		TestInventoryService.testIsFragmentSeriesExist();
	}
	
	public static void testGetManageObject() {
		IInventoryService inventoryService = new InventoryService();
		String tenantUrl = C8yUtil.getTenantUrl("t79467893");
		String encodedTokenStr = C8yUtil.getTenantEncodedToken("t79467893","amzadbasha.shaik@ispace.com", "January#2019");
		Optional<String> response = inventoryService.getManageObject("142536", tenantUrl, encodedTokenStr);
		if(response.isPresent()) {
			System.out.println(response.get());
		}else {
			System.out.println("response not presnent");
		}
	}
	public static void testIsFragmentSeriesExist() {
		IInventoryService inventoryService = new InventoryService();
		String tenantUrl = C8yUtil.getTenantUrl("t79467893");
		String encodedTokenStr = C8yUtil.getTenantEncodedToken("t79467893","amzadbasha.shaik@ispace.com", "January#2019");
		boolean isPresent = inventoryService.isFragmentSeriesExist("Test-WWW", "Test-WWW", tenantUrl, encodedTokenStr);
		System.out.println(isPresent);
	}
	
}
