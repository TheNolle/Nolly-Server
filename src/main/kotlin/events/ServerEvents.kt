package com.thenolle.kotlin.events

import com.thenolle.kotlin.config.ServerConfig
import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.event.GlobalEventHandler
import net.minestom.server.event.server.ServerListPingEvent
import java.util.Base64

/**
 * Handles player-related events.
 *
 * This class listens for player-related events and performs actions based on the event.
 *
 * @param globalEventHandler The global event handler for the server.
 */
class ServerEvents(
	private val globalEventHandler: GlobalEventHandler,
) {
	private val serverConfig: ServerConfig = ServerConfig()

	init {
		// Load configuration values
		val faviconName = serverConfig.getString("favicon-name") ?: "favicon.png"
		val maxPlayers = serverConfig.getInt("max-players") ?: 20
		val motd = serverConfig.getString("motd") ?: "A Custom Minecraft Server"
		val serverName = serverConfig.getString("server-name") ?: "Nolly's Server"

		this.handleServerListPingEvent(faviconName, maxPlayers, motd, serverName)
	}

	/**
	 * Handles the server list ping event.
	 *
	 * This method sets the server list ping response to include the server name,
	 * maximum players, and MOTD. It also sets the favicon for the server.
	 *
	 * @param faviconName The name of the favicon file.
	 * @param maxPlayers The maximum number of players allowed on the server.
	 * @param motd The message of the day for the server.
	 * @param serverName The name of the server.
	 */
	private fun handleServerListPingEvent(faviconName: String, maxPlayers: Int, motd: String, serverName: String) {
		this.globalEventHandler.addListener(ServerListPingEvent::class.java) { event ->
			val responseData = event.responseData
			// Set server Favicon
			responseData.favicon = favicon(faviconName)
			// Set server MOTD
			responseData.description = Component.text(motd)
			// Set server name
			responseData.version = serverName
			// Set max players
			responseData.online = MinecraftServer.getConnectionManager().onlinePlayers.size
			responseData.maxPlayer = maxPlayers
		}
	}

	/**
	 * Returns the favicon as a base64-encoded string.
	 * If the favicon is not found, null is returned.
	 *
	 * @param faviconName The name of the favicon file.
	 * @return The favicon as a base64-encoded string, or null if not found.
	 */
	private fun favicon(faviconName: String): String? {
		return null
		var favicon: String? = null
		try {
			javaClass.getResourceAsStream("/$faviconName")?.use { stream ->
				favicon = "data:image/png;base64," + Base64.getEncoder().encodeToString(stream.readBytes())
			}
		} catch (e: Exception) {
			e.printStackTrace()
		}
		return favicon
	}
}