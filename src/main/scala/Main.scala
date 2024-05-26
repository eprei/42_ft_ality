object Main {
  def main(args: Array[String]): Unit = {
    KeyEventCapture.main(args)
    while (true) {
      Thread.sleep(1000)
    }
  }
}