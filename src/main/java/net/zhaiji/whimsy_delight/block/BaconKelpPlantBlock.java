package net.zhaiji.whimsy_delight.block;

import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.KelpPlantBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.zhaiji.whimsy_delight.init.WDBlocks;

/**
 * 培根海带茎体（非生长节）。机制与原版 kelp_plant 一致：
 * 不参与随机 tick，被破坏时上方所有节一并断裂掉落。
 */
public class BaconKelpPlantBlock extends KelpPlantBlock {

    public BaconKelpPlantBlock() {
        super(Properties.of()
            .mapColor(MapColor.WATER)
            .noCollission()
            .instabreak()
            .sound(SoundType.WET_GRASS)
            .pushReaction(PushReaction.DESTROY));
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) WDBlocks.BACON_KELP.get();
    }
}
