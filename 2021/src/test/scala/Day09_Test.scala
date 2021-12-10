package it.imolab.adventOfCode._2021

final class Day09_Test extends TestSuite:
  val samplePuzzle: List[String] = List(
    "2199943210",
    "3987894921",
    "9856789892",
    "8767896789",
    "9899965678"
  )

  test("answer 1") {
    Day09.solve_1(samplePuzzle) `shouldBe` 15
  }

  test("answer 2") {
    Day09.solve_2(samplePuzzle) `shouldBe` 1134
  }