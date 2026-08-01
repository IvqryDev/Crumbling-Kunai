package net.ivqrydev.crumbling_kunai.event;

import net.ivqrydev.crumbling_kunai.config.CrumblingKunaiConfig;
import net.ivqrydev.crumbling_kunai.effect.ModEffects;
import net.ivqrydev.crumbling_kunai.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = "crumbling_kunai")
public class KunaiCrumblingHandler {

    //Start tracking UUID of antagonistic players.
    private static final Map<UUID, UUID> KUNAI_SHOOTERS = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {
        Entity attacker = event.getSource().getDirectEntity();
        if (attacker == null || !inflictsCrumbling(attacker)) return;

        LivingEntity target = event.getEntity();
        Level level = target.level();
        if (level.isClientSide()) return;

        target.addEffect(new MobEffectInstance(
                ModEffects.CRUMBLING_EFFECT,
                CrumblingKunaiConfig.CRUMBLING_DURATION.get() * 20,
                0,
                false,
                false,
                CrumblingKunaiConfig.CRUMBLING_ICON.get()
        ));

        if (attacker instanceof Projectile projectile && projectile.getOwner() instanceof Player shooter) {
            KUNAI_SHOOTERS.put(target.getUUID(), shooter.getUUID());
        } else {
            KUNAI_SHOOTERS.remove(target.getUUID());
        }
    }

    //Check attacker first, so "minecraft:pillager" would fire an arrow that would inflict crumbling.
    private static boolean inflictsCrumbling(Entity attacker) {
        if (matchesConfiguredList(attacker)) return true;
        if (attacker instanceof Projectile projectile && projectile.getOwner() != null) {
            return matchesConfiguredList(projectile.getOwner());
        }
        return false;
    }

    private static boolean matchesConfiguredList(Entity entity) {
        ResourceLocation typeId = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
        return CrumblingKunaiConfig.CRUMBLING_ENTITIES.get().contains(typeId.toString());
    }

    @SubscribeEvent
    public static void onEffectExpire(MobEffectEvent.Expired event) {
        MobEffectInstance effectInstance = event.getEffectInstance();
        if (!CrumblingKunaiConfig.REFRESH_SOUND.get()) return;
        if (effectInstance == null || !effectInstance.is(ModEffects.CRUMBLING_EFFECT)) return;

        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (level.isClientSide()) return;

        //Fetch config options and play refresh sound effect at the player's position.
        UUID shooterId = KUNAI_SHOOTERS.remove(entity.getUUID());
        Player shooter = shooterId != null ? level.getPlayerByUUID(shooterId) : null;

        //If the player is null for whatever reason, like if they log out, fall back onto the enemy's position.
        BlockPos soundPos      = shooter != null ? shooter.blockPosition() : entity.blockPosition();
        SoundSource soundCategory = shooter != null ? SoundSource.PLAYERS  : entity.getSoundSource();

        level.playSound(null, soundPos, ModSounds.REFRESH.value(), soundCategory, 1.0f, 1.0f);
    }
}