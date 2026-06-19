package net.ivqrydev.crumbling_kunai.effect;

import net.ivqrydev.crumbling_kunai.CrumblingKunai;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, CrumblingKunai.MOD_ID);

    public static final Holder<MobEffect> CRUMBLING_EFFECT = MOB_EFFECTS.register("crumbling",
            () -> new CrumblingEffect(MobEffectCategory.HARMFUL, 0x28283E));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}