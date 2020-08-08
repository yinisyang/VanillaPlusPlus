package vanillaplusplus.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
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
        Inventory inventory = (Inventory) world.getBlockEntity(blockPos);

        // If inventory is empty
        if (inventory.getStack(0).isEmpty()) {
            inventory.setStack(0, player.getStackInHand(hand).split(1));
        } else { // If inventory is full
            if (isCenter(world, blockPos) && player.getStackInHand(hand).getItem() == Items.LAPIS_LAZULI) {
                Inventory south = (Inventory)world.getBlockEntity(blockPos.south(2));
                Inventory west = (Inventory)world.getBlockEntity(blockPos.west(2));
                Inventory north = (Inventory)world.getBlockEntity(blockPos.north(2));
                Inventory east = (Inventory)world.getBlockEntity(blockPos.east(2));

                ItemStack result = ItemStandRecipes.getResultIfRecipeExists(inventory.getStack(0), south.getStack(0), west.getStack(0), north.getStack(0), east.getStack(0));
                if (!result.isEmpty()) {
                    inventory.setStack(0, result);
                    player.getStackInHand(hand).decrement(1);

                    south.removeStack(0);
                    west.removeStack(0);
                    north.removeStack(0);
                    east.removeStack(0);

                    if (world.isClient) {
                        world.playSound(player, blockPos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 0.6f, 1.0f);
                        world.addParticle(ParticleTypes.SMOKE, blockPos.getX(), blockPos.up().getY(), blockPos.getZ(), 0, 0, 0);
                    }
                } else {
                    player.inventory.offerOrDrop(world, inventory.getStack(0));
                    inventory.removeStack(0);
                }
            } else {
                player.inventory.offerOrDrop(world, inventory.getStack(0));
                inventory.removeStack(0);
            }
        }

        // Play sound on the client
        if (world.isClient) {
            world.playSound(player, blockPos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.5f, 1.0f);
        }

        ((StandBlockEntity)world.getBlockEntity(blockPos)).markDirty(world);

        return ActionResult.CONSUME;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);

        Inventory inv = (Inventory)world.getBlockEntity(pos);
        ItemEntity stack = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), inv.getStack(0));
        world.spawnEntity(stack);
    }

    private boolean isCenter(World world, BlockPos pos) {
        boolean south = world.getBlockState(pos.south(2)).isOf(BlockRegistry.STAND);
        boolean west = world.getBlockState(pos.west(2)).isOf(BlockRegistry.STAND);
        boolean north = world.getBlockState(pos.north(2)).isOf(BlockRegistry.STAND);
        boolean east = world.getBlockState(pos.east(2)).isOf(BlockRegistry.STAND);

        return south && west && north && east;
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
