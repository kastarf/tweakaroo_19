package fi.dy.masa.tweakeroo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import fi.dy.masa.tweakeroo.util.IItemStackLimit;

@Mixin(ItemStack.class)
public abstract class MixinItemStack
{
    @Shadow
    public abstract Item getItem();

    @Inject(method = "getMaxCount", at = @At("HEAD"), cancellable = true)
    public void getMaxStackSizeStackSensitive(CallbackInfoReturnable<Integer> ci)
    {
        ci.setReturnValue(((IItemStackLimit) this.getItem()).getMaxStackSize((ItemStack) (Object) this));
    }
}
