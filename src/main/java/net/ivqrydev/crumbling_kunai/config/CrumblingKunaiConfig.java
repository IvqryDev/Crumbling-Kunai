package net.ivqrydev.crumbling_kunai.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class CrumblingKunaiConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.ConfigValue<List<? extends String>> CRUMBLING_ENTITIES;
    public static final ModConfigSpec.DoubleValue ARMOR_REDUCTION;
    public static final ModConfigSpec.IntValue CRUMBLING_DURATION;
    public static final ModConfigSpec.BooleanValue CRUMBLING_PARTICLES;
    public static final ModConfigSpec.BooleanValue CRUMBLING_ICON;
    public static final ModConfigSpec.BooleanValue REFRESH_SOUND;

    static {
        BUILDER.comment("Crumbling Kunai Configuration");

        CRUMBLING_ENTITIES = BUILDER
                .comment("Which entities inflict crumbling when they hit the player or another entity? (This also accepts projectile entities.)")
                .defineList("crumblingEntities", List.of("caverns_and_chasms:kunai"), obj -> obj instanceof String);

        ARMOR_REDUCTION = BUILDER
                .comment("What is the percentage of armor points crumbling should decrease? (Will always round up.)")
                .defineInRange("armorReduction", 0.5, 0.0, 1.0);

        CRUMBLING_DURATION = BUILDER
                .comment("How long should crumbling be applied for? (Measured in seconds.)")
                .defineInRange("crumblingDuration", 10, 1, 100);

        CRUMBLING_PARTICLES = BUILDER
                .comment("Should crumbling show particles?")
                .define("showParticles", true);

        CRUMBLING_ICON = BUILDER
                .comment("Should crumbling show a status icon?")
                .define("showIcon", true);

        REFRESH_SOUND = BUILDER
                .comment("Should a sound play when the crumbling effect wears off?")
                .define("refreshSound", true);

        SPEC = BUILDER.build();
    }
}