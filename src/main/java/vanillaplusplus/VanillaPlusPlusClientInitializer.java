package vanillaplusplus;

import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import vanillaplusplus.common.EntityRegistry;
import vanillaplusplus.entities.renderers.LitDynamiteRenderer;
import vanillaplusplus.common.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import vanillaplusplus.entities.renderers.StandBlockEntityRenderer;

@Environment(EnvType.CLIENT)
public class VanillaPlusPlusClientInitializer implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.LIT_DYNAMITE_ENTITY, (dispatcher, context) -> new LitDynamiteRenderer(dispatcher));
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.STEEL_SCAFFOLD, RenderLayer.getCutout());
        BlockEntityRendererRegistry.INSTANCE.register(EntityRegistry.STAND_BLOCK_ENTITY, StandBlockEntityRenderer::new);
    }
}
