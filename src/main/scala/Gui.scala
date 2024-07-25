import java.awt.Toolkit
import scala.swing.*
import scala.swing.event.*
import CombinationCalculator.*
import StateUtils.*

class Gui(transitions: Map[String, State]) extends SimpleSwingApplication {
  def top: Frame = new MainFrame {
    title = "Mortal Kombat training mode"

    var lastKeyPressedTime: Long = 0L
    var currentCombination: String = ""

    val movementExecutedTxt: Label = new Label {
      text = "Movement executed"
      font = new Font("Arial", java.awt.Font.PLAIN, 15)
      horizontalAlignment = Alignment.Center
    }

    val movementExecuted: TextArea = new TextArea {
      text = "Press a sequence of keys to execute a special movement"
      font = new Font("Arial", java.awt.Font.PLAIN, 15)
      editable = false
      lineWrap = true
      focusable = false
    }

    val actionExecutedTxt: Label = new Label {
      text = "Actions"
      font = new Font("Arial", java.awt.Font.PLAIN, 15)
      horizontalAlignment = Alignment.Center
    }

    val actionExecuted: TextArea = new TextArea {
      val pressableKeys: String = keyMapper.keyToAction.keySet.mkString(" ")
      text = s"Press\n$pressableKeys"
      font = new Font("Arial", java.awt.Font.PLAIN, 15)
      editable = false
      lineWrap = true
      focusable = false
    }

    val panel: GridPanel = new GridPanel(2, 2) {
      contents += movementExecutedTxt
      contents += movementExecuted
      contents += actionExecutedTxt
      contents += actionExecuted

      focusable = true
      requestFocus()

      listenTo(keys)
      reactions += { case KeyPressed(_, keyPressed, _, _) =>
        val currentTime: Long = System.currentTimeMillis()
        val deltaKeystrokes: Long = currentTime - lastKeyPressedTime
        val action: Option[String] = keyToAction(keyPressed.toString)
        action.foreach { action =>
          currentCombination =
            calculateCombination(action, deltaKeystrokes, currentCombination)
          lastKeyPressedTime = currentTime
          actionExecuted.text = currentCombination
          movementExecuted.text = getMovesIfExists(transitions, currentCombination)
        }
      }
    }

    contents = panel

    size = new Dimension(800, 800)
    customCenterOnScreen(this)
    def customCenterOnScreen(frame: Frame): Unit = {
      val screenSize: Dimension = Toolkit.getDefaultToolkit.getScreenSize
      val frameSize: Dimension = frame.size
      frame.location = new Point(
        (screenSize.width - frameSize.width) / 2,
        (screenSize.height - frameSize.height) / 2
      )
    }
  }
}
