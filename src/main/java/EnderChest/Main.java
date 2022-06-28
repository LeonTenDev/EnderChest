package EnderChest;

import EnderChest.commands.EnderChest;
import EnderChest.util.EnderChestConfig;
import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {
    public static Main plugin;
    private EnderChestConfig enderchestConfig;

    public Main() {
    }

    public void onEnable() {
        if (Server.getInstance().getPluginManager().getPlugin("FakeInventories") != null) {
            plugin = this;
            this.enderchestConfig = new EnderChestConfig(this);
            this.enderchestConfig.createDefault();
            String prefix = this.getEnderchestConfig().prefix();
            this.getServer().getLogger().info(prefix + "§aThe plugin has been activate!");
            this.getServer().getCommandMap().register("help", new EnderChest("enderchest", this.getEnderchestConfig().description(), this.getEnderchestConfig().usagemessage(), new String[]{"ec"}));
        } else {
            this.getLogger().alert("§cYou must been install FakeInventories!");
            this.getServer().getPluginManager().getPlugin("EnderChest").onDisable();
        }

    }

    public void onDisable() {
        String prefix = this.getEnderchestConfig().prefix();
        this.getServer().getLogger().info(prefix + "§4The plugin has been deactivate!");
    }

    public static Main getPlugin() {
        return plugin;
    }

    public EnderChestConfig getEnderchestConfig() {
        return this.enderchestConfig;
    }
}