# Power Pong

Welcome to **Power Pong**! This is a Java-based Ping-Pong game with enhanced features. Designed to run in a Java environment, the game provides a classic Pong experience with modern twists. This project is entirely my work, and I welcome any feedback or suggestions for improvement.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Installation](#installation)
- [Running the Game](#running-the-game)
- [Controls](#controls)
- [Dependencies](#dependencies)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Overview

**Power Pong** incorporates various advanced programming techniques and concepts such as:

- **Graphics Handling**: Utilizes `Graphics2D` for rendering game elements.
- **Game State Management**: Efficiently manages game states like Main Menu, Options, and Pause.
- **Dynamic User Interface**: Adjusts UI elements dynamically based on game state and user interaction using Swing components.
- **Power-Ups**: Features special power-ups that influence gameplay.
- **MVC Design Pattern**: Implements the Model-View-Controller (MVC) pattern with change listeners to separate game logic from the user interface.

## Features

- **Multiple Views**: Includes Main Menu, Options Menu, Pause Menu, and gameplay views.
- **Dynamic Menus**: Menus adapt based on user interactions and game state.
- **Customizable Graphics**: Supports custom fonts and dynamic text rendering.
- **Power-Up Mechanics**: Integrates special effects and mechanics that affect gameplay.
- **MVC Design Pattern**: Utilizes change listeners to synchronize model updates with view changes.

## Installation

To set up the project, follow these steps:

1. **Clone the Repository**:

    ```sh
    git clone https://github.com/ukpabik/power-pong.git
    cd power-pong
    ```

2. **Compile the Code**:

    Ensure you have JDK 8 or higher installed. Compile the Java files using:

    ```sh
    javac -d bin src/**/*.java
    ```

3. **Run the Game**:

    Navigate to the `bin` directory and execute the `Main` class:

    ```sh
    java Main
    ```

## Running the Game

To start the game, run the `Main` class's `main` method. This initializes and launches the game.

```sh
javac src/Main.java
java Main
```
Ensure your classpath is correctly set to include all necessary classes and dependencies.

## Controls

- **Player Movement**: Use the arrow keys to control the paddles.
- **Special Actions**: Use the spacebar for additional actions.

## Dependencies

- **Java Development Kit (JDK)**: Version 8 or higher is required.
- **No external libraries** are needed; all functionality is built using Java standard libraries.

## License

This project is under exclusive copyright. No specific license is used. Please refer to the project's licensing information for details.

## Contact

For questions or feedback, contact me:

- **Email**: kuolingou1@gmail.com
- **GitHub**: [ukpabik](https://github.com/ukpabik)
