package vanillaplusplus.util;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.*;
import java.util.ArrayList;
import java.util.List;

public class ItemStandRecipes {

    private static final ItemStandRecipe FROST_WALKER = ItemStandRecipe.create()
            .withCenterIngredient(Items.BOOK)
            .withIngredient(Items.SNOWBALL, 1)
            .withIngredient(Items.ICE, 1)
            .withIngredient(Items.LAPIS_LAZULI, 2)
            .withOutput(Items.ENCHANTED_BOOK, Enchantments.FROST_WALKER, 1);
    private static final ItemStandRecipe SHARPNESS_ONE = ItemStandRecipe.create()
            .withCenterIngredient(Items.BOOK)
            .withIngredient(Items.QUARTZ, 4)
            .withOutput(Items.ENCHANTED_BOOK, Enchantments.SHARPNESS, 1);
    private static final ItemStandRecipe SHARPNESS_TWO = ItemStandRecipe.create()
            .withCenterIngredient(Items.BOOK)
            .withIngredient(Items.QUARTZ_BLOCK, 3)
            .withEnchantedBookIngredient(Enchantments.SHARPNESS, 1)
            .withOutput(Items.ENCHANTED_BOOK, Enchantments.SHARPNESS, 2);
    private static final ItemStandRecipe SHARPNESS_THREE = ItemStandRecipe.create()
            .withCenterIngredient(Items.BOOK)
            .withIngredient(Items.DIAMOND, 2)
            .withIngredient(Items.QUARTZ_BLOCK, 1)
            .withEnchantedBookIngredient(Enchantments.SHARPNESS, 2)
            .withOutput(Items.ENCHANTED_BOOK, Enchantments.SHARPNESS, 3);
    private static final ItemStandRecipe SHARPNESS_FOUR = ItemStandRecipe.create()
            .withCenterIngredient(Items.BOOK)
            .withIngredient(Items.DIAMOND_BLOCK, 1)
            .withIngredient(Items.QUARTZ_BLOCK, 2)
            .withEnchantedBookIngredient(Enchantments.SHARPNESS, 3)
            .withOutput(Items.ENCHANTED_BOOK, Enchantments.SHARPNESS, 4);
    private static final ItemStandRecipe SHARPNESS_FIVE = ItemStandRecipe.create()
            .withCenterIngredient(Items.BOOK)
            .withIngredient(Items.DIAMOND_BLOCK, 3)
            .withEnchantedBookIngredient(Enchantments.SHARPNESS, 4)
            .withOutput(Items.ENCHANTED_BOOK, Enchantments.SHARPNESS, 5);

    private static List<ItemStandRecipe> RECIPES = new ArrayList<>();
    static {
        RECIPES.add(FROST_WALKER);
        RECIPES.add(SHARPNESS_ONE);
        RECIPES.add(SHARPNESS_TWO);
        RECIPES.add(SHARPNESS_THREE);
        RECIPES.add(SHARPNESS_FOUR);
        RECIPES.add(SHARPNESS_FIVE);
    }

    public static ItemStack getResultIfRecipeExists(ItemStack centerItem, ItemStack item1, ItemStack item2, ItemStack item3, ItemStack item4) {
        for (ItemStandRecipe recipe: RECIPES) {
            if (recipe.matchesInputs(centerItem, item1, item2, item3, item4)) {
                return recipe.getOutput();
            }
        }
        return ItemStack.EMPTY;
    }
}
