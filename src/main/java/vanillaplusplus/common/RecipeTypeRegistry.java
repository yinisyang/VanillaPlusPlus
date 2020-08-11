package vanillaplusplus.common;

import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import vanillaplusplus.recipe.GrindingRecipe;

import static vanillaplusplus.VanillaPlusPlusInitializer.MOD_ID;

public class RecipeTypeRegistry {

    public static RecipeType<GrindingRecipe> GRINDING = register("grinding");
    public static CookingRecipeSerializer<GrindingRecipe> GRINDING_SERIALIZER = new CookingRecipeSerializer(GrindingRecipe::new, 200);

    public static void init() {
        RecipeSerializer
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, "grinding"), GRINDING_SERIALIZER);
    }

    private static <T extends Recipe<?>> RecipeType<T> register(final String string) {
        return Registry.register(Registry.RECIPE_TYPE, (Identifier)(new Identifier(string)), new RecipeType<T>() {
            public String toString() {
                return string;
            }
        });
    }
}
