package vanillaplusplus.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class NetherStarIngotItem extends Item {
    public NetherStarIngotItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
