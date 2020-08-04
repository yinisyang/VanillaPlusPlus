package vanillaplusplus.entities;

import vanillaplusplus.common.EntityRegistry;
import vanillaplusplus.common.FluidRegistry;
import net.minecraft.block.Block;

public class IronBlockEntity extends MeltableBlockEntity {
    public IronBlockEntity() {
        super(EntityRegistry.IRON_BLOCK_ENTITY);
    }

    @Override
    protected Block getMoltenBlock() {
        return FluidRegistry.MOLTEN_IRON_BLOCK;
    }

    @Override
    protected double getHeatUpChance() {
        return 0.1;
    }
}
