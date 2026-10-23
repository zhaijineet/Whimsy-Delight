package net.zhaiji.whimsy_delight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.zhaiji.whimsy_delight.init.WDBlocks;
import net.zhaiji.whimsy_delight.init.WDItems;

/**
 * 龙虾南瓜茎：6个生长阶段的作物方块。
 * 机制与原版南瓜茎一致：成熟后随机 tick 时尝试在相邻四方向之一结果，
 * 结果成功后茎转变为附着茎（一株同时只结一个瓜）。
 */
public class LobsterPumpkinStemBlock extends CropBlock {
    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D)
    };

    public LobsterPumpkinStemBlock() {
        super(Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollission()
            .randomTicks()
            .instabreak()
            .pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
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
    protected int getBonemealAgeIncrease(Level pLevel) {
        return 1;
    }

    @Override
    protected net.minecraft.world.level.ItemLike getBaseSeedId() {
        return WDItems.LOBSTER_PUMPKIN_SEEDS.get();
    }

    /**
     * 成熟后仍需继续接收随机 tick 用于结果，因此始终返回 true（同原版 StemBlock）。
     */
    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    /**
     * 与原版 StemBlock.randomTick 一致：
     * 亮度>=9 时按生长速度概率推进；未成熟则长一阶段，成熟则随机选一个水平方向，
     * 该位置为空且下方为耕地/蛋白沃土/上表面完整方块时结果，
     * 同时把茎自身替换为朝向果实的附着茎。
     */
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.getRawBrightness(pPos, 0) >= 9) {
            float f = getGrowthSpeed(this, pLevel, pPos);
            if (pRandom.nextInt((int)(25.0F / f) + 1) == 0) {
                int age = this.getAge(pState);
                if (age < MAX_AGE) {
                    pLevel.setBlock(pPos, pState.setValue(AGE, age + 1), 2);
                } else {
                    Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom);
                    BlockPos fruitPos = pPos.relative(direction);
                    BlockState below = pLevel.getBlockState(fruitPos.below());
                    if (pLevel.isEmptyBlock(fruitPos) && this.canFruitSitOn(below, pLevel, fruitPos.below())) {
                        pLevel.setBlockAndUpdate(fruitPos,
                            WDBlocks.LOBSTER_PUMPKIN_ATTACHED.get().defaultBlockState()
                                .setValue(LobsterPumpkinAttachedBlock.FACING, direction.getOpposite()));
                        pLevel.setBlockAndUpdate(pPos,
                            WDBlocks.LOBSTER_PUMPKIN_STEM_ATTACHED.get().defaultBlockState()
                                .setValue(LobsterPumpkinStemAttachedBlock.FACING, direction));
                    }
                }
            }
        }
    }

    /**
     * 果实基底判定：与原版一致（耕地或上表面完整），额外允许模组的蛋白沃土。
     */
    private boolean canFruitSitOn(BlockState below, BlockGetter pLevel, BlockPos belowPos) {
        return below.is(Blocks.FARMLAND)
            || below.is(WDBlocks.PROTEIN_SOIL.get())
            || below.isFaceSturdy(pLevel, belowPos, Direction.UP);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
