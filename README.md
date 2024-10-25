# FT_ALITY

## Project Overview

FT_ALITY is a Scala-based application for recognizing fighting game-style keyboard combos. It utilizes a finite-state automaton (FSA) to detect key combinations, making it ideal for emulating training modes of fighting games. Through this project, we explore functional programming techniques, regular languages, and syntactic analysis.

## Features

- **Automaton Training**: The FSA is built at runtime by reading a grammar file with specified moves, allowing dynamic configuration and fast setup.
- **Key Combination Recognition**: Recognizes both single and combo key presses, designed for input emulation similar to fighting game move sequences.
- **Graphical Interface**: Displays recognized key presses in real-time, enabling user interaction with a visual guide for valid key combinations.

## Project Structure

```plaintext
├── docs/                     # Documentation files
├── grammars/                 # Contains grammar files for configuring automaton moves
├── src/main/scala/           # Main Scala application files
│   ├── Automaton.scala       # FSA setup and combo parsing
│   ├── Gui.scala             # GUI to display key combinations
│   ├── GrammarValidator.scala# Validates grammar inputs
│   ├── CombinationCalculator.scala # Helper to determine sequential or simultaneous actions
│   └── Main.scala            # Main application entry point
├── Makefile                  # Build automation script
├── build.sbt                 # SBT project configuration
└── README.md                 # Project README
```

## Setup Instructions

### Requirements
- **Scala** 3.4.1 or higher
- **Java JDK** 8 or higher
- **SBT** (Scala Build Tool)

### Installation

1. Clone the repository

2. Build and run using SBT:
   ```bash
   sbt compile
   sbt run
   ```

### Running the Application
Run the application with a grammar file as an argument:
```bash
./ft_ality grammars/mk9.yml
```
- The GUI displays valid key mappings and recognizes combos in real-time.

## Grammar Configuration
Grammar files, located in the `grammars/` directory, define valid key mappings and combos. Customize these YAML files to specify different key combos for the FSA.

### Example Grammar
```yaml
keyMapping:
  →: Right
  ↓: Down
  ←: Left
  ↑: Up
  z: "[FK]"
  x: "[FP]"
combos:
  Punch (Baraka): "Left+[FP]"
  Kick (Liu-Kang): "Right+[FK]"
```

## Functional Requirements
1. **Automaton Definition**: Define FSA with states, transitions, and an acceptance condition.
2. **Real-time Recognition**: Combo display updates dynamically with user input.
3. **Error Handling**: Provides feedback for invalid or unknown keys.
