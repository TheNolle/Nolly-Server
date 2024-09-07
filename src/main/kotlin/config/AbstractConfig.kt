package com.thenolle.kotlin.config

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.Properties
import kotlin.system.exitProcess

/**
 * AbstractConfig is a base class designed to manage configuration files.
 *
 * This class provides mechanisms to load, save, and reset configuration files. It allows loading default
 * values from the resources folder and saving them to a working directory. Specific configuration types
 * (e.g., Minecraft server properties) can extend this class to handle their own specific config files.
 *
 * @property defaultPropertiesFileName The name of the default properties file located in the resources folder.
 * @property properties The properties object used to store configuration key-value pairs.
 * @property configFile The file where the configuration is saved and loaded from.
 */
abstract class AbstractConfig(private val defaultPropertiesFileName: String) {
	protected var properties: Properties = Properties()
	protected lateinit var configFile: File

	/**
	 * Initializes the configuration by ensuring the file exists, loading it,
	 * or creating a new one with default values from the resources folder.
	 */
	init {
		this.configFile = this.getOrCreateConfigFile()
		this.loadConfig()
	}

	/**
	 * Abstract method to get the configuration file name.
	 *
	 * Each subclass must define its configuration file name (e.g., "server.properties").
	 *
	 * @return The name of the config file (used in the working directory).
	 */
	protected abstract fun getConfigFileName(): String

	/**
	 * Ensures the configuration file exists in the working directory.
	 *
	 * If the file does not exist, it is created by copying the default properties from the resources
	 * folder into the working directory. The default file is used as a template, but the working config
	 * file is where changes are made.
	 *
	 * @return The File object representing the working configuration file.
	 */
	private fun getOrCreateConfigFile(): File {
		val configFile = File(getConfigFileName())
		if (!configFile.exists()) {
			println("Configuration file not found. Creating a new one with default values from resources...")
			try {
				// Copy default properties from resources to the working directory
				val resourceStream = javaClass.classLoader.getResourceAsStream(defaultPropertiesFileName)
				if (resourceStream != null) {
					Files.copy(resourceStream, configFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
				} else {
					println("Error: Default configuration file not found in resources.")
					exitProcess(1)
				}
			} catch (e: IOException) {
				println("Error creating configuration file: ${e.message}")
				exitProcess(1)
			}
		}
		return configFile
	}

	/**
	 * Loads the configuration from the working config file.
	 *
	 * Reads the configuration from the specified file and populates the properties object.
	 */
	protected fun loadConfig() {
		try {
			this.configFile.inputStream().use {
				this.properties.load(it)
			}
		} catch (e: IOException) {
			println("Error loading configuration file: ${e.message}")
			exitProcess(1)
		}
	}

	/**
	 * Saves the current configuration to the working config file.
	 *
	 * This method saves the in-memory properties to the config file, ensuring that changes
	 * are persisted. If the saving fails, a temporary file is created to avoid corruption.
	 */
	fun saveConfig() {
		val tempFile = File("${this.configFile.name}.tmp")
		try {
			this.properties.store(tempFile.outputStream(), null)
			Files.move(tempFile.toPath(), this.configFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
			println("Configuration saved successfully.")
		} catch (e: IOException) {
			println("Error saving configuration: ${e.message}")
			tempFile.delete() // Clean up temp file if there's an issue
		}
	}

	/**
	 * Resets the configuration to its default values.
	 *
	 * This method overwrites the current working config file with the default properties file
	 * from the resources folder, effectively resetting the configuration to its original state.
	 */
	fun resetToDefault() {
		try {
			val resourceStream = javaClass.classLoader.getResourceAsStream(defaultPropertiesFileName)
			if (resourceStream != null) {
				Files.copy(resourceStream, this.configFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
				this.loadConfig()
				println("Configuration reset to default values.")
			} else {
				println("Error: Default configuration file not found in resources.")
			}
		} catch (e: IOException) {
			println("Error resetting configuration file: ${e.message}")
		}
	}

	/**
	 * Prints the current configuration values to the console.
	 *
	 * This method is useful for debugging or inspecting the current configuration settings.
	 */
	fun printConfig() {
		println("Current configuration:")
		this.properties.forEach { (key, value) ->
			println("$key = $value")
		}
	}

	/**
	 * Retrieves a property value as a string.
	 *
	 * @param key The key of the property to retrieve.
	 * @return The value associated with the key, or null if the key does not exist.
	 */
	fun getString(key: String): String? = properties.getProperty(key)

	/**
	 * Retrieves a property value as an integer.
	 *
	 * @param key The key of the property to retrieve.
	 * @return The integer value associated with the key, or null if the key does not exist or is invalid.
	 */
	fun getInt(key: String): Int? = getString(key)?.toIntOrNull()

	/**
	 * Retrieves a property value as a boolean.
	 *
	 * @param key The key of the property to retrieve.
	 * @return The boolean value associated with the key, or false if the key does not exist or is invalid.
	 */
	fun getBoolean(key: String): Boolean = getString(key)?.toBoolean() == true

	/**
	 * Sets a property value and saves the configuration.
	 *
	 * @param key The key of the property to update.
	 * @param value The new value to set for the key.
	 */
	fun setProperty(key: String, value: String) {
		this.properties.setProperty(key, value)
		this.saveConfig()
	}
}