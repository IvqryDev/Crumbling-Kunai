package net.ivqrydev.crumbling_kunai.sound;

import net.ivqrydev.crumbling_kunai.CrumblingKunai;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.Holder;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, CrumblingKunai.MOD_ID);

    public static final Holder<SoundEvent> REFRESH = SOUND_EVENTS.register("refresh",
            () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(CrumblingKunai.MOD_ID, "refresh")
            ));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}