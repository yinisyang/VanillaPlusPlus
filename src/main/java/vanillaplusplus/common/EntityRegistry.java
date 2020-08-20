package vanillaplusplus.common;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import vanillaplusplus.entities.*;

import static vanillaplusplus.VanillaPlusPlusInitializer.MOD_ID;

public class EntityRegistry {

    public static BlockEntityType<BeaconExtenderEntity> BEACON_EXTENDER_ENTITY = BlockEntityType.Builder.create(BeaconExtenderEntity::new, BlockRegistry.BEACON_EXTENDER).build(null);
    public static BlockEntityType<StandBlockEntity> STAND_BLOCK_ENTITY = BlockEntityType.Builder.create(StandBlockEntity::new, BlockRegistry.ITEM_STAND).build(null);
    public static BlockEntityType<InfusionStandBlockEntity> INFUSION_STAND_BLOCK_ENTITY = BlockEntityType.Builder.create(InfusionStandBlockEntity::new, BlockRegistry.INFUSION_STAND).build(null);
    public static EntityType LIT_DYNAMITE_ENTITY = FabricEntityTypeBuilder.create(SpawnGroup.MISC, LitDynamiteEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build();

    public static void register() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "stand_block_entity"), STAND_BLOCK_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "infusion_stand_block_entity"), INFUSION_STAND_BLOCK_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, "dynamite"), LIT_DYNAMITE_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "beacon_extender_entity"), BEACON_EXTENDER_ENTITY);
    }
}
