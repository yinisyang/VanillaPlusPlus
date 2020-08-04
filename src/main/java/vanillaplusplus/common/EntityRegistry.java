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

    public static BlockEntityType<IronBlockEntity> IRON_BLOCK_ENTITY = BlockEntityType.Builder.create(IronBlockEntity::new, BlockRegistry.MELTABLE_IRON_BLOCK).build(null);
    public static BlockEntityType<SteelBlockEntity> STEEL_BLOCK_ENTITY = BlockEntityType.Builder.create(SteelBlockEntity::new, BlockRegistry.STEEL_BLOCK).build(null);
    public static BlockEntityType<GoldBlockEntity> GOLD_BLOCK_ENTITY = BlockEntityType.Builder.create(GoldBlockEntity::new, BlockRegistry.MELTABLE_GOLD_BLOCK).build(null);
    public static BlockEntityType<BeaconExtenderEntity> BEACON_EXTENDER_ENTITY = BlockEntityType.Builder.create(BeaconExtenderEntity::new, BlockRegistry.BEACON_EXTENDER).build(null);
    public static EntityType LIT_DYNAMITE_ENTITY = FabricEntityTypeBuilder.create(SpawnGroup.MISC, LitDynamiteEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build();

    public static void register() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "iron_block_entity"), IRON_BLOCK_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "steel_block_entity"), STEEL_BLOCK_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "gold_block_entity"), GOLD_BLOCK_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, "dynamite"), LIT_DYNAMITE_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "beacon_extender_entity"), BEACON_EXTENDER_ENTITY);
    }
}
