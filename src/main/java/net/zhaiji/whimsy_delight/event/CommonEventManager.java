package net.zhaiji.whimsy_delight.event;

import net.minecraftforge.eventbus.api.IEventBus;

public class CommonEventManager {
    public static void init(IEventBus modBus, IEventBus forgeBus) {
        CommonEventManager.modBusListener(modBus);
        CommonEventManager.forgeBusListener(forgeBus);
    }

    public static void modBusListener(IEventBus modBus) {
    }

    public static void forgeBusListener(IEventBus forgeBus) {
    }
}
