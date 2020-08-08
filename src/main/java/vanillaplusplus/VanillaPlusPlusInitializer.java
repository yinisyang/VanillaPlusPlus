package vanillaplusplus;

import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
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

        DispenserBehavior moltenMetalBucketBehavior = new ItemDispenserBehavior() {
            private final ItemDispenserBehavior itemDispenserBehavior = new ItemDispenserBehavior();

            public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                BucketItem bucketItem = (BucketItem)stack.getItem();
                BlockPos blockPos = pointer.getBlockPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                World world = pointer.getWorld();
                if (bucketItem.placeFluid(null, world, blockPos, null)) {
                    bucketItem.onEmptied(world, stack, blockPos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.itemDispenserBehavior.dispense(pointer, stack);
                }
            }
        };
        DispenserBlock.registerBehavior(ItemRegistry.MOLTEN_GOLD_BUCKET, moltenMetalBucketBehavior);
        DispenserBlock.registerBehavior(ItemRegistry.MOLTEN_IRON_BUCKET, moltenMetalBucketBehavior);
        DispenserBlock.registerBehavior(ItemRegistry.MOLTEN_STEEL_BUCKET, moltenMetalBucketBehavior);
    }

    public static final ItemGroup VPP_ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(BlockRegistry.NETHER_STAR_BLOCK)
    );
}