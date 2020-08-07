package vanillaplusplus.util;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.*;
import java.util.ArrayList;
import java.util.List;

public class ItemStandRecipes {

    private static final ItemStandRecipe TEST_RECIPE = ItemStandRecipe.create().withCenterIngredient(Items.DIRT).withIngredient(Items.SNOWBALL, 4).withOutput(new ItemStack(Items.DIAMOND_SWORD));
    private static final ItemStandRecipe WATER_WALKING_BOOK = ItemStandRecipe.create().withCenterIngredient(Items.BOOK).withIngredient(Items.SNOWBALL, 1).withIngredient(Items.ICE, 1).withIngredient(Items.LAPIS_LAZULI, 2).withOutput(new ItemStack(Items.ENCHANTED_BOOK), Enchantments.FROST_WALKER, 1);

    private static List<ItemStandRecipe> RECIPES = new ArrayList<>();
    static {
        RECIPES.add(TEST_RECIPE);
        RECIPES.add(WATER_WALKING_BOOK);
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
