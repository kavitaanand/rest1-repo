package com.ankur.inventory.domain;

public class InventoryRemoveItemResponse {

    private Status status;

    public InventoryRemoveItemResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
