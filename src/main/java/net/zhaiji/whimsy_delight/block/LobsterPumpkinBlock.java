package net.zhaiji.whimsy_delight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * 龙虾南瓜果实方块（普通状态，独立放置，无方向属性）。
 * 碰撞箱为南瓜形状（比完整方块略小）。
 */
public class LobsterPumpkinBlock extends Block {

    public LobsterPumpkinBlock() {
        super(Properties.of()
            .mapColor(MapColor.COLOR_ORANGE)
            .strength(1.0F)
            .noOcclusion());
        this.registerDefaultState(this.stateDefinition.any());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
}
