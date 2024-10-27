# FT_ALITY

A finite-state automaton implementation for recognizing fighting game combos, built with Scala and focusing on functional programming principles.

## 🎮 Overview

FT_ALITY is a project that recreates a fighting game's training mode by implementing a finite-state automaton (FSA) for recognizing keyboard combinations. The project emphasizes functional programming concepts while providing a practical application for formal language theory.

## 🛠 Technical Stack

- **Scala 3.4.1**: Main programming language
- **SBT**: Build tool and dependency management
- **Scala Swing**: GUI implementation
- **Circe**: YAML parsing and processing
- **OS-Lib**: File system operations

## 🌟 Key Features

- **Dynamic Automaton Training**: Builds FSA at runtime using YAML grammar files
- **Real-time Combo Recognition**: Detects both sequential and simultaneous key combinations
- **Graphical Interface**: Visual feedback for key presses and successful combos
- **Flexible Grammar System**: Customizable move sets through YAML configuration

## 🏗 Project Structure

```
├── src/main/scala/
│   ├── Automaton.scala         # Core FSA implementation
│   ├── Parsers.scala          # Input parsing logic
│   ├── Grammar.scala          # Grammar definitions
│   ├── GrammarValidator.scala # Input validation
│   ├── Gui.scala             # Graphical interface
│   └── Main.scala            # Application entry point
```

## 🚀 Getting Started

### Prerequisites

- Java JDK 8+
- Scala 3.4.1+
- SBT

### Installation

1. Clone the repository

2. Build the project:
```bash
make
```

### Running

You can run the program in several ways:

1. Using `make`:
```bash
# Run with default grammar file (mk9.yml)
make run

# Run with a specific grammar file
make run GRAMMAR=grammars/your_grammar.yml
```

2. Using `sbt` directly:
```bash
# Run with default grammar file
sbt "run grammars/mk9.yml"

# Run with a specific grammar file
sbt "run grammars/your_grammar.yml"
```

3. Show help message:
```bash
sbt "run -h"
```

## 📖 Grammar Format

The program uses YAML files to define key mappings and combos:

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

## 🎯 Functional Programming Highlights

- **Immutable Data Structures**: Extensive use of immutable collections and case classes
- **Pattern Matching**: Utilized for parsing and state transitions
- **Pure Functions**: Side-effect free implementations where possible
- **Type Safety**: Strong typing with sealed traits and enums
- **Monadic Error Handling**: Using Either for error propagation

## 🎨 Implementation Details

- **FSA Implementation**: Uses a pure functional approach for state transitions
- **Combo Detection**: Supports both sequential and simultaneous key presses
- **Error Handling**: Comprehensive validation for grammar files and user inputs
- **Real-time Processing**: Efficient parsing of keyboard inputs with timing considerations

## 📜 Project Goals

1. Implement a formal language parser using FSA
2. Apply functional programming principles in a real-world scenario
3. Create an interactive training mode for fighting game moves
4. Practice proper error handling and input validation

## 🔍 Key Learning Points

- Finite-State Automata implementation
- Functional programming patterns and practices
- Real-time input processing
- YAML configuration handling
- GUI development with Scala Swing

---

_Note: This project was developed as part of the programming curriculum at 42 School, focusing on functional programming concepts and formal language theory._
