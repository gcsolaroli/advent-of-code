package it.imolab.adventOfCode._2021

import it.imolab.adventOfCode._2021.Day01 as Day01

final class Day02_Test extends TestSuite:
  val samplePuzzle: List[String] = List(
    "forward 5",
    "down 5",
    "forward 8",
    "up 3",
    "down 8",
    "forward 2"
  )

  test("answer 1") {
    Day02.solve_1(samplePuzzle) `shouldBe` 150
  }

  test("aswwer 2") {
    Day02.solve_2(samplePuzzle) `shouldBe` 900
  }