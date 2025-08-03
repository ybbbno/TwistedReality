package me.deadybbb.twistedreality.registry

import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

class RegistryHelper {

    fun <BE: BlockEntity> registerBlockEntity(
        factory: BlockEntityType.BlockEntityFactory<BE>,
        resource: Identifier?,
        vararg blocks: Block
    ) : BlockEntityType<BE>? {
        resource ?: return null

        val mojangFactory = BlockEntityType.BlockEntityFactory<BE> { pos, state -> factory.create(pos, state) }

        val builder: BlockEntityType.Builder<BE> = BlockEntityType.Builder.create(mojangFactory, *blocks)

        val blockEntityType: BlockEntityType<BE> = builder.build(null)

        Registry.register(
            Registries.BLOCK_ENTITY_TYPE, resource, blockEntityType
        )

        TwistedRealityBlockEntities.BLOCK_ENTITIES[resource] = blockEntityType

        return blockEntityType
    }
}