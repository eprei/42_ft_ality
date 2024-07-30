import cats.syntax.either.*
import os.Path

object Main:
  def main(args: Array[String]): Unit =
    parseArgsAndLoadGrammar(args) match
      case Left(error) => handleError(error)
      case Right(grammar) => processGrammar(grammar, args)


  private def parseArgsAndLoadGrammar(args: Array[String]): Either[AppError, Grammar] =
    for
      grammarPath: Path <- parseArgs(args)
      grammarContent: String <- Either.catchNonFatal(os.read(grammarPath)).leftMap(AppError.IOError)
      parsedGrammar: Grammar <- YamlParser.parseYaml(grammarContent).leftMap(AppError.ParsingError)
      validatedGrammar: Grammar <- GrammarValidator.validate(parsedGrammar).leftMap(AppError.ValidationError)
    yield validatedGrammar

  private def handleError(error: AppError): Unit = error match
    case AppError.Help => print_help()
    case AppError.InvalidArguments => println("usage: ft_ality [-h] grammarFile")
    case AppError.IOError(error) => println(error)
    case AppError.ParsingError(error) => println(error)
    case AppError.ValidationError(error) => println(s"Syntactic error found in the grammar file: $error")


  private def processGrammar(grammar: Grammar, args: Array[String]): Unit =
    val automaton = Automaton.createFromGrammar(grammar)
    val gui = new Gui(automaton.transitions)
    gui.main(args)

    while (true)
      Thread.sleep(1000)
