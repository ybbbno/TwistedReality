package me.deadybbb.twistedreality.client.core.central

import net.minecraft.world.level.LevelProperties

interface Animatable {
    fun getStates(): MutableMap<String, AnimationInstance>
    fun getTime(): Float
    fun setTime(time: Float)

    fun allocate(id: String) {
        getStates()[id] = AnimationInstance()
    }

    fun getTime(id: String): Long {
        return 1L
    }

    fun getAnim(id: String): AnimationInstance {
        return getStates()[id] ?: AnimationInstance().also { getStates()[id] = it }
    }

    fun play(id: String, speed: Float): AnimationInstance {
        val instance = getAnim(id)
        // Здесь должнап быть логика воспроизведения анимации
        return instance
    }

    fun tick(level: LevelProperties) {
        // Логика тика на клиентской стороне
        level.playerData ?: return
    }
}