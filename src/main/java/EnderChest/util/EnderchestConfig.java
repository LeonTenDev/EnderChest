package EnderChest.util;

import EnderChest.Main;
import cn.nukkit.utils.Config;

import java.io.File;

public class EnderchestConfig {


    private Main plugin;
    private Config config;
    private File file;

    public EnderchestConfig(Main plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "config.yml");
        this.config = new cn.nukkit.utils.Config(this.file, cn.nukkit.utils.Config.YAML);
    }

    public void createDefault(){
        this.addDefault("enderchest.prefix", "§5EnderChest §f» ");
        this.addDefault("enderchest.message.open", "§3You have open the §5EnderChest§3!");
        this.addDefault("enderchest.message.usageMessage", "§4Please use /ec!");
        this.addDefault("enderchest.message.hasNotPermission", "§4You can't use this command!");
        this.addDefault("enderchest.message.isNotaPlayer", "§4You are not a player!");
        this.addDefault("enderchest.message.playerIsNotOnline", "§4This Player is not online!");
        this.addDefault("enderchest.options.openEnderchestMessage", true);
    }

    public String prefix(){
        return this.config.getString("enderchest.prefix");
    }
    public String open(){
        return this.config.getString("enderchest.message.open");
    }
    public String usagemessage(){
        return this.config.getString("enderchest.message.usageMessage");
    }
    public String hasNotPermission(){
        return this.config.getString("enderchest.message.hasNotPermission");
    }
    public String isNotaPlayer(){
        return this.config.getString("enderchest.message.isNotaPlayer");
    }
    public String isNotOnline(){
        return this.config.getString("enderchest.message.playerIsNotOnline");
    }
    public boolean openEnderchestMessage(){
        return this.config.getBoolean("enderchest.options.openEnderchestMessage");
    }

    public void addDefault(String path, Object object){
        if (!this.config.exists(path)){
            this.config.set(path, object);
            this.config.save(this.file);
        }
    }
}
