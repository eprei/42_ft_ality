package parsers

import automaton.Action

object Parsers:
    type ParseResult = (Int, Int)
    type Parser = Seq[Action] => Option[ParseResult]
    val simultaneousKeyPressInterval: Int = 40

    def action(id: Int): Parser =
    l => if l.isEmpty || l(0)(0) != id then None else Some(1, l(0)(1))

    extension (p: Parser)
        def simult(p2: Parser): Parser =
        l =>
            p(l) match
                case Some((n, ts)) => 
                    p2(l.drop(n)) match
                        case Some((n2, ts2)) => if ts2 - ts < simultaneousKeyPressInterval then Some(n + n2, ts2) else None
                        case _ => None
                case _ => None

        def and(p2: Parser): Parser =
        l =>
            p(l) match
                case Some((n, ts)) => 
                    p2(l.drop(n)) match
                        case Some((n2, ts2)) => Some(n + n2, ts2)
                        case _ => None
                case _ => None


        
                        
            


