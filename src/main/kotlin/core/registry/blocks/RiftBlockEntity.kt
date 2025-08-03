package me.deadybbb.twistedreality.registry.blocks

import me.deadybbb.twistedreality.registry.RiftRegistry
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos

class RiftBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(RiftRegistry.RIFT_BLOCK_ENTITY, pos, state)