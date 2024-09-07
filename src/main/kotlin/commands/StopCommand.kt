package com.thenolle.kotlin.commands

import com.thenolle.kotlin.core.Server
import net.minestom.server.command.CommandSender
import net.minestom.server.command.ConsoleSender
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.network.ConnectionManager

/**
 * A command that stops the server.
 *
 * @param serverInstance The server instance.
 * @param connectionManager The connection manager.
 *
 * @see AbstractCommand
 */
class StopCommand(private val serverInstance: Server, private val connectionManager: ConnectionManager) : AbstractCommand("stop", "exit", "quit", "end", "shutdown", "halt", "terminate") {
	override fun execute(sender: CommandSender, context: CommandContext) {
		if (!sender.hasPermission("nolly.command.stop") && !sender.hasPermission("nolly.command.admin") && sender !is ConsoleSender) {
			sender.sendMessage("You do not have permission to stop the server.")
			return
		}

		connectionManager.onlinePlayers.forEach { it.kick("Server is shutting down.") }
		serverInstance.stop()
	}
}