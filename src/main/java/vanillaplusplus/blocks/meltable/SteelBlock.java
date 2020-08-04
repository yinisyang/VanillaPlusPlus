package vanillaplusplus.blocks.meltable;

import vanillaplusplus.entities.SteelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class SteelBlock extends MeltableBlock {
    public SteelBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new SteelBlockEntity();
    }
}
