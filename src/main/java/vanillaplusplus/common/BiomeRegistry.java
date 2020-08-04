package vanillaplusplus.common;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import vanillaplusplus.features.BoulderFeature;

import static vanillaplusplus.VanillaPlusPlusInitializer.MOD_ID;

public class BiomeRegistry {

    public static LakeFeature IRON_LAKE = new LakeFeature(SingleStateFeatureConfig.CODEC);
    public static BoulderFeature BOULDER = new BoulderFeature(BlockPileFeatureConfig.CODEC);

    public static void register() {
        // Add custom features to biomes
        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "iron_lake"), IRON_LAKE);

        Biomes.NETHER_WASTES.addFeature(
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                IRON_LAKE.configure(new SingleStateFeatureConfig(FluidRegistry.MOLTEN_IRON_BLOCK.getDefaultState()))
                        .createDecoratedFeature(Decorator.LAVA_LAKE.configure(new ChanceDecoratorConfig(10)))
        );

        // Add custom ore to biomes
        Registry.BIOME.forEach(BiomeRegistry::handleBiome);

        // Listen for biomes from other mods
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));
    }

    private static void handleBiome(Biome biome) {
        if(biome == Biomes.NETHER_WASTES) {
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NETHERRACK,
                                    BlockRegistry.SULFUR_ORE_BLOCK.getDefaultState(),
                                    9
                            )
                    ).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    20,
                                    0,
                                    0,
                                    64
                            ))
                    )
            );
        }

        if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    BlockRegistry.SALTPETER_ORE_BLOCK.getDefaultState(),
                                    7
                            )
                    ).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    20,
                                    0,
                                    0,
                                    64
                            ))
                    )
            );

            if (biome.getCategory() == Biome.Category.PLAINS || biome.getCategory() == Biome.Category.SAVANNA) {
                biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
                        BOULDER.configure(new BlockPileFeatureConfig((new WeightedBlockStateProvider())
                                .addState(Blocks.ANDESITE.getDefaultState(), 6)
                                .addState(Blocks.DIORITE.getDefaultState(), 4)
                                .addState(Blocks.COBBLESTONE.getDefaultState(), 6)
                                .addState(Blocks.IRON_ORE.getDefaultState(), 1)
                                .addState(Blocks.COAL_ORE.getDefaultState(), 1))
                        ).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(10)))
                );
            }
        }
    }
}
