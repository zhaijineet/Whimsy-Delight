package net.zhaiji.whimsy_delight.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zhaiji.whimsy_delight.WhimsyDelight;

public class WDCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(
        Registries.CREATIVE_MODE_TAB,
        WhimsyDelight.MOD_ID
    );
    public static final String WHIMSY_DELIGHT_TAB_TRANSLATABLE = "itemGroup.whimsy_delight.creative_tab";

    public static final RegistryObject<CreativeModeTab> WHIMSY_DELIGHT_TAB = CREATIVE_MODE_TAB.register(
        "whimsy_delight_tab",
        () -> CreativeModeTab.builder()
            .icon(() -> WDItems.MEAT_RAFFLESIA.get().getDefaultInstance())
            .title(Component.translatable(WHIMSY_DELIGHT_TAB_TRANSLATABLE))
            .displayItems((pParameters, pOutput) -> {
                for (RegistryObject<Item> entry : WDItems.ITEM.getEntries()) {
                    pOutput.accept(entry.get());
                }
            })
            .build()
    );
}
