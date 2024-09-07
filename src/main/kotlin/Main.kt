package com.thenolle.kotlin

import com.thenolle.kotlin.commands.*
import com.thenolle.kotlin.core.*
import com.thenolle.kotlin.events.*
import net.minestom.server.MinecraftServer

fun main() {
	// Server
	val minecraftServer = MinecraftServer.init()

	// Managers
	val instanceManager = MinecraftServer.getInstanceManager()
	val commandManager = MinecraftServer.getCommandManager()
	val connectionManager = MinecraftServer.getConnectionManager()
	val schedulerManager = MinecraftServer.getSchedulerManager()

	// Containers
	val instanceContainer = instanceManager.createInstanceContainer()
	val serverInstance = Server(minecraftServer, instanceManager, instanceContainer, schedulerManager)
	val globalEventHandler = MinecraftServer.getGlobalEventHandler()

	// Commands
	commandManager.register(HelloCommand())
	commandManager.register(GreetCommand(connectionManager))
	commandManager.register(StopCommand(serverInstance, connectionManager))

	// Events
	BlockEvents(globalEventHandler)
	ItemEvents(globalEventHandler)
	PlayerEvents(globalEventHandler, instanceContainer)
	ServerEvents(globalEventHandler)

	// World
	val world = World(instanceContainer)

	// Start the server
	serverInstance.start()
}