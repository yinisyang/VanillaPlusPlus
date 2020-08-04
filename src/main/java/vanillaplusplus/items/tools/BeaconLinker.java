package vanillaplusplus.items.tools;

import vanillaplusplus.entities.BeaconExtenderEntity;
import vanillaplusplus.common.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BeaconLinker extends Item {

    private BeaconBlockEntity selectedBeacon;

    public BeaconLinker(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        BlockState state = world.getBlockState(pos);
        if (state.isOf(Blocks.BEACON)) {
            this.selectedBeacon = (BeaconBlockEntity)world.getBlockEntity(pos);
        } else if (state.isOf(BlockRegistry.BEACON_EXTENDER)) {
            if (selectedBeacon != null) {
                ((BeaconExtenderEntity)world.getBlockEntity(pos)).linkBeacon(selectedBeacon);
            }
        }

        return ActionResult.SUCCESS;
    }
}
