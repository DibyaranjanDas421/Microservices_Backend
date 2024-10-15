package com.inventory.service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <-- Use this import

import com.inventory.service.dto.InventoryResponse;
import com.inventory.service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;

	@Transactional(readOnly = true)  // <-- Now the readOnly attribute is valid
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		return inventoryRepository.findBySkuCodeIn(skuCode).stream()
				.map(inventory ->
					InventoryResponse.builder().skuCode(inventory.getSkuCode())
					.isInstock(inventory.getQuantity() >0)
					.build()
					).toList();
	}
}
