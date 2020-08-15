package vanillaplusplus.common;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import vanillaplusplus.features.BoulderFeature;

import static vanillaplusplus.VanillaPlusPlusInitializer.MOD_ID;

public class BiomeRegistry {

    public static SpringFeature IRON_SPRING = new SpringFeature(SpringFeatureConfig.CODEC);
    public static BoulderFeature BOULDER = new BoulderFeature(BlockPileFeatureConfig.CODEC);
    public static OreFeature ORE_SALTPETER = new OreFeature(OreFeatureConfig.CODEC);
    public static OreFeature ORE_SULFUR = new OreFeature(OreFeatureConfig.CODEC);

    public static SurfaceBuilder CHARRED_GROUND = new DefaultSurfaceBuilder(TernarySurfaceConfig.CODEC);

    public static RegistryKey<Biome> TEST_BIOME = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "test"));

    public static void register() {
        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "spring_iron"), IRON_SPRING);
        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "boulder"), BOULDER);
        Registry.register(Registry.SURFACE_BUILDER, new Identifier(MOD_ID, "charred_ground"), CHARRED_GROUND);
        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "ore_saltpeter"), ORE_SALTPETER);
        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "ore_sulfur"), ORE_SULFUR);
    }
}
