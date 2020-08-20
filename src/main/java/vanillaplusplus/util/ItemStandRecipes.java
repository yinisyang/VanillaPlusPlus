package vanillaplusplus.util;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import vanillaplusplus.common.BlockRegistry;
import vanillaplusplus.common.ItemRegistry;
import vanillaplusplus.entities.StandBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class ItemStandRecipes {

    public static ItemStack getResult(PlayerEntity player, ItemStack centerItem, ItemStack item1, ItemStack item2, ItemStack item3, ItemStack item4) {
        for (ItemStandRecipe recipe: RECIPES) {
            if (recipe.matchesInputs(centerItem, item1, item2, item3, item4)) {
                if (player.experienceLevel >= recipe.getLevelsUsed()) {
                    if (recipe.getLevelsUsed() > 0)
                        player.addExperienceLevels(-recipe.getLevelsUsed());
                    return recipe.getOutput();
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack getResult(PlayerEntity player, StandBlockEntity innerInventory, ArrayList<StandBlockEntity> outerInventories) {
        if (outerInventories.size() != 4)
            throw new IllegalArgumentException();

        return getResult(
                player,
                innerInventory.getStack(0),
                outerInventories.get(0).getStack(0),
                outerInventories.get(1).getStack(0),
                outerInventories.get(2).getStack(0),
                outerInventories.get(3).getStack(0)
        );
    }

    private static final ItemStandRecipe AQUA_AFFINITY = create()
            .withIngredient(Items.DRIED_KELP_BLOCK)
            .withIngredient(Items.SEA_PICKLE)
            .withIngredient(Items.PRISMARINE)
            .withIngredient(Items.TURTLE_EGG)
            .withOutput(Enchantments.AQUA_AFFINITY);

    private static final ItemStandRecipe CURSE_OF_BINDING = create()
            .withIngredient(Items.CHAIN, 3)
            .withIngredient(Items.FERMENTED_SPIDER_EYE)
            .withOutput(Enchantments.BINDING_CURSE);

    private static final ItemStandRecipe CURSE_OF_VANISHING = create()
            .withIngredient(Items.GLASS, 3)
            .withIngredient(Items.FERMENTED_SPIDER_EYE)
            .withOutput(Enchantments.VANISHING_CURSE);

    private static final ItemStandRecipe EFFICIENCY = create()
            .withIngredient(Items.SUGAR, 4)
            .withOutput(Enchantments.EFFICIENCY);
    private static final ItemStandRecipe EFFICIENCY_2 = create()
            .withIngredient(Items.GLOWSTONE_DUST, 3)
            .withIngredient(EFFICIENCY)
            .withOutput(Enchantments.EFFICIENCY, 2);
    private static final ItemStandRecipe EFFICIENCY_3 = create()
            .withIngredient(Items.GLOWSTONE, 3)
            .withIngredient(EFFICIENCY_2)
            .withOutput(Enchantments.EFFICIENCY, 3);
    private static final ItemStandRecipe EFFICIENCY_4 = create()
            .withIngredient(Items.REDSTONE_BLOCK, 3)
            .withIngredient(EFFICIENCY_3)
            .withOutput(Enchantments.EFFICIENCY, 4);
    private static final ItemStandRecipe EFFICIENCY_5 = create()
            .withIngredient(Items.DIAMOND_PICKAXE)
            .withIngredient(Items.CAKE)
            .withIngredient(ItemRegistry.NETHER_STAR_INGOT)
            .withIngredient(EFFICIENCY_4)
            .withOutput(Enchantments.EFFICIENCY, 5);

    private static final ItemStandRecipe FORTUNE = create()
            .withIngredient(Items.IRON_BLOCK, 4)
            .withOutput(Enchantments.FORTUNE);
    private static final ItemStandRecipe FORTUNE_2 = create()
            .withIngredient(Items.GOLD_BLOCK, 3)
            .withIngredient(FORTUNE)
            .withOutput(Enchantments.FORTUNE, 2);
    private static final ItemStandRecipe FORTUNE_3 = create()
            .withIngredient(Items.DIAMOND_BLOCK, 3)
            .withIngredient(FORTUNE_2)
            .withOutput(Enchantments.FORTUNE, 3);

    private static final ItemStandRecipe INFINITY = create()
            .withIngredient(ItemRegistry.NETHER_STAR_INGOT)
            .withIngredient(Items.ARROW, 3)
            .withOutput(Enchantments.INFINITY);

    private static final ItemStandRecipe MENDING = create()
            .withIngredient(Items.DRAGON_BREATH, 2)
            .withIngredient(ItemRegistry.NETHER_STAR_INGOT, 2)
            .withOutput(Enchantments.MENDING);

    private static final ItemStandRecipe PROTECTION = create()
            .withIngredient(Items.OBSIDIAN, 4)
            .withOutput(Enchantments.PROTECTION);
    private static final ItemStandRecipe PROTECTION_2 = create()
            .withIngredient(Items.OBSIDIAN, 3)
            .withIngredient(PROTECTION)
            .withOutput(Enchantments.PROTECTION, 2);
    private static final ItemStandRecipe PROTECTION_3 = create()
            .withIngredient(Items.CRYING_OBSIDIAN, 3)
            .withIngredient(PROTECTION_2)
            .withOutput(Enchantments.PROTECTION, 3);
    private static final ItemStandRecipe PROTECTION_4 = create()
            .withIngredient(ItemRegistry.NETHER_STAR_INGOT)
            .withIngredient(Items.CRYING_OBSIDIAN, 2)
            .withIngredient(PROTECTION_3)
            .withOutput(Enchantments.PROTECTION, 4);

    private static final ItemStandRecipe SHARPNESS = create()
            .withIngredient(Items.QUARTZ, 4)
            .withOutput(Enchantments.SHARPNESS);
    private static final ItemStandRecipe SHARPNESS_2 = create()
            .withIngredient(Items.QUARTZ_BLOCK, 3)
            .withIngredient(SHARPNESS)
            .withOutput(Enchantments.SHARPNESS, 2);
    private static final ItemStandRecipe SHARPNESS_3 = create()
            .withIngredient(Items.DIAMOND, 2)
            .withIngredient(Items.QUARTZ_BLOCK)
            .withIngredient(SHARPNESS_2)
            .withOutput(Enchantments.SHARPNESS, 3);
    private static final ItemStandRecipe SHARPNESS_4 = create()
            .withIngredient(Items.DIAMOND_BLOCK, 3)
            .withIngredient(SHARPNESS_3)
            .withOutput(Enchantments.SHARPNESS, 4);
    private static final ItemStandRecipe SHARPNESS_5 = create()
            .withIngredient(ItemRegistry.NETHER_STAR_INGOT)
            .withIngredient(Items.DIAMOND_SWORD)
            .withIngredient(SHARPNESS_4)
            .withOutput(Enchantments.SHARPNESS, 5);

    private static final ItemStandRecipe SILK_TOUCH = create()
            .withIngredient(Items.MOSSY_COBBLESTONE, 4)
            .withOutput(Enchantments.SILK_TOUCH);

    private static final ItemStandRecipe UNBREAKING = create()
            .withIngredient(Items.CHISELED_STONE_BRICKS, 4)
            .withOutput(Enchantments.UNBREAKING);
    private static final ItemStandRecipe UNBREAKING_2 = create()
            .withIngredient(Items.OBSIDIAN, 3)
            .withIngredient(UNBREAKING)
            .withOutput(Enchantments.UNBREAKING, 2);
    private static final ItemStandRecipe UNBREAKING_3 = create()
            .withIngredient(ItemRegistry.NETHER_STAR_INGOT)
            .withIngredient(Items.DIAMOND_CHESTPLATE)
            .withIngredient(Items.DIAMOND_LEGGINGS)
            .withIngredient(UNBREAKING_2)
            .withOutput(Enchantments.UNBREAKING, 3);

    private static final ItemStandRecipe ENCHANTED_PUMPKIN = create()
            .withCenterIngredient(Items.CARVED_PUMPKIN)
            .withIngredient(ItemRegistry.MAGIC_DUST, 4)
            .withLevelsUsed(0)
            .withOutput(BlockRegistry.ENCHANTED_PUMPKIN.asItem());
    private static final ItemStandRecipe ENCHANTED_PUMPKIN_ALT = create()
            .withCenterIngredient(Items.JACK_O_LANTERN)
            .withIngredient(ItemRegistry.MAGIC_DUST, 4)
            .withLevelsUsed(0)
            .withOutput(BlockRegistry.ENCHANTED_PUMPKIN.asItem());

    private static List<ItemStandRecipe> RECIPES = new ArrayList<>();
    static {
        RECIPES.add(AQUA_AFFINITY);
        RECIPES.add(CURSE_OF_BINDING);
        RECIPES.add(CURSE_OF_VANISHING);
        RECIPES.add(EFFICIENCY);
        RECIPES.add(EFFICIENCY_2);
        RECIPES.add(EFFICIENCY_3);
        RECIPES.add(EFFICIENCY_4);
        RECIPES.add(EFFICIENCY_5);
        RECIPES.add(FORTUNE);
        RECIPES.add(FORTUNE_2);
        RECIPES.add(FORTUNE_3);
        RECIPES.add(INFINITY);
        RECIPES.add(MENDING);
        RECIPES.add(PROTECTION);
        RECIPES.add(PROTECTION_2);
        RECIPES.add(PROTECTION_3);
        RECIPES.add(PROTECTION_4);
        RECIPES.add(SHARPNESS);
        RECIPES.add(SHARPNESS_2);
        RECIPES.add(SHARPNESS_3);
        RECIPES.add(SHARPNESS_4);
        RECIPES.add(SHARPNESS_5);
        RECIPES.add(SILK_TOUCH);
        RECIPES.add(UNBREAKING);
        RECIPES.add(UNBREAKING_2);
        RECIPES.add(UNBREAKING_3);
        RECIPES.add(ENCHANTED_PUMPKIN);
        RECIPES.add(ENCHANTED_PUMPKIN_ALT);
    }

    private static ItemStandRecipe create() {
        return new ItemStandRecipe();
    }
}
