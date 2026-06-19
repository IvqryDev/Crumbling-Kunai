package net.ivqrydev.crumbling_kunai.effect;

import net.ivqrydev.crumbling_kunai.config.CrumblingKunaiConfig;
import net.ivqrydev.crumbling_kunai.particle.ModParticles;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class CrumblingEffect extends MobEffect {

    private static final ResourceLocation MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("crumbling_kunai", "effect.crumbling.armor");

    public CrumblingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (CrumblingKunaiConfig.CRUMBLING_PARTICLES.get() && entity.level() instanceof ServerLevel serverLevel) {
            double width = entity.getBbWidth();
            double height = entity.getBbHeight();

            serverLevel.sendParticles(
                    ModParticles.CRUMBLING_DUST.get(),
                    entity.getX(), entity.getY() + height * 0.5, entity.getZ(),
                    2,
                    width * 0.4, height * 0.3, width * 0.4,
                    0.0
            );
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        return tickCount % 5 == 0;
    }

    @Override
    public void addAttributeModifiers(AttributeMap attributeMap, int amplifier) {
        AttributeInstance instance = attributeMap.getInstance(Attributes.ARMOR);
        if (instance != null) {
            instance.removeModifier(MODIFIER_ID);
            instance.addOrReplacePermanentModifier(new AttributeModifier(
                    MODIFIER_ID,
                    -CrumblingKunaiConfig.ARMOR_REDUCTION.get(),
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ));
        }
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        AttributeInstance instance = attributeMap.getInstance(Attributes.ARMOR);
        if (instance != null) {
            instance.removeModifier(MODIFIER_ID);
        }
    }
}