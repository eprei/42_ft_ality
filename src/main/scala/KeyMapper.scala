final case class KeyMapper(
    keyToAction: Map[String, String]
)

// TODO according to the subject:
// "The key mappings must be automatically computed from the grammar.
// If they’re hardcoded, I will personally come and break your bones."
val keyMapper: KeyMapper = KeyMapper(
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

def keyToAction(key: String): Option[String] = keyMapper.keyToAction.get(key.toLowerCase)