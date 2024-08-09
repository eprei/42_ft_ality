object GrammarValidator:
  def validate(grammar: Grammar): Either[SyntaxError, Grammar] =
    val allKeysValid = grammar.keyMapping.keys.forall(isValidKey)
    val allCombosValid = grammar.combos.values.forall(isValidCombo(_, grammar.keyMapping.values))

    if (!allKeysValid) Left(SyntaxError.InvalidKey)
    else if (!allCombosValid) Left(SyntaxError.UnknownAction)
    else Right(grammar)

  private def isValidKey(key: String): Boolean =
    val specialCharacters = Set("←", "→", "↑", "↓", "left", "right", "up", "down")
    key.length == 1 && key.forall(_.isLetterOrDigit) || specialCharacters.contains(key)

  private def isValidCombo(combo: String, allActions: Iterable[String]): Boolean =
    combo.split(Array(',', '+')).forall(allActions.toSet.contains)

