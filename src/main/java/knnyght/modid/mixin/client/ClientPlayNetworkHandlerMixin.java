package knnyght.modid.mixin.client;

import knnyght.modid.util.LivingEntityAccessor;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "onEntityDamage", at = @At("HEAD"))
    private void catchTrueDamageType(EntityDamageS2CPacket packet, CallbackInfo ci) {
        ClientPlayNetworkHandler handler = (ClientPlayNetworkHandler) (Object) this;

        if (handler.getWorld() == null) return;

        Entity entity = handler.getWorld().getEntityById(packet.entityId());

        if (entity instanceof LivingEntity livingEntity) {
            if (packet.sourceType() != null && packet.sourceType().getKey().isPresent()) {
                RegistryKey<DamageType> damageKey = packet.sourceType().getKey().get();

                if (damageKey == DamageTypes.IN_FIRE ||
                        damageKey == DamageTypes.CAMPFIRE ||
                        damageKey == DamageTypes.ON_FIRE ||
                        damageKey == DamageTypes.LAVA ||
                        damageKey == DamageTypes.HOT_FLOOR) {

                    // Give it 25 ticks of visual fire life
                    ((LivingEntityAccessor) livingEntity).setFireDamageTimer(25);
                }

            }
        }
    }
}
