package net.zhaiji.whimsy_delight.client.render;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.zhaiji.whimsy_delight.block.blockentity.WDBaseCropBlockEntity;
import net.zhaiji.whimsy_delight.client.model.MeatRafflesiaModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class MeatRafflesiaBlockEntityRenderer extends GeoBlockRenderer<WDBaseCropBlockEntity> {
    public MeatRafflesiaBlockEntityRenderer(BlockEntityRendererProvider.Context pContext) {
        super(new MeatRafflesiaModel());
    }
}
