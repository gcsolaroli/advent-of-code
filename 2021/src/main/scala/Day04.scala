package it.imolab.adventOfCode._2021.Day04

import scala.io.Source

private val puzzle: List[String] = Source.fromFile("2021/src/main/resources/Day04.input.txt").getLines.toList

/*
You're already almost 1.5km (almost a mile) below the surface of the ocean, already so deep that you can't see any sunlight.
What you can see, however, is a giant squid that has attached itself to the outside of your submarine.

Maybe it wants to play bingo?

Bingo is played on a set of boards each consisting of a 5x5 grid of numbers. Numbers are chosen at random, and the chosen
number is marked on all boards on which it appears. (Numbers may not appear on all boards.)
If all numbers in any row or any column of a board are marked, that board wins. (Diagonals don't count.)

The submarine has a bingo subsystem to help passengers (currently, you and the giant squid) pass the time.
It automatically generates a random order in which to draw numbers and a random set of boards (your puzzle input).
For example:

7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19

 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7

 After the first five numbers are drawn (7, 4, 9, 5, and 11), there are no winners, but the boards are marked as follows (shown here adjacent to each other to save space):

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7


After the next six numbers are drawn (17, 23, 2, 0, 14, and 21), there are still no winners:

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7


Finally, 24 is drawn:

22 13 17 11  0         3 15  0  2 22        14 21 17 24  4
 8  2 23  4 24         9 18 13 17  5        10 16 15  9 19
21  9 14 16  7        19  8  7 25 23        18  8 23 26 20
 6 10  3 18  5        20 11 10 24  4        22 11 13  6  5
 1 12 20 15 19        14 21 16 12  6         2  0 12  3  7

At this point, the third board wins because it has at least one complete row or column of marked numbers (in this case, the entire
top row is marked: 14 21 17 24 4).

The score of the winning board can now be calculated.
Start by finding the sum of all unmarked numbers on that board; in this case, the sum is 188.
Then, multiply that sum by the number that was just called when the board won, 24, to get the final score, 188 * 24 = 4512.

To guarantee victory against the giant squid, figure out which board will win first.
What will your final score be if you choose that board?

*/

def solve_1(p: List[String]): Int =
  val inputs: List[Int] = splitString(p.head, ",").map(_.toInt)
  val boards = parseBoards(p.tail)

  val (winningBoard : Board, drawnNumbers: List[Int], _) = searchWinningBoard(boards, List(), inputs)

  winningBoard.values.flatten.diff(drawnNumbers).sum * drawnNumbers.head

// ===================================================================

/*
On the other hand, it might be wise to try a different strategy: let the giant squid win.

You aren't sure how many bingo boards a giant squid could play at once, so rather than waste time counting its arms,
the safe thing to do is to figure out which board will win last and choose that one.
That way, no matter which boards it picks, it will win for sure.

In the above example, the second board is the last to win, which happens after 13 is eventually called and its middle
column is completely marked. If you were to keep playing until this point, the second board would have a sum of unmarked
numbers equal to 148 for a final score of 148 * 13 = 1924.

Figure out which board will win last. Once it wins, what would its final score be?
*/

def solve_2(p: List[String]): Int =
  val inputs: List[Int] = splitString(p.head, ",").map(_.toInt)
  val boards = parseBoards(p.tail)

  // val (winningBoard : Board, drawnNumbers: List[Int]) = searchWinningBoard(boards, List(), inputs)

  def go (leftoverBoards: List[Board], drawnNumbers:List[Int], numbersToDraw: List[Int]) : (Board, List[Int]) =
    val (winningBoard : Board, drawnNumbers_ : List[Int], numbersToDraw_ : List[Int]) = searchWinningBoard(leftoverBoards, drawnNumbers, numbersToDraw)
    if leftoverBoards.size == 1
      then (winningBoard, drawnNumbers_)
      else 
        go(leftoverBoards.filter(!_.equals(winningBoard)), drawnNumbers_, numbersToDraw_)

  val (loosingBoard: Board, drawnNumbers: List[Int]) = go(boards, List(), inputs)

  loosingBoard.values.flatten.diff(drawnNumbers).sum * drawnNumbers.head


@main def answer_1 = println("2021 - Day 04 - answer 1: " + solve_1(puzzle))

@main def answer_2 = println("2021 - Day 04 - answer 2: " + solve_2(puzzle))

// =============================================================================

private case class Board(values: List[List[Int]])


private def splitString (value: String, splitter: String) : List[String] =
  value.split(splitter) match
    case a: Array[String | Null] => a.map { v => v match
      case s: String  => s
      case _          => ""
    }.filter(!_.isEmpty).toList
    case _ => List()


private def parseBoard (lines: List[String]): Board =
  Board(lines.map(l => splitString(l, " ").map(_.toInt)))


private def parseBoards (allLines: List[String]): List[Board] =
  def emptyLine (s: String) : Boolean = s.trim match
    case ss: String => s.isEmpty
    case _          => false

  def go (acc: List[Board], lines: List[String]) : List[Board] = 
    val (takeLines: List[String], dropLines: List[String]) = ((p : (List[String], List[String])) => { val (h,t) = p; (h, t.dropWhile(emptyLine)) } ).apply(lines.span(!emptyLine(_)))

    if takeLines.isEmpty then
      if dropLines.isEmpty then
        acc
      else
        go(acc, dropLines)
    else
      go(acc.appended(parseBoard(takeLines)), dropLines)

  go(List(), allLines)


private def doesBoardWins (selectedNumbers: List[Int])(board: Board): Boolean =
  !board.values.transpose.filter(_.diff(selectedNumbers).isEmpty).isEmpty ||
  !board.values.filter(_.diff(selectedNumbers).isEmpty).isEmpty

private def searchWinningBoard (boards: List[Board], drawnNumbers: List[Int], leftoverNumbers: List[Int]) : (Board, List[Int], List[Int]) =
    boards.filter(doesBoardWins(drawnNumbers))
      .headOption.map((_, drawnNumbers, leftoverNumbers))
      .getOrElse(searchWinningBoard(boards, leftoverNumbers.head :: drawnNumbers, leftoverNumbers.tail))
