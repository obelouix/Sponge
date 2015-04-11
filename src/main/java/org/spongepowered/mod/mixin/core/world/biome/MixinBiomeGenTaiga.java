/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered.org <http://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.mod.mixin.core.world.biome;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenTaiga;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.mod.world.gen.populators.BlockBlobPopulator;
import org.spongepowered.mod.world.gen.populators.DoublePlantPopulator;

@Mixin(BiomeGenTaiga.class)
public abstract class MixinBiomeGenTaiga extends MixinBiomeGenBase {

    @Inject(method = "<init>(II)V", at = @At("RETURN"))
    public void onConstructed(int id, int type, CallbackInfo ci) {
        if (this.populators == null) {
            populators = Lists.newArrayList();
        }
        if (type == 1 || type == 2) {
            this.populators.add(new BlockBlobPopulator(Blocks.cobblestone.getDefaultState(), 3, 0));
        }
        this.populators.add(new DoublePlantPopulator(7, 1, 1, BlockDoublePlant.EnumPlantType.FERN));
        super.buildPopulators(false);
    }
}