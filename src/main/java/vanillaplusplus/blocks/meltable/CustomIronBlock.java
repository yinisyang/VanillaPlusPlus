package vanillaplusplus.blocks.meltable;

import net.minecraft.block.Block;
import vanillaplusplus.common.FluidRegistry;

public class CustomIronBlock extends MeltableBlock {
    public CustomIronBlock(Settings settings) {
        super(settings);
    }

    @Override
    public Block getMoltenBlock() {
        return FluidRegistry.MOLTEN_IRON_BLOCK;
    }
}
