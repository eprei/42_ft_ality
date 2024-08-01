import java.awt.Color
import scala.swing.*
import scala.swing.event.*
import CombinationCalculator.*
import StateUtils.*
import automaton.Automaton

import BorderPanel.Position.*

class Gui(automaton: Automaton, grammar: Grammar) extends SimpleSwingApplication {
  def top: Frame = new MainFrame {
    title = "Mortal Kombat (training mode)"

    var lastKeyPressedTime: Long = 0L
    var currentCombination: String = ""

    val instructions: TextArea = new TextArea(){
      text = "\n\t\tTry a combo!\n"
      font = new Font("Arial", java.awt.Font.BOLD, 18)
    }

    val comboExecuted: TextArea = new TextArea(){
      font = new Font("Arial", java.awt.Font.PLAIN, 20)
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
        val deltaKeystrokes: Long = currentTime - lastKeyPressedTime
        val action: Option[String] = grammar.keyMapping.get(keyPressed.toString.toLowerCase())
        action.foreach { action =>
          // TODO use parsers logic to find the currentCombination and the actionExecuted
        currentCombination = calculateCombination(action, deltaKeystrokes, currentCombination)
        lastKeyPressedTime = currentTime
        actionExecuted.text = "\n\t" + currentCombination + "\n"
        comboExecuted.text = "\n\there the combos will appear"
//          comboExecuted.text = "\n" + getMovesIfExists(transitions, currentCombination).map(s => "\t" + s).mkString("\n") + "\n"
        }
      }
    }

    size = new Dimension(600, 800)
    centerOnScreen()
    resizable = false
  }
}
