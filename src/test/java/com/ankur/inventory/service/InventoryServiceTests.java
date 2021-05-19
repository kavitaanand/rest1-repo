package com.ankur.inventory.service;


import com.ankur.inventory.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryServiceTests {

    private InventoryService inventoryService;

    @Test
    public void findById() {
        InventoryService service = new InventoryService();
        ResponseEntity<InventoryFindByIdResponse> response = (ResponseEntity<InventoryFindByIdResponse>) service.findById(new InventoryFindByIdRequest(1));
        assertEquals("Lamp",response.getBody().getItem().getName());
    }

    @Test
    public void findByName() {
        InventoryService service = new InventoryService();
        ResponseEntity<InventoryFindByNameResponse> response = (ResponseEntity<InventoryFindByNameResponse>) service.findByName(new InventoryFindByNameRequest(("Lamp")));
        Collection<Item> items =  response.getBody().getItems();
        items.forEach(item->{
            assertTrue(item.getName().contains("Lamp"));
           // throw new RuntimeException("causing the build to fail");
        });
    }


}
