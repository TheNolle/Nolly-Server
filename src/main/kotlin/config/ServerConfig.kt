package com.thenolle.kotlin.config

/**
 * ServerConfig is a configuration class for managing the Minecraft server properties.
 *
 * It uses the "server.properties" file located in the working directory and loads default values
 * from the "server.properties" file stored in the resources' folder.
 *
 * @see AbstractConfig
 */
class ServerConfig : AbstractConfig("server.properties") {
	/**
	 * Returns the name of the server configuration file.
	 *
	 * This file will be located in the working directory (e.g., "server.properties").
	 *
	 * @return The config file name.
	 */
	override fun getConfigFileName(): String = "server.properties"
}