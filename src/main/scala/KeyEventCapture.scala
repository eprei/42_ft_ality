import java.awt.Toolkit
import scala.swing.*
import scala.swing.event.*

object KeyEventCapture extends SimpleSwingApplication {

  // TODO get these states during parsing of the grammar file
  private val state3: State = State(
    number = 2,
    actionCombination = "Left+[FP]",
    moves = Seq("Blade Swipe (Baraka)"),
    transitions = Map.empty
  )

  private val state2: State = State(
    number = 2,
    actionCombination = "Left,[FP]",
    moves = Seq("Dodge hit (Sonya)", "Left punch (Freddy Krueger)"),
    transitions = Map.empty
  )

  private val state1: State = State(
    number = 1,
    actionCombination = "Left",
    moves = Seq("Fading to the left (Liu-Kang)"),
    transitions = Map("Left,[FP]" -> state2, "Left+[FP]" -> state3)
  )

  private val state0: State = State(
    number = 0,
    actionCombination = "",
    moves = Seq(),
    transitions = Map("Left" -> state1)
  )

  // Does not fit into the functional paradigm and will be corrected in future versions
  private var currentState: State = state0
  private var lastKeyPressedTime = 0L

  private val sequentialKeyPressInterval = 200
  private val simultaneousKeyPressInterval = 40

  private def getNextState(
      keyPressed: scala.swing.event.Key.Value,
      currentTime: Long
  ): State = {
    val action: String = getAction(keyPressed.toString)
    if ( // Keys pressed "simultaneously"
      currentState.actionCombination.nonEmpty && (currentTime - lastKeyPressedTime) <= simultaneousKeyPressInterval
    ) {
      currentState.transitions.getOrElse(
        s"${currentState.actionCombination}+$action", currentState
      )
    } else if ( // Key pressed "separately"
      currentState.actionCombination.nonEmpty && (currentTime - lastKeyPressedTime) > sequentialKeyPressInterval
    ) {
      state0.transitions.getOrElse(action, currentState)
    } else { // Keys pressed "sequentially"
      if (currentState.actionCombination.isEmpty)
        currentState.transitions.getOrElse(action, currentState)
      else
        currentState.transitions.getOrElse(
          s"${currentState.actionCombination},$action", currentState
        )
    }
  }

  def top: Frame = new MainFrame {
    title = "Mortal Kombat training mode"

    val actionsExecuted: Label = new Label {
      text = "Press any key..."
      font = new Font("Arial", java.awt.Font.PLAIN, 24)
    }

    val nameOfDetectedMovement: Label = new Label {
      font = new Font("Arial", java.awt.Font.PLAIN, 24)
    }

    val panel: BoxPanel = new BoxPanel(Orientation.Vertical) {
      contents += actionsExecuted
      contents += nameOfDetectedMovement
      focusable = true
      requestFocus()

      def handleKeyPress(
          keyPressed: scala.swing.event.Key.Value,
          currentTime: Long
      ): State = {
        getNextState(keyPressed, currentTime)
      }

      // Listen for key presses
      listenTo(keys)
      reactions += { case KeyPressed(_, keyPressed, _, _) =>
        val currentTime = System.currentTimeMillis()

        // Does not fit into the functional paradigm and will be corrected in future versions
        currentState = handleKeyPress(keyPressed, currentTime)

        // Does not fit into the functional paradigm and will be corrected in future versions
        lastKeyPressedTime = currentTime

        // Does not fit into the functional paradigm but at some point we have to update the label
        actionsExecuted.text = currentState.actionCombination
        nameOfDetectedMovement.text = currentState.getMoves
      }
    }

    contents = panel

    size = new Dimension(600, 400)
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
