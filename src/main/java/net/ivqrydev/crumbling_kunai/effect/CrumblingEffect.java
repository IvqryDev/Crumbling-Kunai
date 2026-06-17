package net.ivqrydev.crumbling_kunai.effect;

import net.ivqrydev.crumbling_kunai.config.ModConfig;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.resources.ResourceLocation;

public class CrumblingEffect extends MobEffect {

    private static final ResourceLocation MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("crumbling_kunai", "effect.crumbling.armor");

    public CrumblingEffect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(
                Attributes.ARMOR,
                MODIFIER_ID,
                -ModConfig.ARMOR_REDUCTION.get(),
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
}