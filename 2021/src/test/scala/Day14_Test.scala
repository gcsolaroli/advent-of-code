package it.imolab.adventOfCode._2021

final class Day14_Test extends TestSuite:
  val samplePuzzle: List[String] = List(
    "NNCB",
    "",
    "CH -> B",
    "HH -> N",
    "CB -> H",
    "NH -> C",
    "HB -> C",
    "HC -> B",
    "HN -> C",
    "NN -> C",
    "BH -> H",
    "NC -> B",
    "NB -> B",
    "BN -> B",
    "BB -> N",
    "BC -> B",
    "CC -> N",
    "CN -> C",
    "",
  )

  test("answer 1") {
    Day14.solve_1(samplePuzzle, 10) `shouldBe` 1588
    Day14.solve_2(samplePuzzle, 10) `shouldBe` BigInt("1588")
  }

  test("answer 2") {
    Day14.solve_2(samplePuzzle, 40) `shouldBe` BigInt("2188189693529")
  }