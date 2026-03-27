package net.zhaiji.whimsy_delight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zhaiji.whimsy_delight.WhimsyDelight;
import net.zhaiji.whimsy_delight.init.WDBlocks;

public class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {
    public BlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, WhimsyDelight.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        craftStation();
        meatRafflesia();
    }

    private void craftStation() {
        Block block = WDBlocks.CRAFTING_STATION.get();
        ModelFile modelFile = models().getExistingFile(modLoc("block/crafting_station"));
        horizontalBlock(block, modelFile);
        simpleBlockItem(block, modelFile);
    }

    private void meatRafflesia() {
        Block block = WDBlocks.MEAT_RAFFLESIA.get();
        ModelFile modelFile = models().getExistingFile(modLoc("block/meat_rafflesia"));
        for (int age = 0; age <= 3; age++) {
            getVariantBuilder(block)
                .partialState()
                .with(BlockStateProperties.AGE_3, age)
                .modelForState()
                .modelFile(modelFile)
                .addModel();
        }
    }
}
