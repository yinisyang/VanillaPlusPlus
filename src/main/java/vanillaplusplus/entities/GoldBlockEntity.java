package vanillaplusplus.entities;

import vanillaplusplus.common.EntityRegistry;
import vanillaplusplus.common.FluidRegistry;
import net.minecraft.block.Block;

public class GoldBlockEntity extends MeltableBlockEntity {
    public GoldBlockEntity() {
        super(EntityRegistry.GOLD_BLOCK_ENTITY);
    }

    @Override
    protected Block getMoltenBlock() {
        return FluidRegistry.MOLTEN_GOLD_BLOCK;
    }

    @Override
    protected double getHeatUpChance() {
        return 0.4;
    }
}
