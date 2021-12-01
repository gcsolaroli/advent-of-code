package it.imolab.adventOfCode._2021

import it.imolab.adventOfCode._2021.Day01 as Day01

final class Day01_Test extends TestSuite:
  val samplePuzzle: List[Int] = List(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

  test("answer 1") {
    Day01.solve_1(samplePuzzle) `shouldBe` 7
  }

  test("aswwer 2") {
    Day01.solve_2(samplePuzzle) `shouldBe` 5
  }