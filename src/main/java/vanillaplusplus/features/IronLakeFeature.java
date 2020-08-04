package vanillaplusplus.features;

import vanillaplusplus.common.FluidRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class IronLakeFeature extends Feature<DefaultFeatureConfig> {
    public IronLakeFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockPos pos, DefaultFeatureConfig config) {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
                world.setBlockState(topPos, FluidRegistry.MOLTEN_IRON_BLOCK.getDefaultState(), 3);
            }
        }

        return true;
    }
}
