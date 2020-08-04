package vanillaplusplus.blocks.meltable;

import vanillaplusplus.entities.GoldBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class CustomGoldBlock extends MeltableBlock {
    public CustomGoldBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new GoldBlockEntity();
    }
}
