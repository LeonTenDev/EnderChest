package EnderChest;

import EnderChest.commands.Enderchest;
import EnderChest.events.EnderchestListener;
import EnderChest.util.EnderchestConfig;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {

    public static Main plugin;
    private EnderchestConfig enderchestConfig;
    private EnderchestListener enderchestListener;

    @Override
    public void onEnable() {
        plugin = this;
        this.enderchestConfig = new EnderchestConfig(this);
        enderchestConfig.createDefault();
        String prefix = getEnderchestConfig().prefix();
        this.getServer().getLogger().info(prefix + "§aThe plugin has been activate!");
        this.getServer().getCommandMap().register("help", new Enderchest("enderchest", "", getEnderchestConfig().usagemessage(), new String[]{"ec"}));
    }

    @Override
    public void onDisable() {
        String prefix = getEnderchestConfig().prefix();
        this.getServer().getLogger().info(prefix + "§4The plugin has been deactivate!");
    }

    public static Main getPlugin() {
        return plugin;
    }

    public EnderchestConfig getEnderchestConfig() {
        return enderchestConfig;
    }

    public EnderchestListener getEnderchestListener() {
        return enderchestListener;
    }
}
