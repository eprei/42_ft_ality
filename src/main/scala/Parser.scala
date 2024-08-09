import os.RelPath

def parseArgs(args: Seq[String]): Either[AppError, os.Path] =
  if args.contains("-h") || args.contains("--help") then
    Left(AppError.Help)
  else
    args.size match
      case 1 => Right(os.pwd / RelPath(args.head))
      case _ => Left(AppError.InvalidArguments)

def print_help(): Unit =
  println("""usage: ft_ality [-h] grammarFile

positional arguments:
grammarFile    grammar file to train the automaton

optional arguments:
-h, --help    show this help message and exit""")
