# Software Engineering Final Project

This repository contains the final project for the Software Engineering course at Politecnico di Milano. The project implements a networked digital version of the MyShelfie board game in Java, featuring a text-based user interface (TUI) and Java RMI for client–server communication.

---

## Overview

The goal of this project was to develop a distributed implementation of the MyShelfie board game, focusing on correct game rules, turn management, and resilience to client disconnections. The application runs in the terminal using ANSI colors to render the TUI, allowing players to interact with the game board and their personal shelves.

---

## Project Structure

- `Deliverables/`: Contains the project documentation and game-related material.
  - `GameRules.pdf`: Official game rules used as reference for the implementation.
  - Other project deliverables (e.g., reports or design documents), if present.

Additional source code and resource directories will be documented once the repository structure is finalized.

---

## How to Run the Project

The application is distributed as two separate JAR files: one for the server and one for the client.

1. Open a terminal on the machine that will act as the server.
2. Run the server JAR: java -jar MyShelfieServer.jar. When prompted, enter the IP address where the server will be reachable. The server runs by default on port 1068.
3. On each client machine, open a terminal and run: java -jar MyShelfieClient.jar. When prompted, enter the server IP address to connect to the game.

To fully enjoy the TUI, the terminal should support ANSI escape codes; otherwise, the interface will fall back to plain text with slot names.

---

## Documentation

- [Game Rules](Deliverables/Rulebook.pdf)

---

## Technologies

- Java
- Text-based User Interface (TUI) with ANSI colors
- Java RMI

---

## License

This project is released under the MIT License.

---

## Notes

This project was developed as part of the final assignment for the Software Engineering course at Politecnico di Milano. The code and documentation are provided for educational purposes and to demonstrate the implementation of a networked board game with a TUI and RMI-based architecture.

