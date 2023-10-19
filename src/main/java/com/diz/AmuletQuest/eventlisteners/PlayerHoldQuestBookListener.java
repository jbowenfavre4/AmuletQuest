package com.diz.AmuletQuest.eventlisteners;
import com.diz.AmuletQuest.QuestBookWriter;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;

// listens for the player to hold a quest book and updates it from the PlayerQuestData json file
public class PlayerHoldQuestBookListener implements Listener {

    @EventHandler
    public void onPlayerHoldQuestBook(PlayerItemHeldEvent e) {

        // get player inventory and check if they are now holding a quest book
        PlayerInventory playerInv = e.getPlayer().getInventory();

        if (playerInv.getItem(e.getNewSlot()) != null && playerInv.getItem(e.getNewSlot()).getType() == Material.WRITTEN_BOOK) {
            ItemStack inHand = playerInv.getItem(e.getNewSlot());
            if (inHand.getItemMeta() instanceof BookMeta && ((BookMeta) inHand.getItemMeta()).getTitle().equals("dizQuestBook")) {

                // pass the player and the book to the book writer to update the book
                QuestBookWriter.writeToBook(e.getPlayer().getUniqueId(), inHand);
            }
        }
    }

}
