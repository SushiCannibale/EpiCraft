package org.epigame.lobbyplugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class EntityListener implements Listener {
    @EventHandler
    public static void onEntityInteract(@NotNull PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof Villager villager) {
            PersistentDataContainer container = villager.getPersistentDataContainer();
            if (!container.has(LobbyPlugin.NAMESPACE)) {
                return;
            }

            String server = container.get(LobbyPlugin.NAMESPACE, PersistentDataType.STRING);
            if (server == null) {
                return;
            }

            Player player = e.getPlayer();

            /* Sends plugin message to proxy (https://docs.papermc.io/paper/dev/plugin-messaging) */
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(LobbyPlugin.getInstance(), "BungeeCord", out.toByteArray());

            e.setCancelled(true); // Prevent showing offers
        }
    }
}
