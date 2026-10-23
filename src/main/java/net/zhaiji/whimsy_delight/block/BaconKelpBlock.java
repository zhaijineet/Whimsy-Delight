package net.zhaiji.whimsy_delight.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.KelpBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.zhaiji.whimsy_delight.init.WDBlocks;

/**
 * 培根海带头（生长点）。机制与原版海带一致：
 * 水中种植，随机 tick 向上生长（age 0~25 封顶），
 * 生长时当前头变为茎体、上方生成新头。
 */
public class BaconKelpBlock extends KelpBlock {

    public BaconKelpBlock() {
        super(Properties.of()
            .mapColor(MapColor.WATER)
            .noCollission()
            .randomTicks()
            .instabreak()
            .sound(SoundType.WET_GRASS)
            .pushReaction(PushReaction.DESTROY));
    }

    @Override
    protected Block getBodyBlock() {
        return WDBlocks.BACON_KELP_PLANT.get();
    }
}
