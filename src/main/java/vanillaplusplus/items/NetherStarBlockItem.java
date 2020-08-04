package vanillaplusplus.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class NetherStarBlockItem extends BlockItem {
    public NetherStarBlockItem(Block block, Settings settings) {
        super(block, settings);
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
