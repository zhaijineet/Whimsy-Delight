package net.zhaiji.whimsy_delight.client.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.zhaiji.whimsy_delight.WhimsyDelight;
import net.zhaiji.whimsy_delight.block.blockentity.WDBaseCropBlockEntity;
import software.bernie.geckolib.model.GeoModel;

@OnlyIn(Dist.CLIENT)
public class MeatRafflesiaModel extends GeoModel<WDBaseCropBlockEntity> {
    @Override
    public ResourceLocation getModelResource(WDBaseCropBlockEntity animatable) {
        int age = animatable.getBlockState().getValue(BlockStateProperties.AGE_3);
        return new ResourceLocation(WhimsyDelight.MOD_ID, "geo/meat_rafflesia_crop_stage_" + age + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WDBaseCropBlockEntity animatable) {
        int age = animatable.getBlockState().getValue(BlockStateProperties.AGE_3);
        return new ResourceLocation(WhimsyDelight.MOD_ID, "textures/block/meat_rafflesia_crop_stage_" + age + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(WDBaseCropBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderType getRenderType(WDBaseCropBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
}
