import java.awt.Toolkit
import scala.swing.*
import scala.swing.event.*

class KeyEventCapture(transitions: Map[String, State]) extends SimpleSwingApplication {

  private def calculateArithmetic(
      action: String,
      deltaKeystrokes: Long
  ): String = {
    val sequentialKeyPressInterval = 200
    val simultaneousKeyPressInterval = 40

    if (deltaKeystrokes <= simultaneousKeyPressInterval) {
      s"+$action"
    } else if (deltaKeystrokes > sequentialKeyPressInterval) {
      action
    } else { s",$action" }
  }

  private def executeArithmetic(
      currentCombination: String,
      actionArithmetic: String
  ): String = {
    if (
      1 < currentCombination.length && (actionArithmetic.startsWith(
        "+"
      ) || actionArithmetic.startsWith(","))
    ) {
      currentCombination + actionArithmetic
    } else { actionArithmetic }
  }

  private def getMovesIfExists(transitions: Map[String, State], currentCombination: String, defaultText: String): String = {
    transitions.get(currentCombination).map(_.getMoves) match
      case Some(moves) => moves
      case None => defaultText
  }

  def top: Frame = new MainFrame {
    title = "Mortal Kombat training mode"

    var lastKeyPressedTime = 0L
    var currentCombination: String = ""

    val combinationExecuted: Label = new Label {
      text = "Press any key..."
      font = new Font("Arial", java.awt.Font.PLAIN, 24)
    }

    val nameOfDetectedMovement: Label = new Label {
      font = new Font("Arial", java.awt.Font.PLAIN, 24)
    }

    val panel: BoxPanel = new BoxPanel(Orientation.Vertical) {
      contents += combinationExecuted
      contents += nameOfDetectedMovement
      focusable = true
      requestFocus()

      listenTo(keys)
      reactions += { case KeyPressed(_, keyPressed, _, _) =>
        val currentTime: Long = System.currentTimeMillis()
        val deltaKeystrokes = currentTime - lastKeyPressedTime
        val action: String = keyToAction(keyPressed.toString)
        val actionArithmetic = calculateArithmetic(action, deltaKeystrokes)

        // Not functional
        currentCombination =
          executeArithmetic(currentCombination, actionArithmetic)
        lastKeyPressedTime = currentTime
        // Not functional but at some point we have to update the GUI
        combinationExecuted.text = currentCombination
        nameOfDetectedMovement.text = getMovesIfExists(transitions, currentCombination, nameOfDetectedMovement.text)
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
