package me.deadybbb.twistedreality

import me.deadybbb.twistedreality.registry.TwistedRealityBlockEntities
import me.deadybbb.twistedreality.registry.TwistedRealityBlocks
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object TwistedRealityLoader {
    @JvmStatic
    fun loadBlocks() {
        TwistedRealityBlocks.BLOCKS.forEach { it -> Registry.register(Registries.BLOCK, it.key, it.value) }
    }

    @JvmStatic
    fun load() {
        loadBlocks()
        TwistedRealityBlockEntities.load()
    }
}