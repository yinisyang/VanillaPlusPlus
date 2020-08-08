package vanillaplusplus.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vanillaplusplus.common.EntityRegistry;
import vanillaplusplus.entities.LitDynamiteEntity;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow
    private ClientWorld world;

    // Set the staged entity type
    @Inject(method = "onEntitySpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/s2c/play/EntitySpawnS2CPacket;getX()D"))
    private void handleEntitySpawn(EntitySpawnS2CPacket pkt, CallbackInfo ci) {
        Entity toSpawn = null;
        double x = pkt.getX();
        double y = pkt.getY();
        double z = pkt.getZ();
        if (pkt.getEntityTypeId() == EntityRegistry.LIT_DYNAMITE_ENTITY) {
            toSpawn = new LitDynamiteEntity(this.world, pkt.getX(), pkt.getY(), pkt.getZ());
        }

        if (toSpawn != null) {
            // Copied from end of ClientPlayNetworkHandler#onEntitySpawn
            toSpawn.updateTrackedPosition(x, y, z);
            toSpawn.refreshPositionAfterTeleport(x, y, z);
            toSpawn.pitch = (float) (pkt.getPitch() * 360) / 256.0F;
            toSpawn.yaw = (float) (pkt.getYaw() * 360) / 256.0F;
            toSpawn.setEntityId(pkt.getId());
            toSpawn.setUuid(pkt.getUuid());
            this.world.addEntity(pkt.getId(), toSpawn);
        }
    }
}
