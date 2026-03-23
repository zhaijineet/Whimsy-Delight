package net.zhaiji.whimsy_delight.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.zhaiji.whimsy_delight.init.WDBlockEntityType;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WDBaseCropBlockEntity extends BlockEntity implements GeoBlockEntity {
    public final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public WDBaseCropBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(WDBlockEntityType.MEAT_RAFFLESIA.get(), pPos, pBlockState);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
