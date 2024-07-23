object Main {
  def main(args: Array[String]): Unit = {
    // open file

    // parsing file 
    
    // create mapping keys
    
    // create automata
    
    // TODO get these states during parsing of the grammar file
    val state3: State = State(
      number = 2,
      moves = Seq("Blade Swipe (Baraka)"),
    )

    val state2: State = State(
      number = 2,
      moves = Seq("Dodge hit (Sonya)", "Left punch (Freddy Krueger)"),
    )

    val state1: State = State(
      number = 1,
      moves = Seq("Fading to the left (Liu-Kang)"),
    )

    val state0: State = State(
      number = 0,
      moves = Seq(),
    )

    val automata: Automaton = Automaton(transitions =
      Map("Left" -> state1, "Left,[FP]" -> state2, "Left+[FP]" -> state3)
    )

    val keyEventCaptureApp = new KeyEventCapture(automata.transitions)
    keyEventCaptureApp.main(args)
    while (true) {
      Thread.sleep(1000)
    }
  }
}
