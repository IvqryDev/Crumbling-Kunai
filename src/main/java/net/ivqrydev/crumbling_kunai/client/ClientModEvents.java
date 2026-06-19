package net.ivqrydev.crumbling_kunai.client;

import net.ivqrydev.crumbling_kunai.particle.CrumblingDustParticle;
import net.ivqrydev.crumbling_kunai.particle.ModParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = "crumbling_kunai", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.CRUMBLING_DUST.get(), CrumblingDustParticle.Provider::new);
    }
}