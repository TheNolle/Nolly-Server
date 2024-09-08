# Nolly's Server

**🚀 Nolly's Server** is a fully customizable Minecraft server built from the ground up
using [Minestom](https://github.com/Minestom/Minestom), an open-source Minecraft server platform. Unlike traditional
Minecraft servers, Nolly's Server gives you complete control over every aspect of the server. By starting from near
zero, you can add only the features and game mechanics you need—nothing more, nothing less.

With full Kotlin support and the latest Minecraft versions, you have the freedom to write everything yourself, from
basic commands to complex gameplay mechanics, giving you total creative control over your server experience.
> **"You manage what you want, how you want."**


## 🌟 Features
- **Customizable from Scratch:** Minestom provides the base, and you can build everything else. No unnecessary features—only what you implement.
- **Kotlin-Powered:** Full Kotlin support to leverage modern language features and expressive syntax for building your server.
- **Up-to-Date:** Always running the latest version of Minecraft, ensuring that you can use the latest game mechanics and blocks.
- **Extensible:** Perfect for custom SMP servers, mini-games, or specialized game modes. If you didn’t code it, it’s not part of the server.
- **Open-Source:** Free to use, modify, and distribute. Share your server with the world, or keep it private for your friends.


## 🛠️ Built With
- [Minestom](https://minestom.net/) snapshots (commit `11ed85a921`) - The Minecraft server platform that powers the project.
- [Kotlin](https://kotlinlang.org/) `2.0.10` - A modern, expressive, and statically-typed programming language.
- [JUnit](https://junit.org/) `5.7.2` - For testing and ensuring the server's stability.
- [SLF4J](http://slf4j.org/) `2.0.16` - Simple Logging Facade for Java.


## 🚀 Getting Started

### Prerequisites
Ensure that you have the following software installed:
- **JDK 21:** The project uses Java 21 for compatibility with Kotlin and Minestom. You can download
  it [here](https://adoptium.net/).
- **Gradle:** To manage dependencies and build the project. Install it from [here](https://gradle.org/).

### Installation
1. **Clone the repository:**
```bash
git clone https://github.com/thenolle/nolly-server.git
cd nolly-server
```
2. **Build the project:** Use Gradle to build the server jar:
```bash
./gradlew shadowJar
```
The jar file will be generated in the `build/libs` folder.
3. **Run the server:** To start the server:
```bash
java -jar build/libs/nolly-server-<version>.jar
```
4. **Command Line Example:** For basic commands, you can implement them easily by extending `AbstractCommand` and adding them to the command manager. For example:
```kotlin
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
```

### Configuration
You can customize the server settings through a `server.properties` file, which can be modified manually or programmatically through Kotlin. The configuration system uses an abstraction layer that allows easy management of properties, automatic file creation, and loading from the 'resources' folder.


## 📦 GitHub Actions and Releases
This project uses GitHub Actions to automate building and releasing the server:
- **On every push** to the master branch, the server is built using `Gradle shadowJar`.
- **On commits starting with** `[New Version] <jar-name>.jar | <description>`, the build is automatically packaged and uploaded to the releases page. The version is detected from the jar name, and the release is tagged with the format `v<version>`.

### Example Workflow
A commit like this:
```plaintext
[New Version] nolly-server-v1.0.3.jar | Initial release with basic features
```
Would automatically create a GitHub release:
- **Release Name:** `nolly server v1.0.3`
- **Tag:** `v1.0.3`
- **Description:** `Initial release with basic features`

You can view the latest releases on the [Releases Page](https://github.com/thenolle/nolly-server/releases).


## 🤝 Contributing
Contributions are welcome! To contribute:
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin feature/my-new-feature`)
5. Open a pull request

For more detailed guidelines, see [CONTRIBUTING.md](CONTRIBUTING.md).


## 📄 Code of Conduct
By participating in this project, you agree to abide by the [Code of Conduct](Code%20of%20Conduct.md).


## 📝 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


## 🌟 Acknowledgments
Special thanks to:
- The [Minestom](https://minestom.net/) team for creating the core platform.
- The [Kotlin](https://kotlinlang.org/) community for making server-side programming enjoyable and productive.
- Anyone contributing to the open-source ecosystem.

---

## 📫 Contact
For any questions or feedback, feel free to reach out:
- **Email:** [nolly.berrebi@gmail.com](mailto:nolly.berrebi@gmail.com)
- **Discord:** [nolly__](https://discord.com/users/1030561407411966064)

## 🌐 Follow Me
Stay up to date with my projects and content:
- **GitHub:** [@thenolle](https://github.com/thenolle)
- **Website:** [thenolle.com](https://thenolle.com)
- **LinkedIn:** [@nollyberrebi](https://linkedin.com/in/nollyberrebi/)
- **Twitter/X:** [@thenolle_](https://x.com/thenolly_)
- **Instagram:** [@nolly.cafe](https://instagram.com/nolly.cafe)
- **YouTube:** [@nolly-b](https://youtube.com/@nolly-b)
- **Twitch:** [@thenolly](https://twitch.tv/thenolly)
- **Reddit:** [u/thenolle](https://reddit.com/u/thenolle)
- **Discord:** [nolly's cafe](https://thenolle.com/)
- **Patreon:** [@_nolly](https://patreon.com/_nolly)
- **Ko-fi:** [@nolly__](https://ko-fi.com/nolly__)