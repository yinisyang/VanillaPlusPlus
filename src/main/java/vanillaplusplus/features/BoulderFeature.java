package vanillaplusplus.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockPileFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class BoulderFeature extends Feature<BlockPileFeatureConfig> {

    public BoulderFeature(Codec<BlockPileFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos, BlockPileFeatureConfig config) {

        int width = random.nextInt(4) + 5;
        int length = random.nextInt(4) + 5;
        int height = random.nextInt(2) + 3;

        BlockPos startPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);

        // startPos could be on top of leaves so we need to bring it to the ground
        while (world.getBlockState(startPos.down()).isIn(BlockTags.LEAVES) || world.getBlockState(startPos.down()).isAir()) {
            startPos = startPos.down();
        }

        // Dont want to generate on top of a tree
        if (world.getBlockState(startPos).isIn(BlockTags.LOGS)) {
            return false;
        }

        BlockPos center = startPos.add(new Vec3i(width/2, 0, length/2));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < height; k++) {
                    Vec3i offset = new Vec3i(i, k, j);

                    double maxDistance = startPos.getSquaredDistance(new Vec3i(center.getX(), center.getY(), center.getZ()));
                    double curDistance = startPos.add(offset).getSquaredDistance(new Vec3i(center.getX(), center.getY(), center.getZ()));

                    if (curDistance < 0.5 * maxDistance && !world.getBlockState(startPos.add(offset).down()).isAir()) {
                        world.setBlockState(startPos.add(offset), config.stateProvider.getBlockState(random, pos), 3);

                        // If this is the first layer of the boulder make sure all parts
                        // reach the ground
                        if (k == 0) {
                            BlockPos cur = startPos.add(offset).down();
                            while (world.getBlockState(cur).isAir() || world.getBlockState(cur).isIn(BlockTags.LEAVES)) {
                                world.setBlockState(cur, config.stateProvider.getBlockState(random, cur), 3);
                                cur = cur.down();
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
