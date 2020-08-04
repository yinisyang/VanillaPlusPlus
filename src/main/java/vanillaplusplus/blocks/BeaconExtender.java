package vanillaplusplus.blocks;

import vanillaplusplus.entities.BeaconExtenderEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class BeaconExtender extends Block implements BlockEntityProvider {

    public BeaconExtender(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new BeaconExtenderEntity();
    }
}
