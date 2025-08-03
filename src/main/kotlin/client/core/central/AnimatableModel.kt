package me.deadybbb.twistedreality.client.core.registry.central

import me.deadybbb.twistedreality.client.core.central.Animatable
import me.deadybbb.twistedreality.client.core.central.AnimationInstance
import net.minecraft.util.Identifier
import net.minecraft.client.model.Model
import net.minecraft.client.model.ModelPart
import net.minecraft.client.render.entity.animation.Animation
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.animation.AnimationHelper
import net.minecraft.client.util.math.MatrixStack
import org.joml.Vector3f
import java.util.Optional

/*
* Абстрактный класс модели с поддержкой анимации
*/
abstract class AnimatableModel(function: (Identifier) -> RenderLayer = { id: Identifier -> RenderLayer.getSolid() }) : Model(function) {

    protected val animations: MutableMap<String, Animation> = HashMap()

    companion object {
        private val ANIMATION_VECTOR_CACHE = Vector3f()
    }

    abstract fun root(): ModelPart

    /* Поиск части модели по имени */
    fun getAnyDescendantWithName(name: String): ModelPart? {
        val root = this.root()
        if (name == "root") {
            return root
        } else {
            return root.getChild(name)
        }
    }

    /* Применение анимации к модели */
    protected fun animate(animation: Animation, runningTime: Long, scale: Float) {
        AnimationHelper.animate(
            this as net.minecraft.client.render.entity.model.SinglePartEntityModel<*>,
            animation,
            runningTime,
            scale,
            ANIMATION_VECTOR_CACHE
        )
    }

    /* Метод настройки анимации модели */
    fun setupAnim(animatable: Animatable, ageInTicks: Float) {
        this.root().resetTransform()

        for ((animationName, animation) in animations) {
            val runningTime: Long = animatable.getTime(animationName)
            val timeInMs: Long = (ageInTicks * 50.0f).toLong()
            animate(animation, timeInMs, 1.0f)
        }
    }

    /* Регистрирует новую анимацию для этой модели */
    fun allocate(name: String, animation: Animation) {
        animations[name] = animation
    }

    abstract override fun render(
        matrices: MatrixStack?,
        vertices: VertexConsumer?,
        light: Int,
        overlay: Int,
        color: Int
    )
}