package net.zhaiji.whimsy_delight.client.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;

@OnlyIn(Dist.CLIENT)
public class ClientEventManager {
    public static void init(IEventBus modBus, IEventBus forgeBus) {
        ClientEventManager.modBusListener(modBus);
        ClientEventManager.forgeBusListener(forgeBus);
    }

    public static void modBusListener(IEventBus modBus) {
    }

    public static void forgeBusListener(IEventBus forgeBus) {
        forgeBus.addListener(ClientEventHandler::handlerEntityRenderersEvent$RegisterRenderers);
    }
}
