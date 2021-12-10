package it.imolab.adventOfCode._2021.Day09

import scala.io.Source
import scala.annotation.varargs

private val puzzle: List[String] = Source.fromFile("2021/src/main/resources/Day09.input.txt").getLines.toList

/*
These caves seem to be lava tubes. Parts are even still volcanically active; small hydrothermal vents release
smoke into the caves that slowly settles like rain.

If you can model how the smoke flows through the caves, you might be able to avoid it and be that much safer.
The submarine generates a heightmap of the floor of the nearby caves for you (your puzzle input).

Smoke flows to the lowest point of the area it's in. For example, consider the following heightmap:

2199943210
3987894921
9856789892
8767896789
9899965678

Each number corresponds to the height of a particular location, where 9 is the highest and 0 is the lowest a location can be.

Your first goal is to find the low points - the locations that are lower than any of its adjacent locations.
Most locations have four adjacent locations (up, down, left, and right); locations on the edge or corner of
the map have three or two adjacent locations, respectively. (Diagonal locations do not count as adjacent.)

In the above example, there are four low points, all highlighted: two are in the first row (a 1 and a 0),
one is in the third row (a 5), and one is in the bottom row (also a 5).
All other locations on the heightmap have some lower adjacent location, and so are not low points.

The risk level of a low point is 1 plus its height. In the above example, the risk levels of the low points are 2, 1, 6,
and 6. The sum of the risk levels of all low points in the heightmap is therefore 15.

Find all of the low points on your heightmap. What is the sum of the risk levels of all low points on your heightmap?
*/

def solve_1 (p: List[String]): Int =
  allCoordinates(p).map(riskLevel(p)).sum


// ===================================================================

/*
Next, you need to find the largest basins so you know what areas are most important to avoid.

A basin is all locations that eventually flow downward to a single low point.
Therefore, every low point has a basin, although some basins are very small.
Locations of height 9 do not count as being in any basin, and all other locations will always be part of exactly one basin.

The size of a basin is the number of locations within the basin, including the low point.
The example above has four basins.

The top-left basin, size 3:

21 99943210
3 987894921
 9856789892
 8767896789
 9899965678

The top-right basin, size 9:
2199943210
3987894921
9856789892
8767896789
9899965678

The middle basin, size 14:
2199943210
3987894921
9856789892
8767896789
9899965678
The bottom-right basin, size 9:

2199943210
3987894921
9856789892
8767896789
9899965678
Find the three largest basins and multiply their sizes together. In the above example, this is 9 * 14 * 9 = 1134.

What do you get if you multiply together the sizes of the three largest basins?
*/

def solve_2 (p: List[String]): Int =
  allCoordinates(p)
    .filter(c => riskLevel(p)(c) > 0)
    .map(basinSize(p)).sorted.reverse
    .take(3).product


@main def answer_1 = println("2021 - Day 09 - answer 1: " + solve_1(puzzle))

@main def answer_2 = println("2021 - Day 09 - answer 2: " + solve_2(puzzle))


// ===========================================================

private def allCoordinates (p:List[String]): IndexedSeq[(Int, Int)] =
  val width = p(0).length
  val height = p.length
  Range(0, width).flatMap(x => Range(0, height).map(y => (x, y)))

private def valueAtCoordinate(p:List[String])(c:(Int, Int)): Int =
  val width = p(0).length
  val height = p.length
  val (x, y) = c
  if (x < 0 ) || (width <= x) || (y < 0) || (height <= y)
  then 10
  else p(y)(x).asDigit

private def surroundingPoints (c:(Int, Int)): List[(Int, Int)] =
  val (x, y) = c
  List((x-1, y), (x+1, y), (x, y-1), (x, y+1))

private def riskLevel (p:List[String])(c:(Int, Int)): Int =
  if valueAtCoordinate(p)(c) < surroundingPoints(c).map(valueAtCoordinate(p)).min
    then  valueAtCoordinate(p)(c) + 1
    else  0

private def expandSelection (p:List[String])(l:List[(Int, Int)]): List[(Int, Int)] =
  def go (n:List[(Int, Int)], a:List[(Int, Int)]): List[(Int, Int)] =
    val nn = n.flatMap(surroundingPoints)
      .filter(c => !a.contains(c))
      .filter(c => (valueAtCoordinate(p)(c) < 9))
    val aa = (a ++ n).distinct
  
    if (nn.isEmpty)
    then aa
    else go(nn, aa)

  go(l, List())

private def basinSize (p:List[String])(c:(Int, Int)): Int =
  expandSelection(p)(List(c)).size
