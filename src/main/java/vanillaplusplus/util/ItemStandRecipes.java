package vanillaplusplus.util;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import vanillaplusplus.common.ItemRegistry;

import java.util.ArrayList;
import java.util.List;

public class ItemStandRecipes {

    public static ItemStack getResultIfRecipeExists(PlayerEntity player, ItemStack centerItem, ItemStack item1, ItemStack item2, ItemStack item3, ItemStack item4) {
        for (ItemStandRecipe recipe: RECIPES) {
            if (recipe.matchesInputs(centerItem, item1, item2, item3, item4)) {
                if (player.totalExperience >= recipe.getExperienceUsed()) {
                    player.addExperience(-1 * recipe.getExperienceUsed());
                    return recipe.getOutput();
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private static final ItemStandRecipe AQUA_AFFINITY = create()
            .withIngredient(Items.DRIED_KELP_BLOCK)
            .withIngredient(Items.SEA_PICKLE)
            .withIngredient(Items.PRISMARINE)
            .withIngredient(Items.TURTLE_EGG)
            .withOutput(Enchantments.AQUA_AFFINITY, 1);

    private static final ItemStandRecipe BANE_OF_ARTHROPODS = create()
            .withIngredient(Items.SPIDER_EYE, 4)
            .withOutput(Enchantments.BANE_OF_ARTHROPODS);
    private static final ItemStandRecipe BANE_OF_ARTHROPODS_3 = create()
            .withIngredient(BANE_OF_ARTHROPODS)
            .withIngredient(Items.SPIDER_EYE, 3)
            .withOutput(Enchantments.BANE_OF_ARTHROPODS, 3);
    private static final ItemStandRecipe BANE_OF_ARTHROPODS_5 = create()
            .withIngredient(BANE_OF_ARTHROPODS_3)
            .withIngredient(Items.SPIDER_EYE, 3)
            .withOutput(Enchantments.BANE_OF_ARTHROPODS, 5);

    private static final ItemStandRecipe BLAST_PROTECTION_2 = create()
            .withIngredient(Items.IRON_INGOT, 4)
            .withOutput(Enchantments.BLAST_PROTECTION, 2);
    private static final ItemStandRecipe BLAST_PROTECTION_4 = create()
            .withIngredient(Items.OBSIDIAN, 3)
            .withIngredient(BLAST_PROTECTION_2)
            .withOutput(Enchantments.BLAST_PROTECTION, 4);

    private static final ItemStandRecipe CHANNELING = create()
            .withIngredient(Items.IRON_BLOCK, 2)
            .withIngredient(Items.REDSTONE_BLOCK, 2)
            .withOutput(Enchantments.CHANNELING);

    private static final ItemStandRecipe CURSE_OF_BINDING = create()
            .withIngredient(Items.CHAIN, 3)
            .withIngredient(Items.FERMENTED_SPIDER_EYE, 1)
            .withOutput(Enchantments.BINDING_CURSE);

    private static final ItemStandRecipe CURSE_OF_VANISHING = create()
            .withIngredient(Items.GLASS, 3)
            .withIngredient(Items.FERMENTED_SPIDER_EYE)
            .withOutput(Enchantments.VANISHING_CURSE);

    //private static final ItemStandRecipe DEPTH_STRIDER;
    //private static final ItemStandRecipe DEPTH_STRIDER_2;
    //private static final ItemStandRecipe DEPTH_STRIDER_3;

    private static final ItemStandRecipe EFFICIENCY = create()
            .withIngredient(Items.SUGAR, 4)
            .withOutput(Enchantments.EFFICIENCY);
    private static final ItemStandRecipe EFFICIENCY_3 = create()
            .withIngredient(Items.GLOWSTONE, 2)
            .withIngredient(Items.REDSTONE_BLOCK)
            .withIngredient(EFFICIENCY)
            .withOutput(Enchantments.EFFICIENCY, 3);
    private static final ItemStandRecipe EFFICIENCY_5 = create()
            .withIngredient(Items.DIAMOND_PICKAXE)
            .withIngredient(Items.CAKE)
            .withIngredient(ItemRegistry.NETHER_STAR_INGOT)
            .withIngredient(EFFICIENCY_3)
            .withOutput(Enchantments.EFFICIENCY, 5);

    private static final ItemStandRecipe FEATHER_FALLING_2 = create()
            .withIngredient(Items.HAY_BLOCK, 2)
            .withIngredient(Items.FEATHER, 2)
            .withOutput(Enchantments.FEATHER_FALLING, 2);
    private static final ItemStandRecipe FEATHER_FALLING_4 = create()
            .withIngredient(Items.HONEY_BLOCK, 1)
            .withIngredient(Items.SLIME_BLOCK, 2)
            .withIngredient(FEATHER_FALLING_2)
            .withOutput(Enchantments.FEATHER_FALLING, 4);

    private static final ItemStandRecipe FIRE_ASPECT = create()
            .withIngredient(Items.FLINT_AND_STEEL)
            .withIngredient(Items.COAL_BLOCK)
            .withOutput(Enchantments.FIRE_ASPECT);
    private static final ItemStandRecipe FIRE_ASPECT_2 = create()
            .withIngredient(Items.BLAZE_ROD, 3)
            .withIngredient(FIRE_ASPECT)
            .withOutput(Enchantments.FIRE_ASPECT, 2);

    //private static final ItemStandRecipe FIRE_PROTECTION_2;
    //private static final ItemStandRecipe FIRE_PROTECTION_4;

    private static final ItemStandRecipe FLAME = create()
            .withIngredient(Items.ARROW, 3)
            .withIngredient(Items.BLAZE_POWDER)
            .withOutput(Enchantments.FLAME);

    private static final ItemStandRecipe FORTUNE = create()
            .withIngredient(Items.COAL)
            .withIngredient(Items.IRON_INGOT)
            .withIngredient(Items.GOLD_INGOT)
            .withIngredient(Items.DIAMOND)
            .withOutput(Enchantments.FORTUNE);
    private static final ItemStandRecipe FORTUNE_2 = create()
            .withIngredient(Items.IRON_BLOCK)
            .withIngredient(Items.GOLD_BLOCK)
            .withIngredient(Items.EMERALD_BLOCK)
            .withIngredient(FORTUNE)
            .withOutput(Enchantments.FORTUNE, 2);
    private static final ItemStandRecipe FORTUNE_3 = create()
            .withIngredient(Items.DIAMOND_BLOCK)
            .withIngredient(Items.EMERALD_BLOCK)
            .withIngredient(FORTUNE_2)
            .withOutput(Enchantments.FORTUNE, 3);

    private static final ItemStandRecipe FROST_WALKER = create()
            .withIngredient(Items.SNOWBALL)
            .withIngredient(Items.ICE)
            .withIngredient(Items.LAPIS_LAZULI, 2)
            .withOutput(Enchantments.FROST_WALKER, 1);
    private static final ItemStandRecipe FROST_WALKER_2 = create()
            .withIngredient(Items.SNOW_BLOCK)
            .withIngredient(Items.PACKED_ICE)
            .withIngredient(Items.BLUE_ICE)
            .withIngredient(FROST_WALKER)
            .withOutput(Enchantments.FROST_WALKER, 2);

    //private static final ItemStandRecipe IMPALING;
    //private static final ItemStandRecipe IMPALING_3;
    //private static final ItemStandRecipe IMPALING_5;

    private static final ItemStandRecipe INFINITY = create()
            .withIngredient(ItemRegistry.NETHER_STAR_INGOT)
            .withIngredient(Items.ARROW)
            .withOutput(Enchantments.INFINITY);
/*
    private static final ItemStandRecipe KNOCKBACK;
    private static final ItemStandRecipe KNOCKBACK_2;

    private static final ItemStandRecipe LOOTING;
    private static final ItemStandRecipe LOOTING_2;
    private static final ItemStandRecipe LOOTING_3;

    private static final ItemStandRecipe LOYALTY;
    private static final ItemStandRecipe LOYALTY_2;
    private static final ItemStandRecipe LOYALTY_3;

    private static final ItemStandRecipe LUCK_OF_THE_SEA;
    private static final ItemStandRecipe LUCK_OF_THE_SEA_2;
    private static final ItemStandRecipe LUCK_OF_THE_SEA_3;

    private static final ItemStandRecipe LURE;
    private static final ItemStandRecipe LURE_2;
    private static final ItemStandRecipe LURE_3;

    private static final ItemStandRecipe MENDING;
*/
    private static final ItemStandRecipe MULTISHOT = create()
            .withIngredient(Items.CROSSBOW, 4)
            .withOutput(Enchantments.MULTISHOT);
/*
    private static final ItemStandRecipe PIERCING_2;
    private static final ItemStandRecipe PIERCING_4;

    private static final ItemStandRecipe POWER;
    private static final ItemStandRecipe POWER_3;
    private static final ItemStandRecipe POWER_5;

    private static final ItemStandRecipe PROJECTILE_PROTECTION_2;
    private static final ItemStandRecipe PROJECTILE_PROTECTION_4;

    private static final ItemStandRecipe PROTECTION_2;
    private static final ItemStandRecipe PROTECTION_4;

    private static final ItemStandRecipe PUNCH;
    private static final ItemStandRecipe PUNCH_2;

    private static final ItemStandRecipe QUICK_CHARGE;
    private static final ItemStandRecipe QUICK_CHARGE_2;
    private static final ItemStandRecipe QUICK_CHARGE_3;

    private static final ItemStandRecipe RESPIRATION;
    private static final ItemStandRecipe RESPIRATION_2;
    private static final ItemStandRecipe RESPIRATION_3;

    private static final ItemStandRecipe RIPTIDE;
    private static final ItemStandRecipe RIPTIDE_2;
    private static final ItemStandRecipe RIPTIDE_3;
*/
    private static final ItemStandRecipe SHARPNESS = create()
            .withIngredient(Items.QUARTZ, 4)
            .withOutput(Enchantments.SHARPNESS, 1);
    private static final ItemStandRecipe SHARPNESS_3 = create()
            .withIngredient(Items.DIAMOND, 2)
            .withIngredient(Items.QUARTZ_BLOCK)
            .withIngredient(SHARPNESS)
            .withOutput(Enchantments.SHARPNESS, 3);
    private static final ItemStandRecipe SHARPNESS_5 = create()
            .withIngredient(Items.DIAMOND_BLOCK, 3)
            .withIngredient(SHARPNESS_3)
            .withOutput(Enchantments.SHARPNESS, 5);
/*
    private static final ItemStandRecipe SILK_TOUCH;

    private static final ItemStandRecipe SMITE;
    private static final ItemStandRecipe SMITE_3;
    private static final ItemStandRecipe SMITE_5;

    private static final ItemStandRecipe SOUL_SPEED;
    private static final ItemStandRecipe SOUL_SPEED_2;
    private static final ItemStandRecipe SOUL_SPEED_3;

    private static final ItemStandRecipe SWEEPING_EDGE;
    private static final ItemStandRecipe SWEEPING_EDGE_2;
    private static final ItemStandRecipe SWEEPING_EDGE_3;

    private static final ItemStandRecipe THORNS;
    private static final ItemStandRecipe THORNS_2;
    private static final ItemStandRecipe THORNS_3;

    private static final ItemStandRecipe UNBREAKING;
    private static final ItemStandRecipe UNBREAKING_2;
    private static final ItemStandRecipe UNBREAKING_3;
*/
    private static List<ItemStandRecipe> RECIPES = new ArrayList<>();
    static {
        RECIPES.add(AQUA_AFFINITY);
        RECIPES.add(BANE_OF_ARTHROPODS);
        RECIPES.add(BANE_OF_ARTHROPODS_3);
        RECIPES.add(BANE_OF_ARTHROPODS_5);
        RECIPES.add(BLAST_PROTECTION_2);
        RECIPES.add(BLAST_PROTECTION_4);
        RECIPES.add(CHANNELING);
        RECIPES.add(CURSE_OF_BINDING);
        RECIPES.add(CURSE_OF_VANISHING);
        //RECIPES.add(DEPTH_STRIDER);
        //RECIPES.add(DEPTH_STRIDER_2);
        //RECIPES.add(DEPTH_STRIDER_3);
        RECIPES.add(EFFICIENCY);
        RECIPES.add(EFFICIENCY_3);
        RECIPES.add(EFFICIENCY_5);
        RECIPES.add(FEATHER_FALLING_2);
        RECIPES.add(FEATHER_FALLING_4);
        RECIPES.add(FIRE_ASPECT);
        RECIPES.add(FIRE_ASPECT_2);
        //RECIPES.add(FIRE_PROTECTION_2);
        //RECIPES.add(FIRE_PROTECTION_4);
        RECIPES.add(FLAME);
        RECIPES.add(FORTUNE);
        RECIPES.add(FORTUNE_2);
        RECIPES.add(FORTUNE_3);
        RECIPES.add(FROST_WALKER);
        RECIPES.add(FROST_WALKER_2);
        /*
        RECIPES.add(IMPALING);
        RECIPES.add(IMPALING_3);
        RECIPES.add(IMPALING_5);

         */
        RECIPES.add(INFINITY);
        /*
        RECIPES.add(KNOCKBACK);
        RECIPES.add(KNOCKBACK_2);
        RECIPES.add(LOOTING);
        RECIPES.add(LOOTING_2);
        RECIPES.add(LOOTING_3);
        RECIPES.add(LOYALTY);
        RECIPES.add(LOYALTY_2);
        RECIPES.add(LOYALTY_3);
        RECIPES.add(LUCK_OF_THE_SEA);
        RECIPES.add(LUCK_OF_THE_SEA_2);
        RECIPES.add(LUCK_OF_THE_SEA_3);
        RECIPES.add(LURE);
        RECIPES.add(LURE_2);
        RECIPES.add(LURE_3);
        RECIPES.add(MENDING);
        */
        RECIPES.add(MULTISHOT);
        /*
        RECIPES.add(PIERCING_2);
        RECIPES.add(PIERCING_4);
        RECIPES.add(POWER);
        RECIPES.add(POWER_3);
        RECIPES.add(POWER_5);
        RECIPES.add(PROJECTILE_PROTECTION_2);
        RECIPES.add(PROJECTILE_PROTECTION_4);
        RECIPES.add(PROTECTION_2);
        RECIPES.add(PROTECTION_4);
        RECIPES.add(PUNCH);
        RECIPES.add(PUNCH_2);
        RECIPES.add(QUICK_CHARGE);
        RECIPES.add(QUICK_CHARGE_2);
        RECIPES.add(QUICK_CHARGE_3);
        RECIPES.add(RESPIRATION);
        RECIPES.add(RESPIRATION_2);
        RECIPES.add(RESPIRATION_3);
        RECIPES.add(RIPTIDE);
        RECIPES.add(RIPTIDE_2);
        RECIPES.add(RIPTIDE_3);
        */
        RECIPES.add(SHARPNESS);
        RECIPES.add(SHARPNESS_3);
        RECIPES.add(SHARPNESS_5);
        /*
        RECIPES.add(SILK_TOUCH);
        RECIPES.add(SMITE);
        RECIPES.add(SMITE_3);
        RECIPES.add(SMITE_5);
        RECIPES.add(SOUL_SPEED);
        RECIPES.add(SOUL_SPEED_2);
        RECIPES.add(SOUL_SPEED_3);
        RECIPES.add(THORNS);
        RECIPES.add(THORNS_2);
        RECIPES.add(THORNS_3);
        RECIPES.add(UNBREAKING);
        RECIPES.add(UNBREAKING_2);
        RECIPES.add(UNBREAKING_3);
        */
    }

    private static ItemStandRecipe create() {
        return new ItemStandRecipe();
    }
}
