package automaton

import parsers.Parsers.*

// (id of action, timestamp of action)
type Action = (Int, Long)

class Automaton(val actions: Map[String, Int], combos: Map[String, String]):

    val actions_remap: Map[Int, String] = actions.map(_.swap)
    val parsers: Seq[(String, Parser)] = combos.map(
        (name, combination) => 
            val combined_parser = combination.split(',').map( acts =>
                acts.split('+').map( act => action(this.actions(act))).reduceLeft(
                    _.simult(_)
                )
            ).reduceLeft(
                _.and(_)
            )
            (name, combined_parser)
    ).toSeq

    def parse(as: Seq[Action]): Set[String] =
        parsers.map(
            (n, p) => p(as).map(_ => n))
            .flatten
            .toSet
        
