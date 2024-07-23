final case class State(
    number: Int,
    moves: Seq[String],
) {
  def getMoves: String =
    moves.mkString("\n")
}
