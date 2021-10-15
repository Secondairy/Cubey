package me.secondairy.cubey.mixins;

import me.secondairy.cubey.Cubey;
import net.minecraft.world.gen.feature.BastionRemnantFeatureConfig;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(BastionRemnantFeatureConfig.class)
public class BastionRemnantFeatureConfigMixin {

    @Shadow @Final private List<StructurePoolFeatureConfig> possibleConfigs;

    @Inject(at = @At("HEAD"), method = "getRandom", cancellable = true)
    private void getRandom(Random random, CallbackInfoReturnable<StructurePoolFeatureConfig> cir) {
        StructurePoolFeatureConfig treasure = this.possibleConfigs.get(2);
        cir.setReturnValue(treasure);
        cir.cancel();
    }
}
