package vanillaplusplus.blocks.meltable;

import vanillaplusplus.entities.IronBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class CustomIronBlock extends MeltableBlock {
    public CustomIronBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new IronBlockEntity();
    }
}
