package vanillaplusplus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
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
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import vanillaplusplus.common.BlockRegistry;
import vanillaplusplus.entities.InfusionStandBlockEntity;
import vanillaplusplus.entities.StandBlockEntity;
import vanillaplusplus.util.ItemStandRecipes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class InfusionStandBlock extends StandBlock implements BlockEntityProvider {

    private static final int MAX_SEARCH_DISTANCE = 10;

    public InfusionStandBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new InfusionStandBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        InfusionStandBlockEntity infusionStandBlockEntity = (InfusionStandBlockEntity)world.getBlockEntity(blockPos);

        ArrayList<StandBlockEntity> standBlockEntities = getOuterStands(world, blockPos);

        if (standBlockEntities.size() == 4 && player.getStackInHand(hand).getItem() == Items.LAPIS_LAZULI) {

            ItemStack result = ItemStandRecipes.getResult(player, infusionStandBlockEntity, standBlockEntities);
            // Crafting was successful
            if (!result.isEmpty()) {
                infusionStandBlockEntity.setStack(0, result);
                infusionStandBlockEntity.markDirty(world);

                // Don't use the lapis if in creative
                if (!player.isCreative()) {
                    player.getStackInHand(hand).decrement(1);
                }

                // Clear outer item stands
                for (StandBlockEntity standBlockEntity: standBlockEntities) {
                    standBlockEntity.removeStack(0);
                    standBlockEntity.markDirty(world);
                }

                if (world.isClient) {
                    world.playSound(player, blockPos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 0.6f, 1.0f);
                    world.addParticle(ParticleTypes.SMOKE, blockPos.getX(), blockPos.up().getY(), blockPos.getZ(), 0, 0, 0);
                }
                return ActionResult.SUCCESS;
            }
        }

        return super.onUse(blockState, world, blockPos, player, hand, blockHitResult);
    }

    // Searches out MAX_SEARCH_DISTANCE in each cardinal direction for
    // a StandBlock and returns what it finds
    private ArrayList<StandBlockEntity> getOuterStands(World world, BlockPos pos) {
        ArrayList<StandBlockEntity> inventories = new ArrayList<>();

        Direction[] directions = {Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST};
        for (Direction dir: directions) {
            for (int i = 0; i < MAX_SEARCH_DISTANCE; i++) {
                BlockPos cur = pos.offset(dir, i);
                if (world.getBlockState(cur).isOf(BlockRegistry.STAND)) {
                    inventories.add((StandBlockEntity) world.getBlockEntity(cur));
                    break;
                }
            }
        }
        return inventories;
    }
}
