package com.thenolle.kotlin.config

/**
 * CoreConfig is a configuration class for managing the Software's core properties.
 *
 * This class extends the AbstractConfig class, which provides the basic functionality for
 * reading and writing properties files.
 *
 * @see AbstractConfig
 */
class CoreConfig : AbstractConfig("core.properties") {
	/**
	 * Returns the name of the core configuration file.
	 *
	 * This file will be located in the working directory (e.g., "core.properties").
	 *
	 * @return The config file name.
	 */
	override fun getConfigFileName(): String = "core.properties"
}