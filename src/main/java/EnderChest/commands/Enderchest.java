package EnderChest.commands;

import EnderChest.Main;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.Task;
import com.nukkitx.fakeinventories.FakeInventoriesListener;
import com.nukkitx.fakeinventories.inventory.*;

import java.util.Map;

public class Enderchest extends Command {

    public static Player player;

    public Enderchest(String name, String description, String usageMessage, String[] aliases) {
        super(name, description, usageMessage, aliases);
        this.setPermission("enderchest.cmd");
        this.commandParameters.put("Enderchest", new CommandParameter[]{
           new CommandParameter("Players", CommandParamType.TARGET, false)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player1 = (Player) sender;
        if (sender instanceof Player){
            if (sender.hasPermission("enderchest.see")){
                if (args.length <= 1){
                    if (args.length == 0) {
                        ChestFakeInventory ec = new ChestFakeInventory();
                        ec.setName("§5Enderchest");
                        ec.setContents(player1.getEnderChestInventory().getContents());
                        player1.addWindow(ec);
                        if (Main.getPlugin().getEnderchestConfig().openEnderchestMessage() == true) {
                            sender.sendMessage(Main.plugin.getEnderchestConfig().prefix() + Main.getPlugin().getEnderchestConfig().open());
                        }
                        ec.addListener(this::onSlotChange);
                    }
                    if (args.length == 1){
                        if (sender.hasPermission("enderchest.see.other") | sender.isOp()){
                            String one = args[0];
                            Player target = Server.getInstance().getPlayer(one);
                            if (target != null){
                                player = target;
                                ChestFakeInventory ec = new ChestFakeInventory();
                                ec.setName("§5Enderchest from §e" + target.getName() + "§5!");
                                ec.setContents(target.getEnderChestInventory().getContents());
                                player1.addWindow(ec);
                                ec.addListener(this::onSlotChangeOther);
                            }else{
                                String prefix = Main.getPlugin().getEnderchestConfig().prefix();
                                sender.sendMessage(prefix + Main.getPlugin().getEnderchestConfig().isNotOnline());
                            }
                        }else{
                            String prefix = Main.getPlugin().getEnderchestConfig().prefix();
                            sender.sendMessage(prefix + Main.getPlugin().getEnderchestConfig().hasNotPermission());
                        }
                    }
                }else{
                    String prefix = Main.getPlugin().getEnderchestConfig().prefix();
                    sender.sendMessage(prefix + this.usageMessage);
                }
            }else{
                String prefix = Main.getPlugin().getEnderchestConfig().prefix();
                sender.sendMessage(prefix + Main.getPlugin().getEnderchestConfig().hasNotPermission());
            }
        }else {
            String prefix = Main.getPlugin().getEnderchestConfig().prefix();
            sender.sendMessage(prefix + Main.getPlugin().getEnderchestConfig().isNotaPlayer());
        }
        return true;
    }
    private void onSlotChange(FakeSlotChangeEvent event){
        if (event.getInventory() instanceof ChestFakeInventory) {
            if (event.getInventory().getName().equalsIgnoreCase("§5Enderchest")) {
                Player player2 = event.getPlayer();
                Server.getInstance().getScheduler().scheduleDelayedTask(new Task() {
                    @Override
                    public void onRun(int currentTick) {
                        Map<Integer, Item> contens = event.getInventory().getContents();
                        player2.getEnderChestInventory().setContents(contens);
                    }
                }, 1);


            }
        }
    }
    private void onSlotChangeOther(FakeSlotChangeEvent event){
        if (event.getInventory() instanceof ChestFakeInventory) {
            if (event.getInventory().getName().equalsIgnoreCase("§5Enderchest from §e" + player.getName() + "§5!")) {
                event.setCancelled(true);

            }
        }

    }
}
