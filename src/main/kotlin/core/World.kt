package com.thenolle.kotlin.core

import net.minestom.server.instance.InstanceContainer
import net.minestom.server.instance.block.Block

/**
 * Represents the world of the Minecraft server.
 *
 * @param instanceContainer The instance container for the server.
 */
class World(private val instanceContainer: InstanceContainer) {
	/**
	 * Initializes the world.
	 */
	init {
		instanceContainer.setGenerator { unit ->
			unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK)
		}
	}
}