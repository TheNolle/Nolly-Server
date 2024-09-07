package com.thenolle.kotlin.commands

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.exception.ArgumentSyntaxException

/**
 * Abstract class for commands in the custom Minecraft server.
 *
 * This class allows easy creation of commands by specifying the name, aliases, and optional arguments.
 * The commands are registered to server command system with optional argument parsing.
 *
 * @param name The primary name of the command.
 * @param aliases A variable number of aliases for the command.
 *
 * @see Command
 * @see CommandSender
 * @see CommandContext
 */
abstract class AbstractCommand(name: String, vararg aliases: String) : Command(name, *aliases) {

	init {
		// Set default executor if no arguments are provided
		setDefaultExecutor { sender, context ->
			execute(sender, context)
		}

		// Set execution logic when arguments are provided (if needed)
		setupArguments()
	}

	/**
	 * Executes the command logic when the command is called without arguments or the default case.
	 *
	 * @param sender The entity or console that sent the command.
	 * @param context The context containing the command arguments.
	 */
	abstract fun execute(sender: CommandSender, context: CommandContext)

	/**
	 * Setup command arguments for more complex commands that require parameters.
	 *
	 * Override this method to define and register arguments for the command.
	 * By default, this method does nothing and can be left empty if no arguments are needed.
	 */
	open fun setupArguments() {
		// Override to add arguments in specific commands
	}

	/**
	 * Adds argument to the command with execution logic.
	 *
	 * This method allows registering arguments and their specific execution logic. The argument must be
	 * provided when calling the command.
	 *
	 * @param argument The argument to be registered for the command.
	 * @param execution The logic to be executed when the argument is provided.
	 */
	fun addArgumentExecution(argument: Argument<*>, execution: (sender: CommandSender, context: CommandContext) -> Unit) {
		addSyntax({ sender, context ->
			try {
				execution(sender, context)
			} catch (e: ArgumentSyntaxException) {
				sender.sendMessage("Invalid argument: ${e.input} - ${e.message}")
			}
		}, argument)
	}
}
