package com.thenolle.kotlin.commands

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import net.minestom.server.network.ConnectionManager

/**
 * A simple command that greets the player.
 *
 * @param connectionManager The connection manager to use for the command
 *
 * @see AbstractCommand
 */
class GreetCommand(private val connectionManager: ConnectionManager) : AbstractCommand("greet") {

	override fun setupArguments() {
		// Define an argument for the player's name
		val playerNameArgument = ArgumentType.String("playerName")

		// Add the argument and its execution logic
		addArgumentExecution(playerNameArgument) { sender, context ->
			val playerName = context.get(playerNameArgument)
			val player = connectionManager.onlinePlayers.find { it.username.equals(playerName, ignoreCase = true) }

			if (player != null) {
				player.sendMessage("Greetings from ${(sender as Player).username}!")
				sender.sendMessage("Greeted ${player.username}.")
			} else {
				sender.sendMessage("Player not found.")
			}
		}
	}

	override fun execute(sender: CommandSender, context: CommandContext) {
		sender.sendMessage("Greetings!")
	}
}
