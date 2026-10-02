package knnyght.modid.mixin.client;

import knnyght.modid.util.LivingEntityAccessor;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @Inject(
            method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V",
            at = @At("TAIL")
    )
    private void overrideFireStateAtTail(LivingEntity livingEntity, LivingEntityRenderState state, float tickDelta, CallbackInfo ci) {
        LivingEntityAccessor entityData = (LivingEntityAccessor) livingEntity;

        if (livingEntity.isOnFire() || livingEntity.isInLava()) {
            // Read directly from this specific mob's isolated tick-timer
            if (entityData.getFireDamageTimer() > 0) {
                state.onFire = true;
            } else {
                state.onFire = false;
            }
        } else {
            state.onFire = false;
        }
    }
}
