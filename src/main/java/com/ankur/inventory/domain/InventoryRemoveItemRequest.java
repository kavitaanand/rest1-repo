package com.ankur.inventory.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class InventoryRemoveItemRequest {

    @Valid
    @NotNull(message="Item id cannot be empty")
    private Integer itemId;


    public InventoryRemoveItemRequest(){
    }

    public InventoryRemoveItemRequest(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemId() {
        return itemId;
    }
}
