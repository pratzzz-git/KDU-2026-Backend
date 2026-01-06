package com.company.warehouse;

// This is a highly resource-intensive object
public class ItemPlaceholder {

    public ItemPlaceholder() {
        System.out.println("ALERT: Creating expensive placeholder object!");
    }

    public String getInfo() {
        return "ID-NOT-FOUND: Placeholder Item";
    }
}
