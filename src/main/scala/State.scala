final case class State(
    number: Int,
    moves: Seq[String],
) {
  def getMoves: Seq[String] =
    if (moves.isEmpty) Seq.empty[String] else moves
}
