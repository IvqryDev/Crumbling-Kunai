package net.ivqrydev.crumbling_kunai.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.DoubleValue ARMOR_REDUCTION;

    static {
        BUILDER.comment("Crumbling Kunai Configuration");

        ARMOR_REDUCTION = BUILDER
                .comment("What percent should crumbling reduce your defense by?")
                .defineInRange("armorReduction", 0.5, 0.0, 1.0);

        SPEC = BUILDER.build();
    }
}