package it.imolab.adventOfCode._2021

final class Day10_Test extends TestSuite:
  val samplePuzzle: List[String] = List(
    "[({(<(())[]>[[{[]{<()<>>",
    "[(()[<>])]({[<{<<[]>>(",
    "{([(<{}[<>[]}>{[]{[(<()>",
    "(((({<>}<{<{<>}{[]{[]{}",
    "[[<[([]))<([[{}[[()]]]",
    "[{[{({}]{}}([{[{{{}}([]",
    "{<[[]]>}<{[{[{[]{()[[[]",
    "[<(<(<(<{}))><([]([]()",
    "<{([([[(<>()){}]>(<<{{",
    "<{([{{}}[<[[[<>{}]]]>[]]",
  )

  test("answer 1") {
    Day10.solve_1(samplePuzzle) `shouldBe` 26397
  }

  test("answer 2") {
    Day10.solve_2(samplePuzzle) `shouldBe` BigInt(288957)
  }