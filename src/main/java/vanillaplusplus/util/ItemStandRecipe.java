package vanillaplusplus.util;

import net.minecraft.block.Blocks;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class ItemStandRecipe {

    private ItemStack output;
    private ItemStack centerIngredient;
    private Map<Item, Integer> ingredients;
    private EnchantmentLevelEntry enchantedBookIngredientData;
    private int baseLevelsUsed;

    public ItemStandRecipe() {
        this.ingredients = new HashMap<>();
        this.centerIngredient = new ItemStack(Items.BOOK); // Have book be default center item
        this.baseLevelsUsed = 1;
    }

    ItemStandRecipe withIngredient(Item item, int amount) {
        this.ingredients.merge(item, amount, Integer::sum);
        return this;
    }

    ItemStandRecipe withIngredient(Item item) {
        return withIngredient(item, 1);
    }

    // Adds the output of the incoming recipe to the ingredients
    ItemStandRecipe withIngredient(ItemStandRecipe recipe) {
        if (recipe.getOutput().getItem() == Items.ENCHANTED_BOOK) {
            ListTag listTag = EnchantedBookItem.getEnchantmentTag(recipe.getOutput());
            CompoundTag cTag = listTag.getCompound(0);
            int level = cTag.getShort("lvl");
            String id = cTag.getString("id");
            return withEnchantedBookIngredient(Registry.ENCHANTMENT.get(new Identifier(id)), level);
        }
        return withIngredient(recipe.getOutput().getItem());
    }

    ItemStandRecipe withEnchantedBookIngredient(Enchantment enchantment, int level) {
        this.enchantedBookIngredientData = new EnchantmentLevelEntry(enchantment, level);
        this.ingredients.merge(Items.ENCHANTED_BOOK, 1, Integer::sum);
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

    ItemStandRecipe withOutput(Item item) {
        return withOutput(new ItemStack(item));
    }

    ItemStandRecipe withOutput(Item item, Enchantment enchantment, int level) {
        ItemStack stack = new ItemStack(item);
        EnchantedBookItem.addEnchantment(stack, new EnchantmentLevelEntry(enchantment, level));
        this.output = stack;
        return this;
    }

    ItemStandRecipe withOutput(Enchantment enchantment, int level) {
        return withOutput(Items.ENCHANTED_BOOK, enchantment, level);
    }

    ItemStandRecipe withOutput(Enchantment enchantment) {
        return withOutput(enchantment, 1);
    }

    ItemStandRecipe withLevelsUsed(int levels) {
        this.baseLevelsUsed = levels;
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

    public ItemStack getOutput() {
        return this.output.copy();
    }

    public int getLevelsUsed() {
        if (this.enchantedBookIngredientData != null) {
            return this.baseLevelsUsed * this.enchantedBookIngredientData.level;
        }
        return this.baseLevelsUsed;
    }

    private Map<Item, Integer> convertToMap(ItemStack[] convertInputs) {
        Map<Item, Integer> temp = new HashMap<>();
        for (ItemStack i: convertInputs) {
            temp.merge(i.getItem(), 1, Integer::sum);
        }
        return temp;
    }
}
