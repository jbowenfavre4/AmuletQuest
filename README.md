# AmuletQuest

This plugin introduces amulets and questbooks to the game, which will drop from mobs at different percentages that can be customized in the config file. 

## Overview
### Amulets
When dropped, amulets will have no bonus. They can be equipped in the player's off-hand. The amulet will be a nether star labelled "Amulet."

### Quests 
Quests drop in the form of books. When a questbook drops, it will be one of 4 rarities:
1. Common: 50%
2. Rare: 35%
3. Legendary: 10%
4. Mythic: 5%

Additionally, when a questbook is created, it will contain one of three quests, chosen randomly:

1. Kill quest - player must kill a certain number of a specified mob type
2. Find quest - player must find and interact with (right click) a certain type of mob
3. Fishing quest - player must obtain a certain number of items through fishing

The questbook will be labelled with its rarity. The more rare books will offer more difficult quests, but will also offer better rewards upon completion. The player can open the book to see the quest associated with it, and the book's text will update as progress is made toward the quest. Upon completion, the player will be given essence as a reward, and the book's text will be updated with the text "DONE" in green.

### Essence
Upon comopleting a quest, the player will be rewarded with Essence of the same rarity as the quest that they completed. The essence will be randomly associated with one of four attributes: 
1. Max health
2. Armor
3. Strength (attack damage)
4. Speed

Depending on the rarity, essence can be infused into an amulet to add an attribute modifier to the amulet, providing a bonus to one of the above attributes when the amulet is in the player's off-hand. The bonus provided by the essence will depend on the rarity.

### Upgrading
Upgrading an amulet can be done at an anvil. The amulet must be placed in the first slot, and the essence in the second slot. The upgrade will have an experience cost, depending on the rarity of the essence and the number of times that the amulet has already been upgraded. 

## Customization
You can customize the rate at which both amulets and questbooks drop by changing the `quest-drop-chance` and `amulet-drop-chance` in the config file. The values must be an integer, and if value is invalid, it will default to 10%. To change the values:
1. Start the server with the plugin jar file placed in the plugins folder
2. Once the server is up and running, you will see a new folder created in your plugins folder called 'AmuletQuest'. In that folder, a file called `config.yml` will be created.
3. Open the config file and change the values as desired. If you wish to disable the plugin, you can do so by changing the `enable-plugin` value to `false`. 
4. Restart your server. On startup, the drop percentages will be printed to your server output. 

Note: Do not make any changes to the `quests.json` file. 

## Known issues

- ~~Amulets also behaving like normal nether stars~~ ([Issue #1 (Fixed in 1.0.1)](https://github.com/jbowenfavre4/AmuletQuest/issues/1))
- ~~Essence also behaving like normal glowstone dust~~ ([Issue #2 (Fixed in 1.0.1)](https://github.com/jbowenfavre4/AmuletQuest/issues/2))

## Future updates

- Combining amulets

## Version History

### [v1.0.1](https://github.com/jbowenfavre4/AmuletQuest/releases/tag/v1.0.1)
- Release Date: TBD
- Bug fixes:
  - fixed amulets and essence behaving like normal nether stars and glowstone dust in crafting

### [v1.0.0](https://github.com/jbowenfavre4/AmuletQuest/releases/tag/v1.0.0)
- Release Date: 2023-11-20
- Initial Release
  - Initial release of the plugin for balance testing
