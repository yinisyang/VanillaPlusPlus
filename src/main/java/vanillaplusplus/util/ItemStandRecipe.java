package vanillaplusplus.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class ItemStandRecipe {

    private ItemStack output;
    private ItemStack centerIngredient;
    private Map<Item, Integer> ingredients;
    private EnchantmentLevelEntry enchantedBookIngredientData;

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

    ItemStandRecipe withEnchantedBookIngredient(Enchantment enchantment, int level) {
        ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
        this.enchantedBookIngredientData = new EnchantmentLevelEntry(enchantment, level);
        this.ingredients.merge(book.getItem(), 1, Integer::sum);
        return this;
    }

    ItemStandRecipe withCenterIngredient(Item item) {
        this.centerIngredient = new ItemStack(item);
        return this;
    }

    ItemStandRecipe withOutput(ItemStack item) {
        this.output = item;
        return this;
    }

    ItemStandRecipe withOutput(Item item, Enchantment enchantment, int level) {
        ItemStack stack = new ItemStack(item);
        EnchantedBookItem.addEnchantment(stack, new EnchantmentLevelEntry(enchantment, level));
        this.output = stack;
        return this;
    }

    boolean matchesInputs(ItemStack centerItem, ItemStack... testInputs) {
       Map<Item, Integer> test = convertToMap(testInputs);

       boolean enchantmentMatches = true;
       if (this.enchantedBookIngredientData != null) {
           for (ItemStack item : testInputs) {
               if (item.getItem() == Items.ENCHANTED_BOOK) {
                   ListTag tag = EnchantedBookItem.getEnchantmentTag(item);
                   CompoundTag cTag = tag.getCompound(0);
                   int level = cTag.getShort("lvl");
                   String id = cTag.getString("id");
                   String nameSpace = Registry.ENCHANTMENT.getId(enchantedBookIngredientData.enchantment).getNamespace();
                   String path = Registry.ENCHANTMENT.getId(enchantedBookIngredientData.enchantment).getPath();
                   enchantmentMatches = (level == this.enchantedBookIngredientData.level) && (nameSpace + ":" + path).equals(id);
               }
           }
       }


       return this.ingredients.equals(test) && this.centerIngredient.isItemEqual(centerItem) && enchantmentMatches;
    }

    ItemStack getOutput() {
        return this.output;
    }

    private Map<Item, Integer> convertToMap(ItemStack[] convertInputs) {
        Map<Item, Integer> temp = new HashMap<>();
        for (ItemStack i: convertInputs) {
            temp.merge(i.getItem(), 1, Integer::sum);
        }
        return temp;
    }
}
