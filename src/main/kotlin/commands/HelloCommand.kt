package com.thenolle.kotlin.commands

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandContext

/**
 * A command that allows the player to say hello.
 *
 * @see AbstractCommand
 */
class HelloCommand : AbstractCommand("hello", "hi") {
	override fun execute(sender: CommandSender, context: CommandContext) {
		sender.sendMessage("Hello, World!")
	}
}
