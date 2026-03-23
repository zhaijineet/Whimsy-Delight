package net.zhaiji.whimsy_delight.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zhaiji.whimsy_delight.WhimsyDelight;

public class WDItems {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Registries.ITEM, WhimsyDelight.MOD_ID);

    // 大五花
    public static final RegistryObject<Item> MEAT_RAFFLESIA = ITEM.register(
        "meat_rafflesia",
        () -> new BlockItem(WDBlocks.MEAT_RAFFLESIA.get(),new Item.Properties())
    );
}
