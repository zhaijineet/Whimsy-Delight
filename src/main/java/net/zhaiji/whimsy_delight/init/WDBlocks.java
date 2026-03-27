package net.zhaiji.whimsy_delight.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zhaiji.whimsy_delight.WhimsyDelight;
import net.zhaiji.whimsy_delight.block.CraftingStationBlock;
import net.zhaiji.whimsy_delight.block.MeatRafflesiaCropBlock;

public class WDBlocks {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(Registries.BLOCK, WhimsyDelight.MOD_ID);

    // 大五花
    public static final RegistryObject<Block> MEAT_RAFFLESIA = BLOCK.register(
        "meat_rafflesia",
        MeatRafflesiaCropBlock::new
    );

    // 工作站
    public static final RegistryObject<Block> CRAFTING_STATION = BLOCK.register(
        "crafting_station",
        CraftingStationBlock::new
    );
}
