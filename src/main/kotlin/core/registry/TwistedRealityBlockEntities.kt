package me.deadybbb.twistedreality.registry

import me.deadybbb.twistedreality.TwistedRealityServices
import me.deadybbb.twistedreality.TwistedReality
import me.deadybbb.twistedreality.registry.blocks.*
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos

object TwistedRealityBlockEntities {
    @JvmStatic
    val BLOCK_ENTITIES: MutableMap<Identifier, BlockEntityType<*>> = HashMap()

    @JvmStatic
    val RIFT: BlockEntityType<RiftBlockEntity>? = TwistedRealityServices.REGISTRY.registerBlockEntity(
        factory = { pos: BlockPos, state: BlockState -> RiftBlockEntity(pos, state) },
        resource = Identifier.tryParse(TwistedReality.Companion.MOD_ID, "rift_block_entity"),
        blocks = arrayOf(TwistedRealityBlocks.RIFT)
    )

    @JvmStatic
    fun load() {}
}