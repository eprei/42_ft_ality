object StateUtils {
  def getMovesIfExists(transitions: Map[String, State], currentCombination: String): Seq[String] = {
    transitions.get(currentCombination) match {
      case Some(value) => value.getMoves
      case None => Seq.empty[String]
    }
  }
}