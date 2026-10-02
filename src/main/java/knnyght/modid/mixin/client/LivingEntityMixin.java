package knnyght.modid.mixin.client;

import knnyght.modid.util.LivingEntityAccessor;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements LivingEntityAccessor {

    @Unique
    private int fireDamageTimer = 0;

    @Override
    public int getFireDamageTimer() {
        return this.fireDamageTimer;
    }

    @Override
    public void setFireDamageTimer(int ticks) {
        this.fireDamageTimer = ticks;
    }

    // This method executes naturally once per game tick for EVERY individual mob
    @Inject(method = "tick", at = @At("HEAD"))
    private void onEntityTick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        // Only run this countdown on the client side where the packets are caught
        if (entity.getEntityWorld().isClient() && this.fireDamageTimer > 0) {
            this.fireDamageTimer--;
        }
    }
}
