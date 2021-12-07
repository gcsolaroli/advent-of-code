package it.imolab.adventOfCode._2021

final class Day07_Test extends TestSuite:
  val samplePuzzle: List[Integer] = List(16,1,2,0,4,2,7,1,2,14)

  test("answer 1") {
    Day07.solve_1(samplePuzzle) `shouldBe` 37
  }

  test("answer 2") {
    Day07.solve_2(samplePuzzle) `shouldBe` 168
  }