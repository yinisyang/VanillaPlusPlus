package vanillaplusplus.entities;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import vanillaplusplus.common.EntityRegistry;
import vanillaplusplus.util.ImplementedInventory;

public class InfusionStandBlockEntity extends StandBlockEntity implements Tickable {

    @Override
    public void tick() {

    }

    public InfusionStandBlockEntity() {
        super(EntityRegistry.INFUSION_STAND_BLOCK_ENTITY);
    }

}
