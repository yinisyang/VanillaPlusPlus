package vanillaplusplus.blocks.meltable;

import net.minecraft.block.Block;
import vanillaplusplus.common.FluidRegistry;

public class SteelBlock extends MeltableBlock {
    public SteelBlock(Settings settings) {
        super(settings);
    }

    @Override
    public Block getMoltenBlock() {
        return FluidRegistry.MOLTEN_STEEL_BLOCK;
    }
}
