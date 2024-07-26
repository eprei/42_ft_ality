import java.io.{FileNotFoundException, IOException}

object Main {
  def main(args: Array[String]): Unit = {
    parse_args(args) match {
      case Left(ParseArgsError.Help) =>
        print_help()

      case Left(ParseArgsError.InvalidNumberOfArgs) =>
        println("usage: ft_ality [-h] grammarFile")

      case Right(grammar_path) =>
        println("[+] Loading grammar...")
        val grammar =
          try {
            os.read(grammar_path)
          } catch {
            case e: FileNotFoundException =>
              println(s"ft_ality: error: file not found: $grammar_path")
              return
            case e: IOException =>
              println(s"ft_ality: error: could not open: $grammar_path")
              return
          }

        println(grammar)

        // parsing file

        // create mapping keys

        // create automata
        // TODO get these states during parsing of the grammar file
        val state3: State = State(
          number = 2,
          moves = Seq("Blade Swipe (Baraka)")
        )

        val state2: State = State(
          number = 2,
          moves = Seq("Dodge hit (Sonya)", "Left punch (Freddy Krueger)")
        )

        val state1: State = State(
          number = 1,
          moves = Seq("Fading to the left (Liu-Kang)")
        )

        val state0: State = State(
          number = 0,
          moves = Seq()
        )

        val automata: Automaton = Automaton(transitions =
          Map("Left" -> state1, "Left,[FP]" -> state2, "Left+[FP]" -> state3)
        )

        val gui: Gui = new Gui(automata.transitions)
        // start the graphical environment
        gui.main(args)

        while (true) {
          Thread.sleep(1000)
        }
    }
  }
}
