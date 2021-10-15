package me.secondairy.cubey.mixins;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import me.secondairy.cubey.Cubey;
import net.minecraft.server.command.LocateCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

import static net.minecraft.server.command.LocateCommand.sendCoordinates;
import static net.minecraft.world.gen.feature.StructureFeature.FORTRESS;

@Mixin(LocateCommand.class)
public class LocateCommandMixin {
    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType((Message)new TranslatableText("commands.locate.failed"));

    @Inject(at = @At("HEAD"), method = "execute", cancellable = true)
    private static void execute(ServerCommandSource source, StructureFeature<?> structureFeature, CallbackInfoReturnable<Integer> cir) throws CommandSyntaxException {
        if (Objects.equals(structureFeature.getName(), "fortress")) {
            throw FAILED_EXCEPTION.create();
        }
        else {
            BlockPos blockPos = new BlockPos(source.getPosition());
            BlockPos blockPos2 = source.getWorld().locateStructure(structureFeature, blockPos, 100, false);
            if (blockPos2 == null) {
                throw FAILED_EXCEPTION.create();
            }
            cir.setReturnValue(sendCoordinates(source, structureFeature.getName(), blockPos, blockPos2, "commands.locate.success"));
        }
        cir.cancel();
    }
}
