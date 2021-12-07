package it.imolab.adventOfCode._2021.Day07

import scala.io.Source

private val puzzle: List[Integer] = splitString(Source.fromFile("2021/src/main/resources/Day07.input.txt").mkString, ",").map(_.trim match {case str:String =>str; case _ => "0"}).map(_.toInt)

/*
A giant whale has decided your submarine is its next meal, and it's much faster than you are. There's nowhere to run!

Suddenly, a swarm of crabs (each in its own tiny submarine - it's too deep for them otherwise) zooms in to rescue you!
They seem to be preparing to blast a hole in the ocean floor; sensors indicate a massive underground cave system just
beyond where they're aiming!

The crab submarines all need to be aligned before they'll have enough power to blast a large enough hole for your
submarine to get through. However, it doesn't look like they'll be aligned before the whale catches you! Maybe you can help?

There's one major catch - crab submarines can only move horizontally.

You quickly make a list of the horizontal position of each crab (your puzzle input). Crab submarines have limited fuel, so
you need to find a way to make all of their horizontal positions match while requiring them to spend as little fuel as possible.

For example, consider the following horizontal positions:

16,1,2,0,4,2,7,1,2,14
This means there's a crab with horizontal position 16, a crab with horizontal position 1, and so on.

Each change of 1 step in horizontal position of a single crab costs 1 fuel. You could choose any horizontal position to align
them all on, but the one that costs the least fuel is horizontal position 2:

Move from 16 to 2: 14 fuel
Move from 1 to 2: 1 fuel
Move from 2 to 2: 0 fuel
Move from 0 to 2: 2 fuel
Move from 4 to 2: 2 fuel
Move from 2 to 2: 0 fuel
Move from 7 to 2: 5 fuel
Move from 1 to 2: 1 fuel
Move from 2 to 2: 0 fuel
Move from 14 to 2: 12 fuel
This costs a total of 37 fuel. This is the cheapest possible outcome; more expensive outcomes include aligning at position 1 (41 fuel),
position 3 (39 fuel), or position 10 (71 fuel).

Determine the horizontal position that the crabs can align to using the least fuel possible.
How much fuel must they spend to align to that position?
*/
def solve_1 (p: List[Integer]): Integer =
  Range.inclusive(p.min, p.max)
    .map(d => (d, p         
      .map(x => Math.abs(d - x))                                                //  compute the fuel required to reach a give position for each submarine
      .sum)                                                                     //  sum them all together
    )                             
    .min((a1, a2) => { val (x1, f1) = a1; val (x2, f2) = a2; f1.compare(f2) })  //  find the solution using the least fuel
    ._2                                                                         //  return just the fuel

// ===================================================================

/*
The crabs don't seem interested in your proposed solution. Perhaps you misunderstand crab engineering?

As it turns out, crab submarine engines don't burn fuel at a constant rate. Instead, each change of 1 step in horizontal position
costs 1 more unit of fuel than the last: the first step costs 1, the second step costs 2, the third step costs 3, and so on.

As each crab moves, moving further becomes more expensive. This changes the best horizontal position to align them all on;
in the example above, this becomes 5:

Move from 16 to 5: 66 fuel
Move from 1 to 5: 10 fuel
Move from 2 to 5: 6 fuel
Move from 0 to 5: 15 fuel
Move from 4 to 5: 1 fuel
Move from 2 to 5: 6 fuel
Move from 7 to 5: 3 fuel
Move from 1 to 5: 10 fuel
Move from 2 to 5: 6 fuel
Move from 14 to 5: 45 fuel
This costs a total of 168 fuel. This is the new cheapest possible outcome; the old alignment position (2) now costs 206 fuel instead.

Determine the horizontal position that the crabs can align to using the least fuel possible so they can make you an escape route!
How much fuel must they spend to align to that position?
*/

def solve_2 (p: List[Integer]): Integer =
  Range.inclusive(p.min, p.max)
    .map(d => (d, p         
      .map(x => { val n = Math.abs(d - x); (n + 1) * n / 2} )                                                //  compute the fuel required to reach a give position for each submarine
      .sum)                                                                     //  sum them all together
    )                             
    .min((a1, a2) => { val (x1, f1) = a1; val (x2, f2) = a2; f1.compare(f2) })  //  find the solution using the least fuel
    ._2                                                                         //  return just the fuel


@main def answer_1 = println("2021 - Day 07 - answer 1: " + solve_1(puzzle))

@main def answer_2 = println("2021 - Day 07 - answer 2: " + solve_2(puzzle))

// ======================================================================

private def splitString (value: String, splitter: String) : List[String] =
  value.split(splitter) match
    case a: Array[String | Null] => a.map { v => v match
      case s: String  => s
      case _          => ""
    }.filter(!_.isEmpty).toList
    case _ => List()
