package it.imolab.adventOfCode._2021

final class Day01_Test extends TestSuite:
  val samplePuzzle: List[Int] = List(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

  test("answer 1") {
    Day01.solve_1(samplePuzzle) `shouldBe` 7
  }

  test("answer 2") {
    Day01.solve_2(samplePuzzle) `shouldBe` 5
  }