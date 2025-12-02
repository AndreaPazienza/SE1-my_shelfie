# Software Engineering Final Project

This repository contains the final project for the Software Engineering course at Politecnico di Milano. The project implements a networked digital version of the MyShelfie board game in Java, featuring a text-based user interface (TUI) and Java RMI for client–server communication.[attached_file:1][web:177]

---

## Overview

The goal of this project was to develop a distributed implementation of the MyShelfie board game, focusing on correct game rules, turn management, and resilience to client disconnections. The application runs in the terminal using ANSI colors to render the TUI, allowing players to interact with the game board and their personal shelves.[web:178][web:219]

---

## Project Structure

- `MyShelfie/src/main/java/`: Contains the main Java source code.
  - `CONTROLLER/`: Contains controller classes that manage game logic and coordinate interactions between model and view.
  - `Distributed/`: Contains classes related to the distributed architecture and Java RMI communication.
  - `Errors/`: Contains custom error and exception handling classes.
  - `Listeners/`: Contains listener interfaces and classes used for event handling.
  - `MODEL/`: Contains the core game domain model.
  - `VIEW/`: Contains the TUI components responsible for rendering the game state in the terminal.
  - `App.java`: Entry point for running the application.
  - `AppClient.java`: Entry point for starting the client application.
  - `AppServer.java`: Entry point for starting the server application.
- `MyShelfie/src/main/resources/`: Contains additional resources used by the application.
  - `personal_goals.json`: Configuration file defining the personal goal cards used in the game.
- `MyShelfie/src/test/java/`: Contains test classes for validating the behavior of the application.
  - `CONTROLLER/`: Tests for controller classes.
  - `MODEL/`: Tests for the game domain model.
  - `VIEW/`: Tests for the TUI components.
- `Deliverables/`: Contains the project documentation and game-related material.
  - `Rulebook.pdf`: Official game rules used as reference for the implementation.
  - `JavaDoc/`: Generated Javadoc API documentation for the project.
  - `SequenceDiagrams/`: Sequence diagrams describing interactions between system components.
  - `UML/`: UML diagrams illustrating the system architecture and design.
  - `TestCoverage.png`: Image showing the test coverage results for the codebase.
  - `MyShelfieClient.jar`: Executable JAR for the game client.
  - `MyShelfieServer.jar`: Executable JAR for the game server.[attached_file:2][web:243]

---

## How to Run the Project

The application is distributed as two separate JAR files: one for the server and one for the client.

1. Open a terminal on the machine that will act as the server.
2. Run the server JAR:
java -jar MyShelfieServer.jar

text
When prompted, enter the IP address where the server will be reachable. The server runs by default on port 1068.
3. On each client machine, open a terminal and run:
java -jar MyShelfieClient.jar

text
When prompted, enter the server IP address to connect to the game.[web:179]

To fully enjoy the TUI, the terminal should support ANSI escape codes; otherwise, the interface will fall back to plain text with slot names.[web:178][web:184]

---

## Documentation

- [Game Rules](Deliverables/Rulebook.pdf)
- [JavaDoc API documentation](Deliverables/JavaDoc)
- [Sequence diagrams](Deliverables/SequenceDiagrams)
- [UML diagrams](Deliverables/UML)
- [Test coverage report image](Deliverables/TestCoverage.png)[attached_file:2]

---

## Technologies

- Java
- Text-based User Interface (TUI) with ANSI colors
- Java RMI[web:215]

---

## License

This project is released under the MIT License.

---

## Notes

This project was developed as part of the final assignment for the Software Engineering course at Politecnico di Milano. The code and documentation are provided for educational purposes and to demonstrate the implementation of a networked board game with a TUI and RMI-based architecture.[attached_file:1]

---

## Acknowledgements

This repository was forked from the original project developed in collaboration with [Rubin Ronchieri Byrd](https://github.com/RubV18), [Gabriele Proverbio](https://github.com/GabrieleProverbio) and [Alessio Tagab](https://github.com/alessiotagab).
