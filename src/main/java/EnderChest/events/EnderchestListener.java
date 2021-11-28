package EnderChest.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.item.Item;
import com.nukkitx.fakeinventories.inventory.DoubleChestFakeInventory;
import com.nukkitx.fakeinventories.inventory.FakeSlotChangeEvent;

import java.net.http.WebSocket;
import java.util.Map;

public class EnderchestListener implements Listener {

    public void onSlotChange(FakeSlotChangeEvent event){
        if (event.getInventory() instanceof DoubleChestFakeInventory) {
            if (event.getInventory().getName().equalsIgnoreCase("§5Enderchest")) {
                Player player = event.getPlayer();
                Map<Integer, Item> contens = event.getInventory().getContents();
                player.getEnderChestInventory().setContents(contens);
            }
        }
    }
}
