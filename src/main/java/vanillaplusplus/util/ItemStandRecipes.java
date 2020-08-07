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
            .withOutput(new ItemStack(Items.ENCHANTED_BOOK), Enchantments.FROST_WALKER, 1);
    private static final ItemStandRecipe SHARPNESS_ONE = ItemStandRecipe.create()
            .withCenterIngredient(Items.BOOK)
            .withIngredient(Items.QUARTZ, 3)
            .withIngredient(Items.LAPIS_LAZULI, 1)
            .withOutput(new ItemStack(Items.ENCHANTED_BOOK), Enchantments.SHARPNESS, 1);
    private static final ItemStandRecipe SHARPNESS_FIVE = ItemStandRecipe.create()
            .withCenterIngredient(Items.BOOK)
            .withIngredient(Items.DIAMOND_BLOCK, 2)
            .withIngredient(Items.LAPIS_BLOCK, 2)
            .withOutput(new ItemStack(Items.ENCHANTED_BOOK), Enchantments.SHARPNESS, 5);

    private static List<ItemStandRecipe> RECIPES = new ArrayList<>();
    static {
        RECIPES.add(FROST_WALKER);
        RECIPES.add(SHARPNESS_ONE);
        RECIPES.add(SHARPNESS_FIVE);
    }

    public static ItemStack getResultIfRecipeExists(Item centerItem, Item item1, Item item2, Item item3, Item item4) {
        for (ItemStandRecipe recipe: RECIPES) {
            if (recipe.matchesInputs(centerItem, item1, item2, item3, item4)) {
                return recipe.getOutput();
            }
        }
        return ItemStack.EMPTY;
    }
}
