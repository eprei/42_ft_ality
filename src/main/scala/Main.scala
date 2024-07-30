import java.io.{FileNotFoundException, IOException}
import cats.syntax.either.*

object Main {
  def main(args: Array[String]): Unit = {
    parse_args(args) match {
      case Left(ParseArgsError.Help) =>
        print_help()

      case Left(ParseArgsError.InvalidNumberOfArgs) =>
        println("usage: ft_ality [-h] grammarFile")

      case Right(grammar_path) =>
        println("[+] Loading grammar...")

        val grammarOrError: Either[Throwable, String] =
          Either.catchNonFatal(os.read(grammar_path))

        val parsedGrammar: Either[Throwable, Grammar] = for {
          grammarContent <- grammarOrError
          gram <- YamlParser.parseYaml(grammarContent)
        } yield gram: Grammar

        parsedGrammar match {
          case Left(error) =>
            error match {
              case _: FileNotFoundException =>
                println(s"ft_ality error: file not found: $grammar_path")
              case _: IOException =>
                println(s"ft_ality error: could not open: $grammar_path")
              case _ =>
                println(s"ft_ality error: ${error.getMessage}")
            }

          case Right(gram: Grammar) =>
            grammarValidator(gram) match {
              case Left(error) =>
                println(s"Syntactic error found in the grammar file: $error")
                return
              case Right(actionsAndCombos) => println(actionsAndCombos)
            }

            // TODO: get these states during parsing of the grammar file
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
              Map(
                "Left" -> state1,
                "Left,[FP]" -> state2,
                "Left+[FP]" -> state3
              )
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
}
