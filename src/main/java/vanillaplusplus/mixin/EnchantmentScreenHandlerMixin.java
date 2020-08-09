package vanillaplusplus.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.EnchantmentScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {

    @Inject(method="onContentChanged", at=@At("HEAD"), cancellable = true)
    public void onOnContentChanged(Inventory inventory, CallbackInfo cbi) {
        Item item = inventory.getStack(0).getItem();
        if (item == Items.DIAMOND_AXE ||
                item == Items.DIAMOND_SHOVEL ||
                item == Items.DIAMOND_PICKAXE ||
                item == Items.DIAMOND_HOE) {
            cbi.cancel();
        }
    }
}
