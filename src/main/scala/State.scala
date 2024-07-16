final case class State(
    number: Int,
    actionCombination: String,
    moves: Seq[String],
    transitions: Map[
      String,
      State
    ]
) {
  def getMoves: String =
    moves.mkString("\n")
}
