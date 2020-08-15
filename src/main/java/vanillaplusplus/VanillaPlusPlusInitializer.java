package vanillaplusplus;

import net.minecraft.block.dispenser.DispenserBehavior;
import vanillaplusplus.common.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vanillaplusplus.items.WitherBoneMeal;

/**
 * Suggestion List:
 * https://www.reddit.com/r/minecraftsuggestions/comments/hsyacf/when_leftclicking_on_a_nether_portal_tnt_campfire/
 * https://www.reddit.com/r/minecraftsuggestions/comments/i6ink9/idea_for_bamboo_being_used_as_a_wood_block/
 * https://www.reddit.com/r/minecraftsuggestions/comments/hux92k/gravity_blocks_stick_to_honey_blocks/
 * https://www.reddit.com/r/minecraftsuggestions/comments/i5go6i/scaffolding_should_be_spawn_proof/
 * https://www.reddit.com/r/minecraftsuggestions/comments/g2jfyd/saplings_planted_on_sand_should_turn_into_dead/
 */

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

                WitherBoneMeal.fertilize(pointer.getWorld(), pointer.getBlockPos().offset(pointer.getBlockState().get(DispenserBlock.FACING)), stack);
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