package alex.vis.inventoryservice.controller;

import alex.vis.inventoryservice.model.Inventory;
import alex.vis.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryRestController {
    private final InventoryRepository inventoryRepository;

    @GetMapping("/{scuCode}")
    public Boolean isInStock(@PathVariable String scuCode) {
        Inventory inventory = inventoryRepository.findByScuCode(scuCode)
                .orElseThrow(() -> new RuntimeException("Cannot find product by scu code " + scuCode));

        return inventory.getStock() > 0;
    }
}
