package me.deadybbb.twistedreality.client.core.registry.renderers

import me.deadybbb.twistedreality.TwistedReality
import me.deadybbb.twistedreality.registry.blocks.RiftBlockEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.minecraft.util.math.RotationAxis

class RiftBlockEntityRenderer(private val ctx: BlockEntityRendererFactory.Context) :
    BlockEntityRenderer<RiftBlockEntity> {
    override fun render(
        entity: RiftBlockEntity?,
        tickDelta: Float,
        matrices: MatrixStack?,
        vertexConsumers: VertexConsumerProvider?,
        light: Int,
        overlay: Int
    ) {
        val client = MinecraftClient.getInstance()
        val world = entity?.world ?: return

        matrices?.push()

        // Центрирование
        matrices?.translate(0.5, 0.5, 0.5)

        val time = System.currentTimeMillis() / 20
        matrices?.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((time % 360).toFloat()))

        val itemStack =
            ItemStack(Registries.ITEM.get(Identifier.tryParse(TwistedReality.Companion.MOD_ID, "rift_model")))

        // Получение модели
        val modelId = Identifier.tryParse(TwistedReality.Companion.MOD_ID, "block/rift_model")
        val model = client.bakedModelManager.getModel(modelId)

        // Рендер модели
        if (model != null && model !== client.bakedModelManager.missingModel) {
            client.blockRenderManager.modelRenderer.render(
                world,
                model,
                entity.cachedState,
                entity.pos,
                matrices,
                vertexConsumers?.getBuffer(RenderLayer.getCutout()),
                false,
                world.random,
                0L,
                overlay
            )
        }


        matrices?.pop()
    }
}