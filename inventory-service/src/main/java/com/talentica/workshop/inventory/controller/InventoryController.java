package com.talentica.workshop.inventory.controller;


import com.talentica.workshop.inventory.model.Ingredient;
import com.talentica.workshop.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


    @GetMapping("/stock")
    public Map<String, Ingredient> getStock() {
        return inventoryService.getStock();
    }

    @PostMapping("/used")
    public boolean useIngredient(@RequestBody Map<String, Integer> ingredients){
        boolean available = true;
        for(String ingredient : ingredients.keySet()){
            int quantity = ingredients.get(ingredient);
            boolean avail = inventoryService.checkStock(ingredient, quantity);
            if(!avail){
                available = false;
                break;
            }
        }
        if(available) {
            for (String ingredient : ingredients.keySet()) {
                int quantity = ingredients.get(ingredient);
                inventoryService.useIngredient(ingredient, quantity);
            }
        }
        return available;
    }
}
