package vanillaplusplus.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.client.gui.screen.recipebook.AbstractFurnaceRecipeBookScreen;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.Set;

@Environment(EnvType.CLIENT)
public class GrinderRecipeBookScreen extends AbstractFurnaceRecipeBookScreen {

    protected boolean isFilteringCraftable() {
        return this.recipeBook.isFurnaceFilteringCraftable();
    }

    protected void setFilteringCraftable(boolean filteringCraftable) {
        this.recipeBook.setFurnaceFilteringCraftable(filteringCraftable);
    }

    protected boolean isGuiOpen() {
        return this.recipeBook.isFurnaceGuiOpen();
    }

    protected void setGuiOpen(boolean opened) {
        this.recipeBook.setFurnaceGuiOpen(opened);
    }

    protected Text getToggleCraftableButtonText() {
        return new TranslatableText("gui.recipebook.toggleRecipes.grindable");
    }

    protected Set<Item> getAllowedFuels() {
        return AbstractFurnaceBlockEntity.createFuelTimeMap().keySet();
    }
}
