package it.imolab.adventOfCode._2021

final class Day06_Test extends TestSuite:
  val samplePuzzle: List[Int] = List(3, 4, 3, 1, 2)

  test("answer 1") {
    Day06.solve_1(samplePuzzle) `shouldBe` 5934
  }

  test("answer 2") {
    Day06.solve_2(samplePuzzle) `shouldBe` BigInt("26984457539")
  }