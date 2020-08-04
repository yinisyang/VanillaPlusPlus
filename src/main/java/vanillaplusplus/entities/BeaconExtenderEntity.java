package vanillaplusplus.entities;

import vanillaplusplus.common.EntityRegistry;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class BeaconExtenderEntity extends BlockEntity implements Tickable {

    private BeaconBlockEntity linkedEntity;

    public BeaconExtenderEntity() {
        super(EntityRegistry.BEACON_EXTENDER_ENTITY);
    }

    public void linkBeacon(BeaconBlockEntity beaconBlockEntity) {
        this.linkedEntity = beaconBlockEntity;
    }

    public void unlink() {
        this.linkedEntity = null;
    }

    @Override
    public void tick() {
        if (linkedEntity != null) {
            System.out.println("Linked beacon block entity");

        }
        //linkedEntity.primary;
    }
}
