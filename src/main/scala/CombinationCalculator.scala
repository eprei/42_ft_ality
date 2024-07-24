object CombinationCalculator {
  def calculateCombination(
      action: String,
      deltaKeystrokes: Long,
      currentCombination: String
  ): String = {
    val sequentialKeyPressInterval = 200
    val simultaneousKeyPressInterval = 40

    if (deltaKeystrokes <= simultaneousKeyPressInterval) {
      s"$currentCombination+$action"
    } else if (deltaKeystrokes > sequentialKeyPressInterval) {
      action
    } else { s"$currentCombination,$action" }
  }
}
