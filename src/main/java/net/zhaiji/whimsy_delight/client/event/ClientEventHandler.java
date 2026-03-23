package net.zhaiji.whimsy_delight.client.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.zhaiji.whimsy_delight.client.render.MeatRafflesiaBlockEntityRenderer;
import net.zhaiji.whimsy_delight.init.WDBlockEntityType;

@OnlyIn(Dist.CLIENT)
public class ClientEventHandler {
    public static void handlerEntityRenderersEvent$RegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(WDBlockEntityType.MEAT_RAFFLESIA.get(), MeatRafflesiaBlockEntityRenderer::new);
    }
}
