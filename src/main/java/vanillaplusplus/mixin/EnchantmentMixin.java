package vanillaplusplus.mixin;

import net.minecraft.enchantment.AquaAffinityEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Inject(method="isAvailableForRandomSelection", at=@At("HEAD"), cancellable = true)
    public void onIsAvailableForRandomSelection(CallbackInfoReturnable<Boolean> cbi) {
        if (this.equals(Enchantments.AQUA_AFFINITY) || this.equals(Enchantments.EFFICIENCY)
            || this.equals(Enchantments.FORTUNE) || this.equals(Enchantments.INFINITY)
            || this.equals(Enchantments.MENDING) || this.equals(Enchantments.PROTECTION)
            || this.equals(Enchantments.SHARPNESS) || this.equals(Enchantments.SILK_TOUCH)
            || this.equals(Enchantments.UNBREAKING)) {
            cbi.setReturnValue(Boolean.FALSE);
        }
    }
}
