package tp.pr5.observers;

import java.util.List;

import tp.pr5.items.Item;

/**
 * Interface of the observers that want to be notified about the events related
 * to the player (health, score and death), the player inventory, and the items
 * manipulated by the player
 * 
 * @author Grupo03
 * 
 */
public interface PlayerObserver {

    /**
     * Notifies that the player looked the inventory.
     * 
     * @param inventory
     *            New inventory
     */
    void playerLookedInventory(List<Item> inventory);

    /**
     * Notifies that the player inventory has changed.
     * 
     * @param inventory
     *            New inventory
     */
    void inventoryUpdate(List<Item> inventory);

    /**
     * Notifies that the player attributes (health and score) have changed.
     * 
     * @param newHealth
     *            New health value
     * @param newScore
     *            New score value
     */
    void playerUpdate(int newHealth, int newScore);

    /**
     * Notifies the player's death.
     */
    void playerDead();

    /**
     * Notifies that the player wants to examine an item.
     * 
     * @param description
     *            Item description
     */
    void itemLooked(String description);

    /**
     * Notifies that an item has been used.
     * 
     * @param itemName
     *            Name of the item that has been used
     */
    void itemUsed(String itemName);

    /**
     * Notifies that an item is empty.
     * 
     * @param itemName
     *            Name of the empty item
     */
    void itemEmpty(String itemName);
}
