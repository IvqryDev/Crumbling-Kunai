package net.ivqrydev.crumbling_kunai.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, "crumbling_kunai");

    //Override limited "false" just means it will disable if you have minimal particles enabled.
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CRUMBLING_DUST =
            PARTICLE_TYPES.register("crumbling_dust", () -> new SimpleParticleType(false));
}