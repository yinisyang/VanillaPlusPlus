package vanillaplusplus.blocks;

import net.minecraft.block.*;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import java.util.ArrayList;
import java.util.List;

public class AdvancedDispenser extends DispenserBlock {

    private static final List<Block> PLACEABLE_BLOCKS = new ArrayList<>();

    public AdvancedDispenser(Settings settings) {
        super(settings);

        ItemDispenserBehavior placeBehavior = new ItemDispenserBehavior() {
            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                BlockPos blockPos = pointer.getBlockPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                if (pointer.getBlockState().getBlock() instanceof AdvancedDispenser) {
                    if (pointer.getWorld().getBlockState(blockPos).isAir()) {
                        pointer.getWorld().setBlockState(blockPos, Block.getBlockFromItem(stack.getItem()).getDefaultState());
                        stack.decrement(1);
                    }
                } else {
                    Direction direction = (Direction)pointer.getBlockState().get(DispenserBlock.FACING);
                    Position position = DispenserBlock.getOutputLocation(pointer);
                    ItemStack itemStack = stack.split(1);
                    ItemDispenserBehavior.spawnItem(pointer.getWorld(), itemStack, 6, direction, position);
                }
                return stack;
            }
        };

        for(Block block: PLACEABLE_BLOCKS) {
            DispenserBlock.registerBehavior(block, placeBehavior);
        }
/*
        ItemDispenserBehavior stripLogBehavior = new ItemDispenserBehavior() {
            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                BlockPos blockPos = pointer.getBlockPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                if (pointer.getBlockState().getBlock() instanceof AdvancedDispenser) {
                    Block block = (Block)STRIPPED_BLOCKS.get(blockState.getBlock());
                    if (pointer.getWorld().getBlockState(blockPos).isOf(Blocks.OAK_LOG)) {
                        pointer.getWorld().getBlockState(blockPos).getBlock()
                        pointer.getWorld().setBlockState(blockPos, Block.getBlockFromItem(stack.getItem()).getDefaultState());
                        stack.decrement(1);
                    }
                } else {
                    Direction direction = (Direction)pointer.getBlockState().get(DispenserBlock.FACING);
                    Position position = DispenserBlock.getOutputLocation(pointer);
                    ItemStack itemStack = stack.split(1);
                    ItemDispenserBehavior.spawnItem(pointer.getWorld(), itemStack, 6, direction, position);
                }
                return stack;
            }
        };

        DispenserBlock.registerBehavior(Items.IRON_AXE, stripLogBehavior);*/
    }

    static {
        PLACEABLE_BLOCKS.add(Blocks.COBBLESTONE);
        PLACEABLE_BLOCKS.add(Blocks.STONE);
        PLACEABLE_BLOCKS.add(Blocks.ACACIA_LOG);
        PLACEABLE_BLOCKS.add(Blocks.BIRCH_LOG);
        PLACEABLE_BLOCKS.add(Blocks.DARK_OAK_LOG);
        PLACEABLE_BLOCKS.add(Blocks.JUNGLE_LOG);
        PLACEABLE_BLOCKS.add(Blocks.OAK_LOG);
        PLACEABLE_BLOCKS.add(Blocks.SPRUCE_LOG);
        PLACEABLE_BLOCKS.add(Blocks.ACACIA_WOOD);
        PLACEABLE_BLOCKS.add(Blocks.BIRCH_WOOD);
        PLACEABLE_BLOCKS.add(Blocks.DARK_OAK_WOOD);
        PLACEABLE_BLOCKS.add(Blocks.JUNGLE_WOOD);
        PLACEABLE_BLOCKS.add(Blocks.OAK_WOOD);
        PLACEABLE_BLOCKS.add(Blocks.SPRUCE_WOOD);
        PLACEABLE_BLOCKS.add(Blocks.COAL_ORE);
        PLACEABLE_BLOCKS.add(Blocks.DIAMOND_ORE);
        PLACEABLE_BLOCKS.add(Blocks.EMERALD_ORE);
        PLACEABLE_BLOCKS.add(Blocks.GOLD_ORE);
        PLACEABLE_BLOCKS.add(Blocks.IRON_ORE);
        PLACEABLE_BLOCKS.add(Blocks.LAPIS_ORE);
        PLACEABLE_BLOCKS.add(Blocks.REDSTONE_ORE);
        PLACEABLE_BLOCKS.add(Blocks.NETHER_GOLD_ORE);
        PLACEABLE_BLOCKS.add(Blocks.NETHER_QUARTZ_ORE);
        PLACEABLE_BLOCKS.add(Blocks.NETHERRACK);
        PLACEABLE_BLOCKS.add(Blocks.SAND);
        PLACEABLE_BLOCKS.add(Blocks.GRAVEL);
        PLACEABLE_BLOCKS.add(Blocks.DIRT);
        PLACEABLE_BLOCKS.add(Blocks.COARSE_DIRT);
        PLACEABLE_BLOCKS.add(Blocks.GRASS_BLOCK);
        PLACEABLE_BLOCKS.add(Blocks.ANDESITE);
        PLACEABLE_BLOCKS.add(Blocks.GRANITE);
        PLACEABLE_BLOCKS.add(Blocks.DIORITE);
        PLACEABLE_BLOCKS.add(Blocks.ACACIA_LEAVES);
        PLACEABLE_BLOCKS.add(Blocks.BIRCH_LEAVES);
        PLACEABLE_BLOCKS.add(Blocks.DARK_OAK_LEAVES);
        PLACEABLE_BLOCKS.add(Blocks.JUNGLE_LEAVES);
        PLACEABLE_BLOCKS.add(Blocks.OAK_LEAVES);
        PLACEABLE_BLOCKS.add(Blocks.SPRUCE_LEAVES);
        PLACEABLE_BLOCKS.add(Blocks.PISTON);
        PLACEABLE_BLOCKS.add(Blocks.STICKY_PISTON);
    }
}
