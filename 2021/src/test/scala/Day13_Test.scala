package it.imolab.adventOfCode._2021

final class Day13_Test extends TestSuite:
  val samplePuzzle: List[String] = List(
    "6,10",
    "0,14",
    "9,10",
    "0,3",
    "10,4",
    "4,11",
    "6,0",
    "6,12",
    "4,1",
    "0,13",
    "10,12",
    "3,4",
    "3,0",
    "8,4",
    "1,10",
    "2,14",
    "8,10",
    "9,0",
    "",
    "fold along y=7",
    "fold along x=5",
  )

  test("answer 1") {
    Day13.solve_1(samplePuzzle) `shouldBe` 17
  }

  // test("answer 2") {
  //   Day13.solve_2(samplePuzzle) `shouldBe` 2
  // }