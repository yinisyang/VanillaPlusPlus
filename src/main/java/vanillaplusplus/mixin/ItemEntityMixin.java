package vanillaplusplus.mixin;

import vanillaplusplus.common.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    @Shadow
    public abstract ItemStack getStack();

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void breakNetherStar(DamageSource source, float amount, CallbackInfoReturnable info) {

        if (this.getStack().getItem() == Items.NETHER_STAR && source.isExplosive()) {
            this.remove();

            World world = this.getEntityWorld();
            BlockPos pos = this.getBlockPos();
            int mult = this.getStack().getCount();

            // Spawn lightning
            //LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            //lightning.setPos(pos.getX(), pos.getY(), pos.getZ());
            //world.spawnEntity(lightning);

            // Spawn nether star shards
            ItemStack stack = new ItemStack(ItemRegistry.NETHER_STAR_SHARD, 4 * mult);
            ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.spawnEntity(itemEntity);

            world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.AMBIENT, 1.0f, 1.0f, true);
        }
    }
}
