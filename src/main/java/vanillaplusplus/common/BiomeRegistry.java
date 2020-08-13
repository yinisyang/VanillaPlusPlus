package vanillaplusplus.common;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import vanillaplusplus.features.BoulderFeature;

import static vanillaplusplus.VanillaPlusPlusInitializer.MOD_ID;

public class BiomeRegistry {

    public static SpringFeature IRON_SPRING = new SpringFeature(SpringFeatureConfig.CODEC);
    public static BoulderFeature BOULDER = new BoulderFeature(BlockPileFeatureConfig.CODEC);

    public static void register() {
        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "spring_iron"), IRON_SPRING);
        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "boulder"), BOULDER);
    }
}
