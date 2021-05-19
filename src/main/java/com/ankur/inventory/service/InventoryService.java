package com.ankur.inventory.service;

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
import com.ankur.inventory.domain.Item;
import com.ankur.inventory.domain.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private Map<Integer, Item> db = new HashMap<>();


    public InventoryService() {
        db.put(1, new Item(1, "Lamp", 99.67f));
        db.put(2, new Item(2, "Table", 100.33f));
        db.put(3, new Item(3, "Chair", 65.99f));
        db.put(4, new Item(4, "Alladin's Lamp", 67.99f));
    }


    public ResponseEntity<?> listAll() {
        return new ResponseEntity<InventoryListAllResponse>(new InventoryListAllResponse(db.values()), HttpStatus.OK);
    }

    public ResponseEntity<?> findById(InventoryFindByIdRequest request) {
        Integer id = request.getItemId();
        Item item = db.get(id);
        if(item!=null) {
            return new ResponseEntity<InventoryFindByIdResponse>(new InventoryFindByIdResponse(item),
                    HttpStatus.OK);
        }
        Item itemNotFound = new Item(-1,"Not Found",-1f);
        return new ResponseEntity<InventoryFindByIdResponse>(new InventoryFindByIdResponse(itemNotFound),
                HttpStatus.OK);
    }


    public ResponseEntity<?> add(InventoryAddItemRequest request) {
        InventoryAddItemResponse response = null;
        Item item = request.getItem();
        if (item != null && item.getId() != null) {
            if (db.containsKey(item.getId())) {
                response = new InventoryAddItemResponse(Status.CANNOT_ADD_ITEM_ALREADY_EXISTS);
            } else {
                db.put(request.getItem().getId(), request.getItem());
                response = new InventoryAddItemResponse(Status.SUCCESS);
            }
        }
        return new ResponseEntity<InventoryAddItemResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> update(InventoryUpdateItemRequest request) {
        InventoryUpdateItemResponse response = null;

        Item item = request.getItem();
        if (item != null && item.getId() != null && db.containsKey(item.getId())) {
            db.put(item.getId(), item);
            response = new InventoryUpdateItemResponse(Status.SUCCESS);
        } else {
            response = new InventoryUpdateItemResponse(Status.CANNOT_UPDATE_ITEM_DOES_NOT_EXIST);
        }

        return new ResponseEntity<InventoryUpdateItemResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> remove(InventoryRemoveItemRequest request) {

        InventoryRemoveItemResponse response = null;

        Integer itemId = request.getItemId();
        if (itemId != null && db.containsKey(itemId)) {
            db.remove(itemId);
            response = new InventoryRemoveItemResponse(Status.SUCCESS);
        } else {
            response = new InventoryRemoveItemResponse(Status.CANNOT_REMOVE_ITEM_DOES_NOT_EXIST);
        }

        return new ResponseEntity<InventoryRemoveItemResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> findByName(InventoryFindByNameRequest request) {
        List<Item> result = new ArrayList<>();
        Status status = Status.NO_NAME_MATCH;
        String name = StringUtils.trimToNull(request.getName());
        if (name != null) {
            for (Item item : db.values()) {
                if( StringUtils.containsIgnoreCase(item.getName(),name)){
                    result.add(item);
                    status=Status.SUCCESS;
                }
            }
        }
        InventoryFindByNameResponse response = new InventoryFindByNameResponse(result,status);
        return new ResponseEntity<InventoryFindByNameResponse>(response, HttpStatus.OK);
    }
}
