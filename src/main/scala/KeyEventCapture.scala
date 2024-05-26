import java.awt.Toolkit
import scala.swing.*
import scala.swing.event.*

object KeyEventCapture extends SimpleSwingApplication {

  def top: Frame = new MainFrame {
    title = "Mortal Kombat training mode"

    val label = new Label {
      text = "Press any key..."
      font = new Font("Arial", java.awt.Font.PLAIN, 24)
    }

    // Panel to hold the label
    val panel = new FlowPanel {
      contents += label
      focusable = true
      requestFocus()

      // Listen for key presses
      listenTo(keys)
      reactions += {
        case KeyPressed(_, key, _, _) =>
          label.text = s"${key.toString}"
      }
    }

    contents = panel

    size = new Dimension(400, 200)
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
}
