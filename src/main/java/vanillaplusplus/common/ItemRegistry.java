package vanillaplusplus.common;

import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import vanillaplusplus.items.*;
import vanillaplusplus.items.tools.BeaconLinker;
import vanillaplusplus.items.tools.CustomAxe;
import vanillaplusplus.items.tools.CustomHoe;
import vanillaplusplus.items.tools.CustomPickaxe;

import static vanillaplusplus.VanillaPlusPlusInitializer.MOD_ID;
import static vanillaplusplus.VanillaPlusPlusInitializer.VPP_ITEM_GROUP;

public class ItemRegistry {

    public static final Item SULFUR_ITEM = new Item(new Item.Settings().group(VPP_ITEM_GROUP));
    public static final Item SALTPETER_ITEM = new Item(new Item.Settings().group(VPP_ITEM_GROUP));
    public static final Item WITHER_BONE_MEAL = new WitherBoneMeal(new Item.Settings().group(VPP_ITEM_GROUP));
    public static final Item WITHER_BONE = new Item(new Item.Settings().group(VPP_ITEM_GROUP));
    public static final Item NETHER_STAR_SHARD = new NetherStarShard(new Item.Settings().group(VPP_ITEM_GROUP));
    public static final Item STEEL_INGOT = new Item(new Item.Settings().group(VPP_ITEM_GROUP));
    public static final Item MOLTEN_IRON_BUCKET = new BucketItem(FluidRegistry.STILL_IRON, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(VPP_ITEM_GROUP));
    public static final Item MOLTEN_STEEL_BUCKET = new BucketItem(FluidRegistry.STILL_STEEL, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(VPP_ITEM_GROUP));
    public static final Item MOLTEN_GOLD_BUCKET = new BucketItem(FluidRegistry.STILL_GOLD, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(VPP_ITEM_GROUP));
    public static final Item DYNAMITE = new DynamiteItem(new Item.Settings().group(VPP_ITEM_GROUP));
    public static final Item BEACON_LINKER = new BeaconLinker(new Item.Settings().group(VPP_ITEM_GROUP));
    public static final Item NETHER_STAR_INGOT = new NetherStarIngotItem(new Item.Settings().group(VPP_ITEM_GROUP));

    // Tools
    public static final ToolItem STEEL_SHOVEL = new ShovelItem(CustomToolMaterial.STEEL, 1.5f, -3.0f, new Item.Settings().group(VPP_ITEM_GROUP));
    public static final ToolItem STEEL_SWORD = new SwordItem(CustomToolMaterial.STEEL, 3, -2.4f, new Item.Settings().group(VPP_ITEM_GROUP));
    public static final ToolItem STEEL_AXE = new CustomAxe(CustomToolMaterial.STEEL, 5.5f, -3.1f, new Item.Settings().group(VPP_ITEM_GROUP));
    public static final ToolItem STEEL_PICKAXE = new CustomPickaxe(CustomToolMaterial.STEEL, 1, -2.8f, new Item.Settings().group(VPP_ITEM_GROUP));
    public static final ToolItem STEEL_HOE = new CustomHoe(CustomToolMaterial.STEEL, -2, -1.0f, new Item.Settings().group(VPP_ITEM_GROUP));
    public static final ToolItem NETHER_STAR_SHOVEL = new ShovelItem(CustomToolMaterial.NETHER_STAR_INGOT, 3.0f, -3.0f, new Item.Settings().group(VPP_ITEM_GROUP));
    public static final ToolItem NETHER_STAR_SWORD = new SwordItem(CustomToolMaterial.NETHER_STAR_INGOT, 8, 1.5f, new Item.Settings().group(VPP_ITEM_GROUP));
    public static final ToolItem NETHER_STAR_AXE = new CustomAxe(CustomToolMaterial.NETHER_STAR_INGOT, 5.5f, -1.0f, new Item.Settings().group(VPP_ITEM_GROUP));
    public static final ToolItem NETHER_STAR_PICKAXE = new CustomAxe(CustomToolMaterial.NETHER_STAR_INGOT, 2.5f, -1.0f, new Item.Settings().group(VPP_ITEM_GROUP));
    public static final ToolItem NETHER_STAR_HOE = new CustomHoe(CustomToolMaterial.NETHER_STAR_INGOT, 2, -1.0f, new Item.Settings().group(VPP_ITEM_GROUP));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "sulfur_item"), SULFUR_ITEM);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "saltpeter_item"), SALTPETER_ITEM);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wither_bone_meal"), WITHER_BONE_MEAL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wither_bone"), WITHER_BONE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nether_star_shard"), NETHER_STAR_SHARD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "steel_ingot"), STEEL_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "molten_iron_bucket"), MOLTEN_IRON_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "molten_steel_bucket"), MOLTEN_STEEL_BUCKET);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "molten_gold_bucket"), MOLTEN_GOLD_BUCKET);
        Registry.register(Registry.ITEM, 367, "minecraft:coal_block", new CustomCoalBlockItem());
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dynamite"), DYNAMITE);
        //Registry.register(Registry.ITEM, new Identifier(MOD_ID, "beacon_linker"), BEACON_LINKER);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nether_star_ingot"), NETHER_STAR_INGOT);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "steel_shovel"), STEEL_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "steel_sword"), STEEL_SWORD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "steel_axe"), STEEL_AXE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "steel_pickaxe"), STEEL_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "steel_hoe"), STEEL_HOE);

        //Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nether_star_shovel"), NETHER_STAR_SHOVEL);
        //Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nether_star_sword"), NETHER_STAR_SWORD);
        //Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nether_star_axe"), NETHER_STAR_AXE);
        //Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nether_star_pickaxe"), NETHER_STAR_PICKAXE);
        //Registry.register(Registry.ITEM, new Identifier(MOD_ID, "nether_star_hoe"), NETHER_STAR_HOE);
    }
}
