package me.El_Chupe.hiddensyntaxblocker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HiddenSyntaxBlockerReloaded extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().substring(1).split(" ")[0];
        if(!command.contains(":")) return;
        String namespace = command.split("\\:")[0];
        if(event.getPlayer().hasPermission("hiddensyntaxblocker.allow."+namespace)
                || event.getPlayer().hasPermission("hiddensyntaxblocker.allow.all")) return;
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("noPermMessage")));
        event.setCancelled(true);
    }
}
