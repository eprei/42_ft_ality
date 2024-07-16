final case class KeyMapping(
    keyToAction: Map[String, String]
)

// TODO according to the subject:
// "The key mappings must be automatically computed from the grammar.
// If they’re hardcoded, I will personally come and break your bones."
val keyMapping: KeyMapping = KeyMapping(
  keyToAction = Map(
    "q" -> "Block",
    "↓" -> "Down",
    "w" -> "Flip Stance",
    "←" -> "Left",
    "→" -> "Right",
    "e" -> "Tag",
    "a" -> "Throw",
    "↑" -> "Up",
    "s" -> "[BK]",
    "d" -> "[BP]",
    "z" -> "[FK]",
    "x" -> "[FP]"
  )
)

def getAction(key: String): String = keyMapping.keyToAction.getOrElse(key.toLowerCase(), "")
