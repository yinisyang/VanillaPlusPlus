package vanillaplusplus.entities;

import vanillaplusplus.common.EntityRegistry;
import vanillaplusplus.common.FluidRegistry;
import net.minecraft.block.Block;

public class SteelBlockEntity extends MeltableBlockEntity {

    public SteelBlockEntity() {
        super(EntityRegistry.STEEL_BLOCK_ENTITY);
    }

    @Override
    protected Block getMoltenBlock() {
        return FluidRegistry.MOLTEN_STEEL_BLOCK;
    }

    @Override
    protected double getHeatUpChance() {
        return 0.05;
    }
}
