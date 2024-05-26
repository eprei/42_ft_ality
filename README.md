# FT_ALITY

## Key Event Capture Project

This project is a keyboard event capture application developed in Scala using the Swing library. 
The application is capable of detecting and displaying combinations of keys pressed both sequentially and simultaneously.

## Project Objectives

The main objective of the project is to build a finite-state automaton that recognizes specific key combinations,
similar to move combinations in fighting games like Mortal Kombat. This includes:

- Capturing keyboard events.
- Detecting and displaying key combinations.
- Training and running a finite-state automaton to recognize moves based on key sequences.

For more info : [project description](docs/en.subject.pdf)

## Requirements

To run this project, you need:

- Scala 3.4.1 or higher.
- sbt (Scala Build Tool).
- Java JDK 8 or higher.

## Application Usage

When you run the application, a window with a central label "Press keys..." will open. You can start pressing keys, 
and the application will print the pressed keys in the terminal. If you press multiple keys simultaneously, they will 
be shown together, separated by the "+" sign.



