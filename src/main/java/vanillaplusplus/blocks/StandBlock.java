package vanillaplusplus.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import vanillaplusplus.entities.StandBlockEntity;

public class StandBlock extends Block implements BlockEntityProvider {
    public StandBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new StandBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {

        Inventory inventory = (Inventory) world.getBlockEntity(blockPos);
        // If inventory is empty
        if (inventory.getStack(0).isEmpty()) {
            inventory.setStack(0, player.getStackInHand(hand).split(1));
        } else { // If inventory is full
            player.inventory.offerOrDrop(world, inventory.getStack(0));
            inventory.removeStack(0);
        }

        return ActionResult.CONSUME;
    }
}
