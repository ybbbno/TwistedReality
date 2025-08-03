package me.deadybbb.twistedreality.registry

import me.deadybbb.twistedreality.TwistedReality
import me.deadybbb.twistedreality.registry.blocks.RiftBlock
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.util.Identifier

object TwistedRealityBlocks {
    @JvmStatic
    val BLOCKS: MutableMap<Identifier, Block> = HashMap()

    @JvmStatic
    val RIFT: RiftBlock = this.register(
        Identifier.tryParse(TwistedReality.Companion.MOD_ID, "rift_block"),
        RiftBlock(AbstractBlock.Settings.create().noCollision().nonOpaque().strength(-1.0f, 3600000.0f))
    ) as RiftBlock

    @JvmStatic
    private fun register(resource: Identifier?, block: Block): Block? {
        resource ?: return null
        BLOCKS.put(resource, block)
        return block
    }
}
