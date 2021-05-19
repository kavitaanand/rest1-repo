package com.ankur.inventory.domain;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class Item {

    @Valid
    @NotNull(message="Item id cannot be empty")
    @Digits(integer=10,fraction=0)
    private Integer id;

    @Valid
    @NotNull(message="Item name cannot be empty")
    private String name;

    @Valid
    @NotNull(message="Item price cannot be empty")
    @Digits(integer=10,fraction=3)
    private Float price;

    public Item(Integer id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }
}
