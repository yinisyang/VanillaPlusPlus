package vanillaplusplus.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class NetherStarShard extends Item {
    public NetherStarShard(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isFireproof() {
        return true;
    }
}
