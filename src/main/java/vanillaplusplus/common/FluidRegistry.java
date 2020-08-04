package vanillaplusplus.common;

import vanillaplusplus.fluids.GoldFluid;
import vanillaplusplus.fluids.IronFluid;
import vanillaplusplus.fluids.SteelFluid;
import vanillaplusplus.VanillaPlusPlusInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;

import java.util.function.Function;

import static vanillaplusplus.VanillaPlusPlusInitializer.MOD_ID;

public class FluidRegistry {

    // Tags
    public static Tag<Fluid> MOLTEN_METAL_TAG = TagRegistry.fluid(new Identifier(VanillaPlusPlusInitializer.MOD_ID, "molten_metal"));

    // Fluids
    public static FlowableFluid STILL_IRON = new IronFluid.Still();
    public static FlowableFluid FLOWING_IRON = new IronFluid.Flowing();
    public static FlowableFluid STILL_STEEL = new SteelFluid.Still();
    public static FlowableFluid FLOWING_STEEL = new SteelFluid.Flowing();
    public static FlowableFluid STILL_GOLD = new GoldFluid.Still();
    public static FlowableFluid FLOWING_GOLD = new GoldFluid.Flowing();

    // Fluid Blocks
    public static final Block MOLTEN_IRON_BLOCK = new FluidBlock(STILL_IRON, FabricBlockSettings.copy(Blocks.LAVA).noCollision().lightLevel((state) -> 15).ticksRandomly()){};
    public static final Block MOLTEN_STEEL_BLOCK = new FluidBlock(STILL_STEEL, FabricBlockSettings.copy(Blocks.LAVA).noCollision().lightLevel((state) -> 15).ticksRandomly()){};
    public static final Block MOLTEN_GOLD_BLOCK = new FluidBlock(STILL_GOLD, FabricBlockSettings.copy(Blocks.LAVA).noCollision().lightLevel((state) -> 15).ticksRandomly()){};

    public static void register() {
        createFluid("iron", STILL_IRON, FLOWING_IRON, MOLTEN_IRON_BLOCK, new Identifier("minecraft", "lava"), 0xc22e09);
        createFluid("steel", STILL_STEEL, FLOWING_STEEL, MOLTEN_STEEL_BLOCK, new Identifier("minecraft", "lava"), 0x8f0000);
        createFluid("gold", STILL_GOLD, FLOWING_GOLD, MOLTEN_GOLD_BLOCK, new Identifier("vanilla_plus_plus", "gold"), 0xf8ef66);
    }

    public static void createFluid(String name, FlowableFluid still, FlowableFluid flowing, Block fluidBlock, Identifier baseFluidTexture, int color) {
        // Register fluids
        Registry.register(Registry.FLUID, new Identifier(MOD_ID, "molten_" + name), still);
        Registry.register(Registry.FLUID, new Identifier(MOD_ID, "flowing_molten_" + name), flowing);
        // Register fluid block
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "molten_" + name + "_block"), fluidBlock);
        // Setup fluid rendering
        setupFluidRendering(still, flowing, baseFluidTexture, color);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), still, flowing);
    }

    private static void setupFluidRendering(final Fluid still, final Fluid flowing, final Identifier textureFluidId, final int color) {
        final Identifier stillSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_still");
        final Identifier flowingSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_flow");

        // If they're not already present, add the sprites to the block atlas

        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEX).register((atlasTexture, registry) ->
        {
            registry.register(stillSpriteId);
            registry.register(flowingSpriteId);
        });

        final Identifier fluidId = Registry.FLUID.getId(still);
        final Identifier listenerId = new Identifier(fluidId.getNamespace(), fluidId.getPath() + "_reload_listener");

        final Sprite[] fluidSprites = { null, null };

        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener()
        {
            @Override
            public Identifier getFabricId()
            {
                return listenerId;
            }

            /**
             * Get the sprites from the block atlas when resources are reloaded
             */
            @Override
            public void apply(ResourceManager resourceManager)
            {
                final Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
                fluidSprites[0] = atlas.apply(stillSpriteId);
                fluidSprites[1] = atlas.apply(flowingSpriteId);
            }
        });

        // The FluidRenderer gets the sprites and color from a FluidRenderHandler during rendering
        final FluidRenderHandler renderHandler = new FluidRenderHandler()
        {
            @Override
            public Sprite[] getFluidSprites(BlockRenderView view, BlockPos pos, FluidState state)
            {
                return fluidSprites;
            }

            @Override
            public int getFluidColor(BlockRenderView view, BlockPos pos, FluidState state)
            {
                return color;
            }
        };

        FluidRenderHandlerRegistry.INSTANCE.register(still, renderHandler);
        FluidRenderHandlerRegistry.INSTANCE.register(flowing, renderHandler);
    }
}
