# QuickTeleport

A simple Minecraft Paper plugin that allows players to set a quick teleport location and teleport to it with ease.

## Features

* Set a quick teleport location with **'/setquickteleport'**
* Teleport to the set location with **'/quickteleport'**

## Installation

* [Download the latest version of the plugin](https://www.spigotmc.org/members/arimas.1701565/) from the releases page.
* Place the downloaded **'.jar'** file into your server's **'plugins'** folder.
* Restart your server or load the plugin using a plugin manager.

## Usage

### Commands
* **'/setquickteleport'**: Set the player's current location as their quick teleport location.
* **'/quickteleport**': Teleport the player to their previously set quick teleport location.

### Permissions
* **'quickteleport.set'**: Allows setting the quick teleport location.Default: true (all players)
* **'quickteleport.teleport'**: Allows teleporting to the quick teleport location. Default: true (all players)

## Configuration
There is no configuration file for this plugin, as it stores the quick teleport locations using the PersistentDataContainer method provided by the Spigot API (Plugin using Paper).

## License
This project is licensed under the MIT License. See the LICENSE file for details.