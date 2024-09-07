package com.thenolle.kotlin.events

import net.minestom.server.coordinate.Pos
import net.minestom.server.event.GlobalEventHandler
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import net.minestom.server.instance.InstanceContainer

/**
 * Handles player-related events.
 *
 * This class listens for player-related events and performs actions based on the event.
 *
 * @param globalEventHandler The global event handler for the server.
 * @param instanceContainer The instance container for the server.
 */
class PlayerEvents(
	private val globalEventHandler: GlobalEventHandler,
	private val instanceContainer: InstanceContainer
) {
	init {
		this.handlePlayerJoinEvent()
	}

	/**
	 * Handles the player Join event.
	 *
	 * This method listens for the player join event and sets the player's respawn point to the
	 */
	private fun handlePlayerJoinEvent() {
		this.globalEventHandler.addListener(AsyncPlayerConfigurationEvent::class.java) { event ->
			val player = event.player
			event.spawningInstance = instanceContainer
			player.respawnPoint = Pos(0.0, 42.0, 0.0)
		}
	}
}