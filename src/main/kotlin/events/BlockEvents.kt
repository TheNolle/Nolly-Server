package com.thenolle.kotlin.events

import net.minestom.server.entity.ItemEntity
import net.minestom.server.event.GlobalEventHandler
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.item.ItemStack
import java.time.Duration

class BlockEvents(private val globalEventHandler: GlobalEventHandler) {
	init {
		this.handleBlockBreak()
	}

	/**
	 * Handle block break event
	 *
	 * @see net.minestom.server.event.player.PlayerBlockBreakEvent
	 */
	private fun handleBlockBreak() {
		globalEventHandler.addListener(PlayerBlockBreakEvent::class.java) { event ->
			val block = event.block
			val material = block.registry().material()
			if (material != null) {
				val itemStack = ItemStack.of(material)
				val itemEntity = ItemEntity(itemStack)
				fun randomDouble(min: Double, max: Double) = min + Math.random() * (max - min)
				itemEntity.setInstance(event.instance, event.blockPosition.add(randomDouble(0.1, 0.9), randomDouble(0.1, 0.9), randomDouble(0.1, 0.9)))
				itemEntity.setPickupDelay(Duration.ofMillis(500))
			}
		}
	}
}