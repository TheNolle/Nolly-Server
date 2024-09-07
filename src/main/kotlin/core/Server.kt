package com.thenolle.kotlin.core

import com.thenolle.kotlin.config.CoreConfig
import com.thenolle.kotlin.config.ServerConfig
import net.minestom.server.MinecraftServer
import net.minestom.server.extras.MojangAuth
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.instance.InstanceManager
import net.minestom.server.instance.LightingChunk
import net.minestom.server.timer.SchedulerManager
import net.minestom.server.utils.chunk.ChunkSupplier
import kotlin.system.exitProcess

/**
 * Server class that initializes and starts the Minecraft server.
 *
 * This class loads the configuration from the server.properties file and uses
 * the values to set up and start the Minecraft server.
 *
 * @param minecraftServer The Minecraft server instance.
 * @param instanceManager The instance manager for the server.
 * @param instanceContainer The instance container for the server.
 * @param schedulerManager The scheduler manager for the server.
 */
class Server(
	private val minecraftServer: MinecraftServer,
	private val instanceManager: InstanceManager,
	private val instanceContainer: InstanceContainer,
	private val schedulerManager: SchedulerManager
) {
	private val serverConfig: ServerConfig = ServerConfig()
	private val coreConfig: CoreConfig = CoreConfig()

	/**
	 * Initializes and starts the Minecraft server.
	 *
	 * The server configuration is loaded from the server.properties file, and various
	 * server settings (e.g., port, online mode) are set based on the configuration values.
	 */
	fun start() {
		// Load configuration values
		val host = serverConfig.getString("server-host") ?: "0.0.0.0"
		val onlineMode = serverConfig.getBoolean("online-mode")
		val port = serverConfig.getInt("server-port") ?: 25565

		// Enable online mode if specified in the configuration
		if (onlineMode) {
			println("Enabling online mode")
			MojangAuth.init()
		}

		// Add Lighting to chunks
		instanceContainer.chunkSupplier = ChunkSupplier { instance, chunkX, chunkZ ->
			LightingChunk(instance, chunkX, chunkZ)
		}

		// Start the server
		minecraftServer.start(host, port)
		println("Server started on $host:$port")

		// Stop Hook
		schedulerManager.buildShutdownTask {
			println("Stopping server...")
			if (coreConfig.getBoolean("chunk-saving")) {
				println("Saving chunks to storage...")
				instanceManager.instances.forEach { it.saveChunksToStorage() }
				println("Chunks saved")
			}
		}
	}

	/**
	 * Stops the Minecraft server.
	 */
	fun stop() {
		MinecraftServer.stopCleanly()
		exitProcess(0)
	}
}