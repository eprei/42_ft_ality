object StateUtils {
  def getMovesIfExists(transitions: Map[String, State], currentCombination: String): String = {
    transitions.get(currentCombination).map(_.getMoves).getOrElse("")
  }
}