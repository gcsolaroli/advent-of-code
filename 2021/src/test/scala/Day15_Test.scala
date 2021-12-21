package it.imolab.adventOfCode._2021

final class Day15_Test extends TestSuite:
  val samplePuzzle: List[String] = List(
    "1163751742",
    "1381373672",
    "2136511328",
    "3694931569",
    "7463417111",
    "1319128137",
    "1359912421",
    "3125421639",
    "1293138521",
    "2311944581",
  )

  test("answer 1") {
    Day15.solve_1(samplePuzzle) `shouldBe` 40
  }

  test("answer 2") {
    Day15.solve_2(samplePuzzle) `shouldBe` 315
  }