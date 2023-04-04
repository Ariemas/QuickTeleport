package me.arimas.quickteleport;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class QuickTeleport extends JavaPlugin {

    private NamespacedKey locationKey;

    @Override
    public void onEnable() {
        locationKey = new NamespacedKey(this, "quick_teleport_location");
        getCommand("setquickteleport").setExecutor(this);
        getCommand("quickteleport").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        if (command.getName().equalsIgnoreCase("setquickteleport")) {
            if (!player.hasPermission("quickteleport.set")) {
                player.sendMessage("§cYou don't have permission to set a quick teleport location.");
                return true;
            }

            Location location = player.getLocation();
            dataContainer.set(locationKey, PersistentDataType.STRING, location.getWorld().getName() + "," +
                    location.getX() + "," + location.getY() + "," + location.getZ() + "," +
                    location.getYaw() + "," + location.getPitch());
            player.sendMessage("§aQuick teleport location set successfully.");

        } else if (command.getName().equalsIgnoreCase("quickteleport")) {
            if (!player.hasPermission("quickteleport.teleport")) {
                player.sendMessage("§cYou don't have permission to teleport to the quick teleport location.");
                return true;
            }

            if (!dataContainer.has(locationKey, PersistentDataType.STRING)) {
                player.sendMessage("§cYou haven't set a quick teleport location yet.");
                return true;
            }

            String locationString = dataContainer.get(locationKey, PersistentDataType.STRING);
            String[] locationData = locationString.split(",");
            Location tpLocation = new Location(getServer().getWorld(locationData[0]),
                    Double.parseDouble(locationData[1]),
                    Double.parseDouble(locationData[2]),
                    Double.parseDouble(locationData[3]),
                    Float.parseFloat(locationData[4]),
                    Float.parseFloat(locationData[5]));

            player.teleport(tpLocation);
            player.sendMessage("§aTeleported to your quick teleport location.");
        }

        return true;
    }
}