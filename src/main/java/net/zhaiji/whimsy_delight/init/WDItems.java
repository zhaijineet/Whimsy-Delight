package net.zhaiji.whimsy_delight.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zhaiji.whimsy_delight.WhimsyDelight;

public class WDItems {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Registries.ITEM, WhimsyDelight.MOD_ID);

    // 大五花种子
    public static final RegistryObject<Item> MEAT_RAFFLESIA_SEEDS = ITEM.register(
        "meat_rafflesia_seeds",
        () -> new BlockItem(WDBlocks.MEAT_RAFFLESIA.get(), new Item.Properties())
    );

    // 大五花花苞
    public static final RegistryObject<Item> MEAT_RAFFLESIA_BUD = ITEM.register(
        "meat_rafflesia_bud",
        () -> new Item(new Item.Properties())
    );

    // 大五花
    public static final RegistryObject<Item> MEAT_RAFFLESIA = ITEM.register(
        "meat_rafflesia",
        () -> new Item(new Item.Properties())
    );

    // 工作站
    public static final RegistryObject<Item> CRAFTING_STATION = ITEM.register(
        "crafting_station",
        () -> new BlockItem(WDBlocks.CRAFTING_STATION.get(), new Item.Properties())
    );
}
