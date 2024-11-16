package org.epigame.mcGames;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EntityListener implements Listener {

    @EventHandler
    public static void onEntityInteract(@NotNull PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof Villager villager) {
            PersistentDataContainer container = villager.getPersistentDataContainer();
            if (!container.has(McGames.NAMESPACE)) {
                return;
            }

            String server = container.get(McGames.NAMESPACE, PersistentDataType.STRING);
            if (server == null) {
                return;
            }

            Player player = e.getPlayer();

            /* Sends plugin message to proxy (https://docs.papermc.io/paper/dev/plugin-messaging) */
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(McGames.getInstance(), "BungeeCord", out.toByteArray());
        }
    }
}
