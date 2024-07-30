enum SyntaxError:
  case InvalidKey, UnknownAction

def isValidKey(key: String): Boolean = {
  val validArrows = Set("←", "→", "↑", "↓")
  key.length == 1 && key.forall(_.isLetterOrDigit) || validArrows.contains(key)
}

def isValidCombo(combo: String, allValidActions: Iterable[String]): Boolean = {
  combo.split(Array(',', '+')).forall(action => allValidActions.exists(_ == action))
}

def transformMap(inputMap: Map[String, String]): Map[String, Int] = {
  val invertedPairs: Seq[(String, String)] = inputMap.toSeq.map { case (k, v) => (v, k) }
  val indexedPairs: Seq[(String, Int)] = invertedPairs.zipWithIndex.map { case ((v, _), index) => (v, index) }
  indexedPairs.toMap
}

def grammarValidator(grammar: Grammar): Either[SyntaxError, ActionsAndCombos] = {
  val allKeysValid = grammar.keyMapping.keys.forall(isValidKey)
  if (!allKeysValid)
    Left(SyntaxError.InvalidKey)
  else {
    val allCombosValid = grammar.combos.values.forall(combo => isValidCombo(combo, grammar.keyMapping.values))
    if (!allCombosValid)
      Left(SyntaxError.UnknownAction)
    else
      Right(ActionsAndCombos(transformMap(grammar.keyMapping), grammar.combos))
  }
}
