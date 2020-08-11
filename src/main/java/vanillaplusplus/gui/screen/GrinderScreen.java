package vanillaplusplus.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.FurnaceRecipeBookScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import vanillaplusplus.gui.GrinderScreenHandler;

@Environment(EnvType.CLIENT)
public class GrinderScreen extends AbstractFurnaceScreen<GrinderScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("textures/gui/container/furnace.png");

    public GrinderScreen(GrinderScreenHandler container, PlayerInventory inventory, Text title) {
        super(container, new GrinderRecipeBookScreen(), inventory, title, TEXTURE);
    }
}
