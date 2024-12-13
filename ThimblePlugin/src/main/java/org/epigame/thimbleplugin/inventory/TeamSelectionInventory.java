package org.epigame.thimbleplugin.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.epigame.thimbleplugin.ThimblePlugin;
import org.jetbrains.annotations.NotNull;

public class TeamSelectionInventory implements InventoryHolder {
    private final Inventory inv;

    public TeamSelectionInventory() {
        this.inv = ThimblePlugin.getInstance().getServer().createInventory(this, 9);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inv;
    }
}
