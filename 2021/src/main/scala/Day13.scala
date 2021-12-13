package it.imolab.adventOfCode._2021.Day13

import scala.io.Source
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16

private val puzzle: List[String] = Source.fromFile("2021/src/main/resources/Day13.input.txt").getLines.toList

/*
You reach another volcanically active part of the cave. It would be nice if you could do some kind of thermal imaging
so you could tell ahead of time which caves are too hot to safely enter.

Fortunately, the submarine seems to be equipped with a thermal camera! When you activate it, you are greeted with:

Congratulations on your purchase! To activate this infrared thermal imaging
camera system, please enter the code found on page 1 of the manual.
Apparently, the Elves have never used this feature. To your surprise, you manage to find the manual; as you go to open it,
page 1 falls out. It's a large sheet of transparent paper! The transparent paper is marked with random dots and includes
instructions on how to fold it up (your puzzle input). For example:
6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0

fold along y=7
fold along x=5

The first section is a list of dots on the transparent paper. 0,0 represents the top-left coordinate.
The first value, x, increases to the right. The second value, y, increases downward.
So, the coordinate 3,0 is to the right of 0,0, and the coordinate 0,7 is below 0,0.
The coordinates in this example form the following pattern, where # is a dot on the paper and . is an empty, unmarked position:
...#..#..#.
....#......
...........
#..........
...#....#.#
...........
...........
...........
...........
...........
.#....#.##.
....#......
......#...#
#..........
#.#........

Then, there is a list of fold instructions. Each instruction indicates a line on the transparent paper and wants you to fold the paper up
(for horizontal y=... lines) or left (for vertical x=... lines). In this example, the first fold instruction is fold along y=7,
which designates the line formed by all of the positions where y is 7 (marked here with -):
...#..#..#.
....#......
...........
#..........
...#....#.#
...........
...........
-----------
...........
...........
.#....#.##.
....#......
......#...#
#..........
#.#........

Because this is a horizontal line, fold the bottom half up. Some of the dots might end up overlapping after the fold is complete,
but dots will never appear exactly on a fold line. The result of doing this fold looks like this:
#.##..#..#.
#...#......
......#...#
#...#......
.#.#..#.###
...........
...........

Now, only 17 dots are visible.

Notice, for example, the two dots in the bottom left corner before the transparent paper is folded; after the fold is complete, those dots
appear in the top left corner (at 0,0 and 0,1). Because the paper is transparent, the dot just below them in the result (at 0,3) remains visible,
as it can be seen through the transparent paper.

Also notice that some dots can end up overlapping; in this case, the dots merge together and become a single dot.

The second fold instruction is fold along x=5, which indicates this line:
#.##.|#..#.
#...#|.....
.....|#...#
#...#|.....
.#.#.|#.###
.....|.....
.....|.....

Because this is a vertical line, fold left:
#####
#...#
#...#
#...#
#####
.....
.....
The instructions made a square!

The transparent paper is pretty big, so for now, focus on just completing the first fold. After the first fold in the example above,
17 dots are visible - dots that end up overlapping after the fold is completed count as a single dot.

How many dots are visible after completing just the first fold instruction on your transparent paper?
*/
def solve_1 (p: List[String]): Int =
  val (dots, folds) = parseInput(p)
  fold(dots, folds(0)).size


// ===================================================================

/*
Finish folding the transparent paper according to the instructions. The manual says the code is always eight capital letters.

What code do you use to activate the infrared thermal imaging camera system?
*/

def solve_2 (p: List[String]): Unit =
  val (dots, folds) = parseInput(p)
  val solution = folds.foldLeft(dots)(fold)
  
  val w = solution.map(_._1).max
  val h = solution.map(_._2).max

  val display : List[String] = Range.inclusive(0, h).toList.map(y => Range.inclusive(0, w).toList.map(x => if solution.contains(Dot(x, y)) then "#" else " ").mkString)

  display.foreach(l => println(l))

@main def answer_1 = println("2021 - Day 13 - answer 1: " + solve_1(puzzle))

// @main def answer_2 = println("2021 - Day 13 - answer 2: " + solve_2(puzzle))
@main def answer_2 = solve_2(puzzle)

// ===========================================================

case class Dot (x: Int, y: Int)

sealed trait Fold
case class VerticalFold (x: Int) extends Fold
case class HorizontalFold (y: Int) extends Fold

private def parseInput (p: List[String]): (List[Dot], List[Fold]) =
  val (_dots, _folds) = p.span(s => !s.isEmpty)

  val dots = _dots.map(s => {
    val splitPattern = """(\d+),(\d+)""".r
    val m = splitPattern.findAllIn(s).matchData.next
    Dot(m.group(1).toInt, m.group(2).toInt)
  })

  val folds = _folds
    .filter(s => !s.isEmpty)
    .map(s => {
      val splitPattern = """fold along (.)=(\d+)""".r
      val m = splitPattern.findAllIn(s).matchData.next
      m.group(1) match
        case "x" => VerticalFold(m.group(2).toInt)
        case _   => HorizontalFold(m.group(2).toInt)
    })
  
  (dots, folds)

private def fold (dots: List[Dot], fold: Fold): List[Dot] =
  fold match
    case VerticalFold(x)    => dots.map(d => Dot(
      if d.x > x  then
        (2 * x - d.x)
      else
        d.x
      ,
      d.y
    )).distinct
    case HorizontalFold(y)  => dots.map(d => Dot(
      d.x,
      if d.y > y then
        (2 * y - d.y)
      else
        d.y
    )).distinct