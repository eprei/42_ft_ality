object CombinationCalculator {
  def calculateCombination(
      action: String,
      deltaKeystrokes: Long,
      currentCombination: String
  ): String = {
    val sequentialKeyPressInterval: Int = 200
    val simultaneousKeyPressInterval: Int = 40

    if (deltaKeystrokes <= simultaneousKeyPressInterval) {
      s"$currentCombination+$action"
    } else if (deltaKeystrokes > sequentialKeyPressInterval) {
      action
    } else { s"$currentCombination,$action" }
  }
}
