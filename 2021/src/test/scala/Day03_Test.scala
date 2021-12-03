package it.imolab.adventOfCode._2021

final class Day03_Test extends TestSuite:
  val samplePuzzle: List[String] = List(
    "00100",
    "11110",
    "10110",
    "10111",
    "10101",
    "01111",
    "00111",
    "11100",
    "10000",
    "11001",
    "00010",
    "01010"
  )

  test("answer 1") {
    Day03.solve_1(samplePuzzle) `shouldBe` 198
  }

  test("answer 2") {
    Day03.solve_2(samplePuzzle) `shouldBe` 230
  }