package vanillaplusplus.recipe;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import vanillaplusplus.common.BlockRegistry;
import vanillaplusplus.common.RecipeTypeRegistry;

public class GrindingRecipe extends AbstractCookingRecipe {

    public GrindingRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(RecipeTypeRegistry.GRINDING, id, group, input, output, experience, cookTime);
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(BlockRegistry.GRINDER);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }
}
