package vanillaplusplus.entities.renderers;

import vanillaplusplus.entities.LitDynamiteEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;

public class LitDynamiteRenderer extends EntityRenderer<LitDynamiteEntity> {


    public LitDynamiteRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public Identifier getTexture(LitDynamiteEntity itemEntity) {
        return new Identifier("vanilla_plus_plus", "dynamite");
    }
}
