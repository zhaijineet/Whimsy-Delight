package net.zhaiji.whimsy_delight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.zhaiji.whimsy_delight.block.blockentity.WDBaseCropBlockEntity;
import net.zhaiji.whimsy_delight.init.WDItems;
import org.jetbrains.annotations.Nullable;

/**
 * @see net.minecraft.world.level.block.BeetrootBlock
 * 目前抄的甜菜根的
 */
public class MeatRafflesiaCropBlock extends CropBlock implements EntityBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)
    };

    public MeatRafflesiaCropBlock() {
        super(
            Properties.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.CROP)
                .pushReaction(PushReaction.DESTROY)
        );
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[this.getAge(pState)];
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(3) != 0) {
            super.randomTick(pState, pLevel, pPos, pRandom);
        }
    }

    @Override
    protected int getBonemealAgeIncrease(Level pLevel) {
        return super.getBonemealAgeIncrease(pLevel) / 3;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return WDItems.MEAT_RAFFLESIA_SEEDS.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new WDBaseCropBlockEntity(pPos, pState);
    }
}
