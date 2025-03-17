package com.talentica.workshop.inventory.service;


import com.talentica.workshop.inventory.model.Ingredient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {

    @Value("${inventory.espresso-shot}")
    private int espressoShotQuantity;

    @Value("${inventory.milk}")
    private int milkQuantity;

    @Value("${inventory.milk-foam}")
    private int milkFoamQuantity;

    @Value("${inventory.hot-water}")
    private int hotWaterQuantity;

    private final Map<String, Ingredient> stock = new HashMap<>();

    @PostConstruct
    public void initializeInventory() {
        stock.put("espressoShot", new Ingredient("Espresso Shot", espressoShotQuantity));
        stock.put("milk", new Ingredient("Milk", milkQuantity));
        stock.put("milkFoam", new Ingredient("Milk Foam", milkFoamQuantity));
        stock.put("hotWater", new Ingredient("Hot Water", hotWaterQuantity));
    }

    public Map<String, Ingredient> getStock(){
        return stock;
    }

    public boolean checkStock(String ingredient, int requiredQuantity) {
        Ingredient item = stock.get(ingredient);
        return item != null && item.getQuantity() >= requiredQuantity;
    }

    public void useIngredient(String ingredient, int quantity) {
            stock.get(ingredient).reduceQuantity(quantity);
    }
}
