package vanillaplusplus.entities;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import vanillaplusplus.common.EntityRegistry;
import vanillaplusplus.util.ImplementedInventory;

public class StandBlockEntity extends BlockEntity implements ImplementedInventory, BlockEntityClientSerializable {

    private DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public StandBlockEntity() {
        super(EntityRegistry.STAND_BLOCK_ENTITY);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, items);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, items);
        return super.toTag(tag);
    }

    public void markDirty(World world) {
        super.markDirty();
        if (!world.isClient)
            sync();
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        Inventories.fromTag(compoundTag, items);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return Inventories.toTag(compoundTag, items);
    }
}
