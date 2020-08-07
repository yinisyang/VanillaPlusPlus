package vanillaplusplus.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
            if (isCenter(world, blockPos)) {
                ItemStack result = craft(blockPos, world);
                if (!result.isEmpty()) {
                    inventory.setStack(0, result);

                    ((Inventory)world.getBlockEntity(blockPos.south().south())).removeStack(0);
                    ((Inventory)world.getBlockEntity(blockPos.west().west())).removeStack(0);
                    ((Inventory)world.getBlockEntity(blockPos.north().north())).removeStack(0);
                    ((Inventory)world.getBlockEntity(blockPos.east().east())).removeStack(0);

                    if (world.isClient) {
                        world.playSound(player, blockPos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 0.6f, 1.0f);
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

        return ActionResult.CONSUME;
    }

    private boolean isCenter(World world, BlockPos pos) {
        boolean south = world.getBlockState(pos.south().south()).isOf(BlockRegistry.STAND);
        boolean west = world.getBlockState(pos.west().west()).isOf(BlockRegistry.STAND);
        boolean north = world.getBlockState(pos.north().north()).isOf(BlockRegistry.STAND);
        boolean east = world.getBlockState(pos.east().east()).isOf(BlockRegistry.STAND);

        return south && west && north && east;
    }

    private ItemStack craft(BlockPos pos, World world) {
        Item centerItem = ((Inventory)world.getBlockEntity(pos)).getStack(0).getItem();
        Item south = ((Inventory)world.getBlockEntity(pos.south().south())).getStack(0).getItem();
        Item west = ((Inventory)world.getBlockEntity(pos.west().west())).getStack(0).getItem();
        Item north = ((Inventory)world.getBlockEntity(pos.north().north())).getStack(0).getItem();
        Item east = ((Inventory)world.getBlockEntity(pos.east().east())).getStack(0).getItem();

        return ItemStandRecipes.getResultIfRecipeExists(centerItem, south, west, north, east);
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
