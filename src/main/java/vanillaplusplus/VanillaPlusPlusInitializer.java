package vanillaplusplus;

import vanillaplusplus.common.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class VanillaPlusPlusInitializer implements ModInitializer {

    public static final String MOD_ID = "vanilla_plus_plus";


    @Override
    public void onInitialize() {
        FluidRegistry.register();
        BlockRegistry.register();
        EntityRegistry.register();
        ItemRegistry.register();
        BiomeRegistry.register();
        
        ItemDispenserBehavior witherBoneMealBehavior = new ItemDispenserBehavior() {
            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                BlockPos blockPos = pointer.getBlockPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                World world = pointer.getWorld();

                if (world.getBlockState(blockPos).isOf(Blocks.NETHER_WART)) {

                    BlockState blockState = pointer.getWorld().getBlockState(blockPos);
                    int age = blockState.get(NetherWartBlock.AGE);
                    int increment = new Random().nextInt(2) + 1;

                    if (age + increment > 3) {
                        increment = 3 - age;
                    }

                    if (age < 3) {
                        if (!world.isClient()) {
                            blockState = blockState.with(NetherWartBlock.AGE, age + increment);
                            world.setBlockState(blockPos, blockState, 2);
                        } else {
                            world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
                        }
                        stack.decrement(1);
                    }
                }
                return stack;
            }
        };

        DispenserBlock.registerBehavior(ItemRegistry.WITHER_BONE_MEAL, witherBoneMealBehavior);
/*
        ItemDispenserBehavior moltenIronBucketBehavior = new ItemDispenserBehavior() {
            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                BlockPos blockPos = pointer.getBlockPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                World world = pointer.getWorld();

                if (world.getBlockState(blockPos).isAir()) {
                    world.setBlockState(blockPos, FluidRegistry.MOLTEN_IRON_BLOCK.getDefaultState());
                }

                return stack;
            }
        };

*/
    }

    public static final ItemGroup VPP_ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(BlockRegistry.NETHER_STAR_BLOCK)
    );
}