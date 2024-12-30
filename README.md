# Catalog-Placement-Assignment

## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Compiling and Running

Step 1: Install Dependencies
Ensure you have the following installed:
Java Development Kit (JDK): Version 8 or later
JSON Library: The json.jar file should be present in the lib folder.

Step 2: Compile the Program
To compile the program, use the following command in the terminal from the root directory of the project:
javac -cp "lib/json.jar" -d bin src/ShamirSecretSharing.java

-cp "lib/json.jar": Specifies the classpath for the json.jar library.
-d bin: Directs the compiled .class files to the bin folder.
src/ShamirSecretSharing.java: Path to the main Java file.

Step 3: Run the Program
Once the program is compiled successfully, run it with the following command:
java -cp "bin;lib/json.jar" ShamirSecretSharing

-cp "bin;lib/json.jar": Sets the classpath to include the bin directory (compiled classes) and the json.jar library.
ShamirSecretSharing: Specifies the main class to execute.

Step 4: Input JSON Files
Ensure the input JSON files (testcase1.json and testcase2.json) are in the same directory where the program is being executed or provide their full paths in the code.
