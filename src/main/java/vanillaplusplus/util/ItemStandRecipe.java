package vanillaplusplus.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemStandRecipe {

    private ItemStack output;
    private Item centerIngredient;
    private Map<Item, Integer> ingredients;

    public static ItemStandRecipe create() {
        return new ItemStandRecipe();
    }

    private ItemStandRecipe() {
        this.ingredients = new HashMap<>();
    }

    ItemStandRecipe withIngredient(Item item, int amount) {
        this.ingredients.merge(item, amount, Integer::sum);
        return this;
    }

    ItemStandRecipe withCenterIngredient(Item item) {
        this.centerIngredient = item;
        return this;
    }

    ItemStandRecipe withOutput(ItemStack item) {
        this.output = item;
        return this;
    }

    ItemStandRecipe withOutput(ItemStack item, Enchantment enchantment, int level) {
        EnchantedBookItem.addEnchantment(item, new EnchantmentLevelEntry(enchantment, level));
        this.output = item;
        return this;
    }

    boolean matchesInputs(Item centerItem, Item... testInputs) {
       Map<Item, Integer> test = convertToMap(testInputs);

       return this.ingredients.equals(test) && this.centerIngredient == centerItem;
    }

    ItemStack getOutput() {
        return this.output;
    }

    private Map<Item, Integer> convertToMap(Item[] convertInputs) {
        Map<Item, Integer> temp = new HashMap<>();
        for (Item i: convertInputs) {
            temp.merge(i, 1, Integer::sum);
        }
        return temp;
    }
}
