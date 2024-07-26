import os.RelPath

enum ParseArgsError:
  case Help, InvalidNumberOfArgs

def parse_args(args: Seq[String]): Either[ParseArgsError, os.Path] =
  if args.contains("-h") || args.contains("--help") then
    Left(ParseArgsError.Help)
  else
    args.size match
      case 1 => Right(os.pwd / RelPath(args.head))
      case _ => Left(ParseArgsError.InvalidNumberOfArgs)

def print_help(): Unit =
  println("""usage: ft_ality [-h] grammarFile

positional arguments:
grammarFile    grammar file to train the automaton

optional arguments:
-h, --help    show this help message and exit""")
