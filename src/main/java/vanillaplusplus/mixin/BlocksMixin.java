package vanillaplusplus.mixin;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vanillaplusplus.blocks.meltable.CustomGoldBlock;
import vanillaplusplus.blocks.meltable.CustomIronBlock;

@Mixin(Blocks.class)
public class BlocksMixin {

    @Inject(method="register", at=@At("HEAD"), cancellable = true)
    private static void onRegister(String id, Block block, CallbackInfoReturnable<Block> cbi) {
        if (id.equals("iron_block")) {
            cbi.setReturnValue(Registry.register(Registry.BLOCK, id, new CustomIronBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL))));
        } else if (id.equals("gold_block")) {
            cbi.setReturnValue(Registry.register(Registry.BLOCK, id, new CustomGoldBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.GOLD).requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.METAL))));
        }
    }
}
