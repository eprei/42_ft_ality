object CombinationCalculator {
  def calculateCombination(
      action: String,
      deltaKeystrokes: Long,
      currentCombination: String
  ): String = {
    val sequentialKeyPressInterval: Int = 1000
    val simultaneousKeyPressInterval: Int = 150

    if (deltaKeystrokes <= simultaneousKeyPressInterval) {
      s"$currentCombination+$action"
    } else if (deltaKeystrokes > sequentialKeyPressInterval) {
      action
    } else { s"$currentCombination,$action" }
  }
}
