package com.company.warehouse;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InventorySearch {

    // =====================================
    // 1. Safe Lookup with Lazy Fallback
    // =====================================
    public static Object safeFindItem(String id) {

        // Optional wraps possible null
        // ItemPlaceholder is created ONLY if lookup fails
        return Optional.ofNullable(Inventory.findItem(id))
                .orElseGet(ItemPlaceholder::new);
    }

    // =====================================
    // 2. Flatten Nested Inventory IDs
    // =====================================
    public static Set<String> extractUniqueItemIds(Inventory inventory) {

        return inventory.getPalletItemIds()
                .stream()               // Stream<List<String>>
                .flatMap(List::stream)  // Stream<String>
                .collect(Collectors.toSet()); // Remove duplicates
    }

    public static void main(String[] args) {

        // -------- Safe Lookup Test --------
        Object result = safeFindItem("A100");
        System.out.println("Returned type: " + result.getClass().getSimpleName());

        // -------- Flatten Inventory Test --------
        Inventory inventory = Inventory.findItem("A100");

        if (inventory != null) {
            Set<String> ids = extractUniqueItemIds(inventory);
            System.out.println("Unique Item IDs: " + ids);
        }

        // -------- Failure Case --------
        Object failedResult = safeFindItem("X999");
        System.out.println("Returned type: " + failedResult.getClass().getSimpleName());
    }
}
