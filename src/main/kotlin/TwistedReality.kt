package me.deadybbb.twistedreality

import net.fabricmc.api.ModInitializer

class TwistedReality : ModInitializer {
    override fun onInitialize() {
        TwistedRealityLoader.load()
    }

    companion object {
        const val MOD_ID = "twistedreality"
    }
}