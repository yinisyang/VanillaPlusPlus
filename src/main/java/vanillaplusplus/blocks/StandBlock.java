package vanillaplusplus.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import vanillaplusplus.common.BlockRegistry;
import vanillaplusplus.entities.StandBlockEntity;
import vanillaplusplus.util.ItemStandRecipes;

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
        StandBlockEntity standBlockEntity = (StandBlockEntity) world.getBlockEntity(blockPos);

        // If inventory is empty
        if (standBlockEntity.getStack(0).isEmpty()) {
            standBlockEntity.setStack(0, player.isCreative() ? player.getStackInHand(hand).copy().split(1) : player.getStackInHand(hand).split(1));
        } else { // If inventory is full
            if (!player.isCreative()) {
                player.inventory.offerOrDrop(world, standBlockEntity.getStack(0));
            }
            standBlockEntity.removeStack(0);
        }
        standBlockEntity.markDirty(world);

        if (world.isClient) {
            world.playSound(player, blockPos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.5f, 1.0f);
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);

        Inventory inv = (Inventory)world.getBlockEntity(pos);
        ItemEntity stack = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), inv.getStack(0));
        world.spawnEntity(stack);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        VoxelShape middle = Block.createCuboidShape(3, 3, 3, 13, 13, 13);
        VoxelShape bottom = Block.createCuboidShape(0, 0, 0, 16, 3, 16);
        VoxelShape top = Block.createCuboidShape(0, 13, 0, 16, 16, 16);
        return VoxelShapes.union(bottom, middle, top);
    }
}
