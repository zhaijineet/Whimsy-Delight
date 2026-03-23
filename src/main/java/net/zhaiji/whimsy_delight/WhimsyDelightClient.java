package net.zhaiji.whimsy_delight;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.zhaiji.whimsy_delight.client.event.ClientEventManager;

@OnlyIn(Dist.CLIENT)
public class WhimsyDelightClient {
    public static void init(IEventBus modBus, IEventBus forgeBus) {
        ClientEventManager.init(modBus, forgeBus);
    }
}
