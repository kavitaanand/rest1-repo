package com.ankur.inventory.controller;

import com.ankur.inventory.domain.InventoryAddItemRequest;
import com.ankur.inventory.domain.InventoryAddItemResponse;
import com.ankur.inventory.domain.InventoryFindByIdRequest;
import com.ankur.inventory.domain.InventoryFindByIdResponse;
import com.ankur.inventory.domain.InventoryFindByNameRequest;
import com.ankur.inventory.domain.InventoryFindByNameResponse;
import com.ankur.inventory.domain.InventoryListAllResponse;
import com.ankur.inventory.domain.InventoryRemoveItemRequest;
import com.ankur.inventory.domain.InventoryRemoveItemResponse;
import com.ankur.inventory.domain.InventoryUpdateItemRequest;
import com.ankur.inventory.domain.InventoryUpdateItemResponse;
import com.ankur.inventory.domain.ServiceErrorResponse;
import com.ankur.inventory.service.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory/services")
@Api(value = "/inventory/services", tags = ("Inventory Management"))
public class InventoryController {

    private static final String CLIENT_ID = "client-id";
    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @RequestMapping(value = "findByName", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "findByName", notes = "Finds an inventory item by name", nickname = "findByName")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryFindByNameResponse.class)})
    public ResponseEntity<?> findByName(@RequestHeader(value = CLIENT_ID) String clientId,
                                    @Valid @RequestBody InventoryFindByNameRequest request) {
        return inventoryService.findByName(request);
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "remove", notes = "Removes an item from the Inventory", nickname = "remove")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryRemoveItemResponse.class)})
    public ResponseEntity<?> remove(@RequestHeader(value = CLIENT_ID) String clientId,
                                 @Valid @RequestBody InventoryRemoveItemRequest request) {
        return inventoryService.remove(request);
    }


    @RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "update", notes = "Updates an existing item from the Inventory", nickname = "update")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryUpdateItemResponse.class)})
    public ResponseEntity<?> update(@RequestHeader(value = CLIENT_ID) String clientId,
                                 @Valid @RequestBody InventoryUpdateItemRequest request) {
        return inventoryService.update(request);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "add", notes = "Adds a new item in the Inventory", nickname = "add")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryAddItemResponse.class)})
    public ResponseEntity<?> add(@RequestHeader(value = CLIENT_ID) String clientId,
                                      @Valid @RequestBody InventoryAddItemRequest request) {
        return inventoryService.add(request);
    }


    @RequestMapping(value = "findById", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "findById", notes = "Find a single inventory item by id", nickname = "findById")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryFindByIdResponse.class)})
    public ResponseEntity<?> findById(@RequestHeader(value = CLIENT_ID) String clientId,
                                      @Valid @RequestBody InventoryFindByIdRequest request) {
        return inventoryService.findById(request);
    }

    @RequestMapping(value = "listall", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "listall", notes = "Get all inventory items", nickname = "listall")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryListAllResponse.class)})
    public ResponseEntity<?> listall(@RequestHeader(value = CLIENT_ID) String clientId
    ) {
        return inventoryService.listAll();
    }


}
