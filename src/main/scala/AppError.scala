sealed trait AppError

enum SyntaxError:
  case InvalidKey, UnknownAction

object AppError:
  case object Help extends AppError
  case object InvalidArguments extends AppError
  case class IOError(cause: Throwable) extends AppError
  case class ParsingError(cause: Throwable) extends AppError
  case class ValidationError(cause: SyntaxError) extends AppError