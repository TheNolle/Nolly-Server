# Contributing to Nolly's Server

🎉 First off, thank you for considering contributing to **Nolly's Server**! Your help is greatly appreciated, and we are excited to see how you can enhance the project. Whether you’re fixing a bug, adding new features, or improving documentation, every bit of contribution helps.

Please take a moment to review this guide to ensure that your contributions follow the best practices and help make Nolly's Server better for everyone.


## 🛠️ How Can I Contribute?

### Reporting Bugs 🐛
If you find a bug in the project, feel free to [open an issue](https://github.com/thenolle/nolly-server/issues). When reporting, please be as detailed as possible:
- Provide clear steps to reproduce the bug.
- Include your environment details (Java version, OS, etc.).
- If applicable, attach screenshots or logs to help illustrate the problem.

### Suggesting Enhancements ✨
We love hearing new ideas! If you have an enhancement suggestion (new feature, improved behavior, etc.), please:
1. **Check the existing issues** to see if your suggestion already exists.
2. **Create a new issue** if your idea hasn't been discussed yet.

### Pull Requests 👾
If you'd like to implement a feature or fix a bug, here's how to submit a pull request (PR):
1. **Fork the repository:** Click the "Fork" button on the repository page to copy the project into your GitHub account.
2. **Create a branch:** Always create a new branch for your work from the `master` branch. For example:
```bash
git checkout -b feature/my-new-feature
```
3. **Commit your changes:** Make sure your commits are meaningful and easy to understand. Follow these conventions:
 - Use descriptive commit messages (e.g., `Fix issue with server startup` or `Add support for custom commands`).
 - Include tests, if applicable.
```bash
git commit -am 'Add new feature to enhance server performance'
```
4. **Push your branch:**
```bash
git push origin feature/my-new-feature
```
5. **Open a pull request:** Once your branch is pushed, [open a pull request](https://github.com/thenolle/nolly-server/pulls) to submit your changes. Please include:
- A clear description of your changes.
- Any relevant issue numbers (e.g., fixes #123).
- Any screenshots or details that help illustrate your work.

### Style Guide 🖋️
For consistency, all code contributions must adhere to the following style rules:
- **Kotlin:** Follow the official [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html).
- **Testing:** Add tests for new features and ensure existing tests pass.
- **Documentation:** Ensure that all public classes and functions are documented using proper Kotlin Doc (`/** */`).

### Running Tests 🧪
Before submitting a pull request, ensure that all tests pass:
1. Run the following command to execute the test suite:
```bash
./gradlew test
```
2. Ensure that new features have accompanying tests, and that they cover all possible use cases.


## 🌟 Code of Conduct
We expect all contributors to adhere to our [Code of Conduct](Code%20of%20Conduct.md). Please make sure you read and understand this document to foster a respectful and inclusive community.


## 🚀 Releases & Versioning
- **Automated Releases:** We use GitHub Actions to automatically package and publish new server versions based on commits starting with `[New Version] <jar-name>.jar | <description>`. Please follow this format if your PR involves a version bump.

---

Thank you for taking the time to contribute to **Nolly's Server**. Your effort helps us maintain and grow this project! If you have any questions or need help, feel free to [open an issue](https://github.com/thenolle/nolly-server/issues) or reach out to us.