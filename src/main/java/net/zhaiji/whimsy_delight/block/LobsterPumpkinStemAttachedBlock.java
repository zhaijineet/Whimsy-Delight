package net.zhaiji.whimsy_delight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.zhaiji.whimsy_delight.init.WDBlocks;

/**
 * 龙虾南瓜附着茎：茎结果后转变而成，FACING 朝向果实。
 * 与原版 AttachedStemBlock 一致：不再随机 tick（一株只结一个瓜），
 * 果实被移除后变回成熟茎，可再次结果。
 */
public class LobsterPumpkinStemAttachedBlock extends BushBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    public LobsterPumpkinStemAttachedBlock() {
        super(Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollission()
            .instabreak()
            .pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    /**
     * 果实被移除（朝向方向的邻居不再是龙虾南瓜）时，变回成熟茎以便再次结果。
     */
    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState,
                                  LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        if (pDirection == pState.getValue(FACING)
            && !pNeighborState.is(WDBlocks.LOBSTER_PUMPKIN_ATTACHED.get())
            && !pNeighborState.is(WDBlocks.LOBSTER_PUMPKIN.get())) {
            return WDBlocks.LOBSTER_PUMPKIN_STEM.get().defaultBlockState()
                .setValue(LobsterPumpkinStemBlock.AGE, LobsterPumpkinStemBlock.MAX_AGE);
        }
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
