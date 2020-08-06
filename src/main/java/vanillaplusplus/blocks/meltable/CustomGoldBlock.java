package vanillaplusplus.blocks.meltable;

import net.minecraft.block.Block;
import vanillaplusplus.common.FluidRegistry;

public class CustomGoldBlock extends MeltableBlock {
    public CustomGoldBlock(Settings settings) {
        super(settings);
    }

    @Override
    public Block getMoltenBlock() {
        return FluidRegistry.MOLTEN_GOLD_BLOCK;
    }
}
