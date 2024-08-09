import automaton.Action
import global.Global.simultaneousKeyPressInterval

object KeyUtils {
  def adaptYamlActionsMapForAutomaton(inputMap: Map[String, String]): Map[String, Int]  =
    val invertedPairs: Seq[(String, String)] = inputMap.toSeq.map { case (k, v) => (v, k) }
    val indexedPairs: Seq[(String, Int)] = invertedPairs.zipWithIndex.map { case ((v, _), index) => (v, index) }
    indexedPairs.toMap

  def getActionIndex(keyMapping: Map[String, String], keyPressed: String): Option[Int] =
    keyMapping.get(keyPressed) match {
      case Some(existentAction) => adaptYamlActionsMapForAutomaton(keyMapping).get(existentAction)
      case None => None
    }

  def buildStringOfActionsForPrint(grammar: Grammar, actions: Seq[Action]): String =
    def generateString(actions: Seq[Action], prevTimestamp: Long = Long.MinValue): String = actions match {
      case Nil => ""
      case action :: tail =>
        val separator = calculateSeparator(prevTimestamp, action._2)
        val actionName = getActionNameByIndex(grammar.keyMapping, action._1)
        separator + actionName + generateString(tail, action._2)
    }

    "\n\t" + generateString(actions) + "\n"

  private def calculateSeparator(prevTimestamp: Long, actualTimeStamp: Long ) : String =
    if prevTimestamp == Long.MinValue then ""
    else if actualTimeStamp - prevTimestamp < simultaneousKeyPressInterval then "+"
    else ","


  private def getActionNameByIndex(inputMap: Map[String, String], actionIndex: Int): String =
    val indexedPairs: Seq[(Int, String)] = inputMap.toSeq.zipWithIndex.map { case ((_, v), index) => (index, v) }
    indexedPairs.toMap.getOrElse(actionIndex, "")
}
