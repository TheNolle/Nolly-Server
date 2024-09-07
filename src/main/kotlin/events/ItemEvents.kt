package com.thenolle.kotlin.events

import net.minestom.server.entity.ItemEntity
import net.minestom.server.entity.Player
import net.minestom.server.event.GlobalEventHandler
import net.minestom.server.event.item.ItemDropEvent
import net.minestom.server.event.item.PickupItemEvent
import net.minestom.server.item.ItemStack
import java.time.Duration

class ItemEvents(private val globalEventHandler: GlobalEventHandler) {
	init {
		this.handleItemPickup()
		this.handleItemDrop()
	}

	/**
	 * Handle the pickup of an item.
	 *
	 * @see net.minestom.server.event.item.PickupItemEvent
	 */
	private fun handleItemPickup() {
		globalEventHandler.addListener(PickupItemEvent::class.java) { event ->
			val player = event.livingEntity
			if (player is Player) {
				val itemStack = event.itemStack
				player.inventory.addItemStack(itemStack)
			}
		}
	}

	/**
	 * Handle the drop of an item.
	 *
	 * @see net.minestom.server.event.item.ItemDropEvent
	 */
	private fun handleItemDrop() {
		globalEventHandler.addListener(ItemDropEvent::class.java) { event ->
			val itemStack = event.itemStack
			val itemEntity = ItemEntity(itemStack)
			val player = event.player
			itemEntity.setInstance(player.instance, player.position)
			itemEntity.velocity = player.position.direction().normalize().mul(0.5)
			itemEntity.setPickupDelay(Duration.ofMillis(500))
		}
	}
}