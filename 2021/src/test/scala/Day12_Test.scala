package it.imolab.adventOfCode._2021

final class Day12_Test extends TestSuite:
  val samplePuzzle_1: List[String] = List(
    "start-A",
    "start-b",
    "A-c",
    "A-b",
    "b-d",
    "A-end",
    "b-end",
  )

  val samplePuzzle_2: List[String] = List(
    "dc-end",
    "HN-start",
    "start-kj",
    "dc-start",
    "dc-HN",
    "LN-dc",
    "HN-end",
    "kj-sa",
    "kj-HN",
    "kj-dc",
  )

  val samplePuzzle_3: List[String] = List(
    "fs-end",
    "he-DX",
    "fs-he",
    "start-DX",
    "pj-DX",
    "end-zg",
    "zg-sl",
    "zg-pj",
    "pj-he",
    "RW-he",
    "fs-DX",
    "pj-RW",
    "zg-RW",
    "start-pj",
    "he-WI",
    "zg-he",
    "pj-fs",
    "start-RW",
  )

  test("answer 1") {
    Day12.solve_1(samplePuzzle_1) `shouldBe` 10
    Day12.solve_1(samplePuzzle_2) `shouldBe` 19
    Day12.solve_1(samplePuzzle_3) `shouldBe` 226
  }

  test("answer 2") {
    Day12.solve_2(samplePuzzle_1) `shouldBe` 36
    Day12.solve_2(samplePuzzle_2) `shouldBe` 103
    Day12.solve_2(samplePuzzle_3) `shouldBe` 3509
  }