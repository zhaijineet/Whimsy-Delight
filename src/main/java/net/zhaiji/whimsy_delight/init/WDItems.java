package net.zhaiji.whimsy_delight.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zhaiji.whimsy_delight.WhimsyDelight;

public class WDItems {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Registries.ITEM, WhimsyDelight.MOD_ID);

    public static final RegistryObject<Item> ICON = ITEM.register(
        "icon",
        () -> new Item(new Item.Properties())
    );
}
