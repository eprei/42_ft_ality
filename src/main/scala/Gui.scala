import java.awt.Toolkit
import scala.swing.*
import scala.swing.event.*
import CombinationCalculator._
import StateUtils._

class Gui(transitions: Map[String, State])
    extends SimpleSwingApplication {
  def top: Frame = new MainFrame {
    title = "Mortal Kombat training mode"

    var lastKeyPressedTime = 0L
    var currentCombination: String = ""

    val combinationExecuted: Label = new Label {
      text = "Press any key..."
      font = new Font("Arial", java.awt.Font.PLAIN, 24)
    }

    val detectedMovement: Label = new Label {
      font = new Font("Arial", java.awt.Font.PLAIN, 24)
    }

    val panel: BoxPanel = new BoxPanel(Orientation.Vertical) {
      contents += combinationExecuted
      contents += detectedMovement
      focusable = true
      requestFocus()

      listenTo(keys)
      reactions += { case KeyPressed(_, keyPressed, _, _) =>
        val currentTime: Long = System.currentTimeMillis()
        val deltaKeystrokes = currentTime - lastKeyPressedTime
        val action: Option[String] = keyToAction(keyPressed.toString)
        action.foreach { action =>
          // Not functional
          currentCombination =
            calculateCombination(action, deltaKeystrokes, currentCombination)
          lastKeyPressedTime = currentTime
          // Not functional but at some point we have to update the GUI
          combinationExecuted.text = currentCombination
          detectedMovement.text =
            getMovesIfExists(transitions, currentCombination)
        }
      }
    }

    contents = panel

    size = new Dimension(800, 800)
    customCenterOnScreen(this)
    def customCenterOnScreen(frame: Frame): Unit = {
      val screenSize = Toolkit.getDefaultToolkit.getScreenSize
      val frameSize = frame.size
      frame.location = new Point(
        (screenSize.width - frameSize.width) / 2,
        (screenSize.height - frameSize.height) / 2
      )
    }
  }

  override def main(args: Array[String]): Unit = {
    super.main(args)
    while (true) {
      Thread.sleep(1000)
    }
  }
}
