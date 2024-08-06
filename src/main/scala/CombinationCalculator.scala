import global.Global.simultaneousKeyPressInterval
import global.Global.sequentialKeyPressInterval
import automaton.Action

object CombinationCalculator {
  def calculateCombination(
      action: Action,
      isSequentialInterval: Boolean,
      currentCombination: Seq[Action]
  ): Seq[Action] =
    if isSequentialInterval then currentCombination :+ action else Seq(action)
}
