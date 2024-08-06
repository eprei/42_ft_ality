import java.awt.Color
import scala.swing.*
import scala.swing.event.*
import CombinationCalculator.*
import KeyUtils.{buildStringOfActionsForPrint, getActionIndex}
import automaton.Automaton
import automaton.Action
import global.Global.sequentialKeyPressInterval

import BorderPanel.Position.*

class Gui(automaton: Automaton, grammar: Grammar) extends SimpleSwingApplication {
  def top: Frame = new MainFrame {
    title = "Mortal Kombat (training mode)"

    var lastKeyPressedTime: Long = 0L
    var currentCombination: String = ""
    var seqAction: Seq[Action] = Seq.empty

    val instructions: TextArea = new TextArea(){
      text = "\n\t\tTry a combo!\n"
      font = new Font("Arial", java.awt.Font.BOLD, 18)
    }

    val comboExecuted: TextArea = new TextArea(){
      text = "\n\t\there the combos will appear"
      font = new Font("Arial", java.awt.Font.PLAIN, 16)
      editable = false
      lineWrap = true
      focusable = false
      background = Color.lightGray
    }

    val validKeys: TextArea = new TextArea(){
      val pressableKeys: String = grammar.keyMapping.keySet.mkString(" ")
      text = s"\n\t\tValid Keys:\n\t\t$pressableKeys\n"
      font = new Font("Arial", java.awt.Font.PLAIN, 15)
      editable = false
      lineWrap = true
      focusable = false
      foreground = Color.black
      background = Color.yellow.darker()
    }

    val actionExecuted: TextArea = new TextArea() {
      text = ""
      font = new Font("Arial", java.awt.Font.BOLD, 16)
      editable = false
      lineWrap = true
      focusable = false
      background = Color.yellow
    }

    val actionsPanel: BorderPanel = new BorderPanel {
      layout += validKeys -> Center
      layout += actionExecuted -> South
    }

    contents = new BorderPanel {
      layout += instructions -> North
      layout +=  comboExecuted -> Center
      layout +=  actionsPanel  -> South

      focusable = true
      requestFocus()
      listenTo(keys)

      reactions += { case KeyPressed(_, keyPressed, _, _) =>
        val currentTime: Long = System.currentTimeMillis()
        val isSequentialInterval: Boolean = currentTime - lastKeyPressedTime < sequentialKeyPressInterval
        val actionIndex: Option[Int] = getActionIndex(grammar.keyMapping, keyPressed.toString.toLowerCase())

        actionIndex.foreach { action =>
          seqAction = calculateCombination((action, currentTime), isSequentialInterval, seqAction)
          val combosDetectedByAutomaton: Set[String] = automaton.parse(seqAction)
          lastKeyPressedTime = currentTime
          actionExecuted.text = buildStringOfActionsForPrint(grammar, seqAction)
          comboExecuted.text = "\n\t\t" + combosDetectedByAutomaton.mkString("\n\t\t")
        }
      }
    }

    size = new Dimension(600, 800)
    centerOnScreen()
    resizable = false
  }
}
