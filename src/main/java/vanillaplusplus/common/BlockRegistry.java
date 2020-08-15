package vanillaplusplus.common;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.ScaffoldingItem;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.Direction;
import vanillaplusplus.blocks.*;
import vanillaplusplus.blocks.chests.CustomChest;
import vanillaplusplus.blocks.meltable.SteelBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import vanillaplusplus.items.NetherStarBlockItem;

import static vanillaplusplus.VanillaPlusPlusInitializer.MOD_ID;
import static vanillaplusplus.VanillaPlusPlusInitializer.VPP_ITEM_GROUP;

public class BlockRegistry {

    // Tags
    public static Tag<Block> MELTABLE_BLOCK_TAG = TagRegistry.block(new Identifier(MOD_ID, "meltable"));
    public static Tag<Block> STICKY_BLOCK_TAG = TagRegistry.block(new Identifier(MOD_ID, "sticky"));

    public static final Block SULFUR_ORE_BLOCK = new SulfurOreBlock(FabricBlockSettings.of(Material.STONE).hardness(3.0f));
    public static final Block SALTPETER_ORE_BLOCK = new SaltpeterOreBlock(FabricBlockSettings.of(Material.STONE).hardness(4.0f));
    public static final Block ADVANCED_DISPENSER_BLOCK = new AdvancedDispenser(FabricBlockSettings.of(Material.STONE).hardness(4.0f).requiresTool());
    public static final Block STEEL_BLOCK = new SteelBlock(FabricBlockSettings.of(Material.METAL).hardness(7.0f).requiresTool());
    public static final Block STEEL_SCAFFOLD = new SteelScaffold(FabricBlockSettings.of(Material.SUPPORTED).nonOpaque().noCollision().dynamicBounds());
    public static final Block BEACON_EXTENDER = new BeaconExtender(FabricBlockSettings.of(Material.METAL).hardness(5.0f).requiresTool());
    public static final Block NETHER_STAR_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f).requiresTool());
    public static final Block STAND = new StandBlock(FabricBlockSettings.copyOf(Blocks.ANVIL));
    public static final Block BURNED_LOG = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (blockState) -> blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.SPRUCE).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block BAMBOO_PLANKS = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS));

    public static final Block BLACK_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.BLACK_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block GRAY_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.GRAY_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block LIGHT_GRAY_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.LIGHT_GRAY_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block WHITE_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.WHITE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE).requiresTool());

    public static final Block RED_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.RED_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block BLUE_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.BLUE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block GREEN_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.GREEN_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block BROWN_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.BROWN_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block CYAN_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.CYAN_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block LIGHT_BLUE_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.LIGHT_BLUE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block LIME_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.LIME_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block PINK_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.PINK_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block MAGENTA_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.MAGENTA_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block PURPLE_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.PURPLE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block YELLOW_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.YELLOW_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block ORANGE_CONCRETE_STAIRS = new CustomStairsBlock(Blocks.ORANGE_CONCRETE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));

    public static final Block BLACK_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block GRAY_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block LIGHT_GRAY_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block WHITE_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));

    public static final Block RED_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block BLUE_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block GREEN_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block BROWN_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block CYAN_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block LIGHT_BLUE_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block LIME_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block PINK_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block MAGENTA_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block PURPLE_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block YELLOW_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));
    public static final Block ORANGE_CONCRETE_SLABS = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CONCRETE));

    // Custom chests
    public static final Block SPRUCE_CHEST = new CustomChest(FabricBlockSettings.of(Material.WOOD).strength(2.5f).sounds(BlockSoundGroup.WOOD));

    public static void register() {
        createBlock("sulfur_ore", SULFUR_ORE_BLOCK);
        createBlock("saltpeter_ore", SALTPETER_ORE_BLOCK);
        createBlock("advanced_dispenser", ADVANCED_DISPENSER_BLOCK);
        createBlock("steel_block", STEEL_BLOCK);
        createBlock("steel_scaffold", STEEL_SCAFFOLD, new ScaffoldingItem(STEEL_SCAFFOLD, new Item.Settings().group(VPP_ITEM_GROUP)));
        createBlock("black_concrete_stairs", BLACK_CONCRETE_STAIRS);
        createBlock("red_concrete_stairs", RED_CONCRETE_STAIRS);
        createBlock("blue_concrete_stairs", BLUE_CONCRETE_STAIRS);
        createBlock("green_concrete_stairs", GREEN_CONCRETE_STAIRS);
        createBlock("brown_concrete_stairs", BROWN_CONCRETE_STAIRS);
        createBlock("cyan_concrete_stairs", CYAN_CONCRETE_STAIRS);
        createBlock("gray_concrete_stairs", GRAY_CONCRETE_STAIRS);
        createBlock("light_blue_concrete_stairs", LIGHT_BLUE_CONCRETE_STAIRS);
        createBlock("light_gray_concrete_stairs", LIGHT_GRAY_CONCRETE_STAIRS);
        createBlock("lime_concrete_stairs", LIME_CONCRETE_STAIRS);
        createBlock("white_concrete_stairs", WHITE_CONCRETE_STAIRS);
        createBlock("pink_concrete_stairs", PINK_CONCRETE_STAIRS);
        createBlock("magenta_concrete_stairs", MAGENTA_CONCRETE_STAIRS);
        createBlock("purple_concrete_stairs", PURPLE_CONCRETE_STAIRS);
        createBlock("yellow_concrete_stairs", YELLOW_CONCRETE_STAIRS);
        createBlock("orange_concrete_stairs", ORANGE_CONCRETE_STAIRS);
        createBlock("black_concrete_slab", BLACK_CONCRETE_SLABS);
        createBlock("red_concrete_slab", RED_CONCRETE_SLABS);
        createBlock("blue_concrete_slab", BLUE_CONCRETE_SLABS);
        createBlock("green_concrete_slab", GREEN_CONCRETE_SLABS);
        createBlock("brown_concrete_slab", BROWN_CONCRETE_SLABS);
        createBlock("cyan_concrete_slab", CYAN_CONCRETE_SLABS);
        createBlock("gray_concrete_slab", GRAY_CONCRETE_SLABS);
        createBlock("light_blue_concrete_slab", LIGHT_BLUE_CONCRETE_SLABS);
        createBlock("light_gray_concrete_slab", LIGHT_GRAY_CONCRETE_SLABS);
        createBlock("lime_concrete_slab", LIME_CONCRETE_SLABS);
        createBlock("white_concrete_slab", WHITE_CONCRETE_SLABS);
        createBlock("pink_concrete_slab", PINK_CONCRETE_SLABS);
        createBlock("magenta_concrete_slab", MAGENTA_CONCRETE_SLABS);
        createBlock("purple_concrete_slab", PURPLE_CONCRETE_SLABS);
        createBlock("yellow_concrete_slab", YELLOW_CONCRETE_SLABS);
        createBlock("orange_concrete_slab", ORANGE_CONCRETE_SLABS);
        //createBlock("spruce_chest", SPRUCE_CHEST);
        //createBlock("beacon_extender", BEACON_EXTENDER);
        createBlock("nether_star_block", NETHER_STAR_BLOCK, new NetherStarBlockItem(NETHER_STAR_BLOCK, new Item.Settings().group(VPP_ITEM_GROUP)));
        createBlock("stand", STAND);
        createBlock("burned_log", BURNED_LOG);
        createBlock("bamboo_planks", BAMBOO_PLANKS);
    }

    private static void createBlock(String name, Block block) {
        createBlock(name, block, null);
    }

    private static void createBlock(String name, Block block, BlockItem blockItem) {
        if (blockItem == null) {
            blockItem = new BlockItem(block, new Item.Settings().group(VPP_ITEM_GROUP));
        }
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), block);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, name), blockItem);
    }
}
