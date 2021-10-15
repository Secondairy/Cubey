package me.secondairy.cubey.mixins;

import net.minecraft.world.gen.feature.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetherFortressFeature.Start.class)
public class NetherFortressFeatureMixin {
    @Inject(at = @At("HEAD"), method = "init(Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;IILnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/DefaultFeatureConfig;)V", cancellable = true)
    private void init(CallbackInfo ci) {
        ci.cancel();
    }
}
