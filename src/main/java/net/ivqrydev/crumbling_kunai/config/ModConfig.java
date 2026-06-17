package net.ivqrydev.crumbling_kunai.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.DoubleValue ARMOR_REDUCTION;
    public static final ModConfigSpec.IntValue CRUMBLING_DURATION;
    public static final ModConfigSpec.BooleanValue CRUMBLING_PARTICLES;
    public static final ModConfigSpec.BooleanValue REFRESH_SOUND;

    static {
        BUILDER.comment("Crumbling Kunai Configuration");

        ARMOR_REDUCTION = BUILDER
                .comment("What is the percentage of armor points crumbling should decrease?")
                .defineInRange("armorReduction", 0.5, 0.0, 1.0);

        CRUMBLING_DURATION = BUILDER
                .comment("How long should the kunai apply crumbling for?")
                .defineInRange("crumblingDuration", 10, 1, 100);

        CRUMBLING_PARTICLES = BUILDER
                .comment("Should crumbling show particles?")
                .define("showParticles", false);

        REFRESH_SOUND = BUILDER
                .comment("Should a sound play when the crumbling effect wears off?")
                .define("refreshSound", true);

        SPEC = BUILDER.build();
    }
}