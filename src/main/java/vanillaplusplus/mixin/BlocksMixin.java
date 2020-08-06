package vanillaplusplus.mixin;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import vanillaplusplus.blocks.meltable.CustomGoldBlock;
import vanillaplusplus.blocks.meltable.CustomIronBlock;

@Mixin(Blocks.class)
public class BlocksMixin {

    private static Block register(String id, Block block) {
        if (id.equals("iron_block")) {
            return (Block)Registry.register(Registry.BLOCK, id, new CustomIronBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL)));
        } else if (id.equals("gold_block")) {
            return (Block)Registry.register(Registry.BLOCK, id, new CustomGoldBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.GOLD).requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.METAL)));
        }

        return (Block)Registry.register(Registry.BLOCK, id, block);
    }
}
