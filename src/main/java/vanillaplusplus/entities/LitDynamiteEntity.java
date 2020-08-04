package vanillaplusplus.entities;

import vanillaplusplus.common.EntityRegistry;
import vanillaplusplus.common.ItemRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class LitDynamiteEntity extends ThrownItemEntity {

    private int timeToExplode;

    public LitDynamiteEntity(EntityType type, World world) {
        super(type, world);
        timeToExplode = 100;
    }

    public LitDynamiteEntity(World world, LivingEntity owner) {
        super(EntityRegistry.LIT_DYNAMITE_ENTITY, owner, world);
        timeToExplode = 10;
    }

    public LitDynamiteEntity(World world, double x, double y, double z) {
        super(EntityRegistry.LIT_DYNAMITE_ENTITY, x, y, z, world);
    }

    @Override
    public void tick() {
        super.tick();

        if (timeToExplode <= 0 && !this.world.isClient) {
            this.explode();
        }

        this.timeToExplode--;
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.DYNAMITE;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        this.explode();
    }

    private void explode() {
        this.world.createExplosion(this, this.getX(), this.getY() + 0.5D, this.getZ(), 2.0f, Explosion.DestructionType.BREAK);
        this.remove();
    }
}
