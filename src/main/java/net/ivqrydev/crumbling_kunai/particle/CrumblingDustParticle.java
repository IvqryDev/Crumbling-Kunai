package net.ivqrydev.crumbling_kunai.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class CrumblingDustParticle extends TextureSheetParticle {

    private final SpriteSet spriteSet;

    protected CrumblingDustParticle(ClientLevel level, double x, double y, double z, SpriteSet spriteSet) {
        super(level, x, y, z);
        this.spriteSet = spriteSet;


        this.xd = (this.random.nextDouble() - 0.5) * 0.02;
        this.yd = -0.01 - this.random.nextDouble() * 0.015;
        this.zd = (this.random.nextDouble() - 0.5) * 0.02;

        this.gravity = 0.275f; //Very little gravity.
        this.friction = 0.725f;  //Strong air resistance.
        this.hasPhysics = true; //no flaot :<
        this.quadSize *= 0.35f + this.random.nextFloat() * 0.25f; //Slight size variation.
        this.lifetime = 25 + this.random.nextInt(20); //Lingers and settles and doesn't just pop out of existence.

        this.setSpriteFromAge(spriteSet);
    }

    //Dust particle got that low taper fade.
    //Start fading once it's hit 70% of its lifetime and then disappear entirely.
    @Override
    public void tick() {
        this.setSpriteFromAge(spriteSet);
        super.tick();

        float lifeProgress = (float) this.age / (float) this.lifetime;
        float fadeStart = 0.7f;

        if (lifeProgress > fadeStart) {
            this.alpha = 1.0f - ((lifeProgress - fadeStart) / (1.0f - fadeStart));
        } else {
            this.alpha = 1.0f;
        }
    }


    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new CrumblingDustParticle(level, x, y, z, spriteSet);
        }
    }
}