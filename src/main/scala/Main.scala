import automaton.Automaton
import cats.syntax.either.*
import os.Path

object Main:
  def main(args: Array[String]): Unit =
    parseArgsAndLoadGrammar(args) match
      case Left(error) => handleError(error)
      case Right(grammar) => launchGui(trainAutomaton(grammar), grammar, args)

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

  private def adaptActionsMapForAutomaton(inputMap: Map[String, String]): Map[String, Int] =
    val invertedPairs: Seq[(String, String)] = inputMap.toSeq.map { case (k, v) => (v, k) }
    val indexedPairs: Seq[(String, Int)] = invertedPairs.zipWithIndex.map { case ((v, _), index) => (v, index) }
    indexedPairs.toMap

  private def trainAutomaton(grammar: Grammar): Automaton =
    val actionsForAutomaton: Map[String, Int] = adaptActionsMapForAutomaton(grammar.keyMapping)
    val combos: Map[String, String] = grammar.combos
    new Automaton(actionsForAutomaton, combos)

  private def launchGui(automaton: Automaton, grammar: Grammar, args: Array[String]): Unit =
    val gui = new Gui(automaton, grammar)
    gui.main(args)
    while (true)
      Thread.sleep(1000)
  