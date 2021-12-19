package it.imolab.adventOfCode._2021.Day14

import scala.io.Source

private val puzzle: List[String] = Source.fromFile("2021/src/main/resources/Day14.input.txt").getLines.toList

/*
The incredible pressures at this depth are starting to put a strain on your submarine.
The submarine has polymerization equipment that would produce suitable materials to reinforce the submarine,
and the nearby volcanically-active caves should even have the necessary input elements in sufficient quantities.

The submarine manual contains instructions for finding the optimal polymer formula; specifically, it offers a
polymer template and a list of pair insertion rules (your puzzle input).
You just need to work out what polymer would result after repeating the pair insertion process a few times.

For example:
NNCB

CH -> B
HH -> N
CB -> H
NH -> C
HB -> C
HC -> B
HN -> C
NN -> C
BH -> H
NC -> B
NB -> B
BN -> B
BB -> N
BC -> B
CC -> N
CN -> C

The first line is the polymer template - this is the starting point of the process.

The following section defines the pair insertion rules. A rule like AB -> C means that when elements A and B are immediately adjacent,
element C should be inserted between them. These insertions all happen simultaneously.

So, starting with the polymer template NNCB, the first step simultaneously considers all three pairs:
The first pair (NN) matches the rule NN -> C, so element C is inserted between the first N and the second N.
The second pair (NC) matches the rule NC -> B, so element B is inserted between the N and the C.
The third pair (CB) matches the rule CB -> H, so element H is inserted between the C and the B.
Note that these pairs overlap: the second element of one pair is the first element of the next pair.
Also, because all pairs are considered simultaneously, inserted elements are not considered to be part of a pair until the next step.

After the first step of this process, the polymer becomes NCNBCHB.

Here are the results of a few steps using the above rules:
Template:     NNCB
After step 1: NCNBCHB
After step 2: NBCCNBBBCBHCB
After step 3: NBBBCNCCNBBNBNBBCHBHHBCHB
After step 4: NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB
This polymer grows quickly. After step 5, it has length 97; After step 10, it has length 3073.
After step 10, B occurs 1749 times, C occurs 298 times, H occurs 161 times, and N occurs 865 times;
taking the quantity of the most common element (B, 1749) and subtracting the quantity of the least common element (H, 161) produces 1749 - 161 = 1588.

Apply 10 steps of pair insertion to the polymer template and find the most and least common elements in the result.
What do you get if you take the quantity of the most common element and subtract the quantity of the least common element?
*/
def solve_1 (p: List[String], steps: Int): Int =
  val (initialSequence, insertionRules) = parseInput(p)
  val newSequence = runRules(initialSequence, insertionRules, steps)
  val groupped = newSequence.groupBy(identity).map(t => t._2.length)
  groupped.max - groupped.min

// ===================================================================

/*
The resulting polymer isn't nearly strong enough to reinforce the submarine.
You'll need to run more steps of the pair insertion process; a total of 40 steps should do it.

In the above example, the most common element is B (occurring 2192039569602 times) and the least common element is H (occurring 3849876073 times);
subtracting these produces 2188189693529.

Apply 40 steps of pair insertion to the polymer template and find the most and least common elements in the result.
What do you get if you take the quantity of the most common element and subtract the quantity of the least common element?
*/
def solve_2 (p: List[String], steps: Int): BigInt =
  val (initialSequence, insertionRules) = parseInput(p)
  val elements = applySostitutionRules(initialSequence, insertionRules, steps)
  val elementCounts = elements.values.toList
  elementCounts.max - elementCounts.min


@main def answer_1 = println("2021 - Day 14 - answer 1: " + solve_1(puzzle, 10))

@main def answer_2 = println("2021 - Day 14 - answer 2: " + solve_2(puzzle, 40))

// ===========================================================

private def parseInput (p: List[String]): (String, Map[String, String]) =
  val (initialSequence, rules) = p.span(s => !s.isEmpty)

  val insertionRules = rules.filter(s => !s.isEmpty).map(s => {
    val splitPattern = """([A-Z][A-Z]) -> ([A-Z])""".r
    val m = splitPattern.findAllIn(s).matchData.next
    (m.group(1), m.group(2))
  }).toMap

  (initialSequence(0), insertionRules)

private def runRules (sequence: String, rules: Map[String, String], steps: Int) : String =
  if steps == 0 then
    sequence
  else
    val updatedSequence = s"""${sequence(0)}""" + sequence.sliding(2)
      .map(s => rules.get(s).map(r => s"""${s(0)}${r}${s(1)}""").getOrElse(s))
      .map(s => s.drop(1))
      .mkString

    runRules(updatedSequence, rules, steps-1)

// ----------------------------------------------------------------------------

private def applySostitutionRules (sequence: String, rules: Map[String, String], steps: Int): Map[Char, BigInt] =
  def addToMap (map: Map[Char, BigInt], char: Char, value: BigInt) : Map[Char, BigInt] =
    map + (char -> (map.get(char).getOrElse(BigInt(0)) + value))

  def mergeMaps (m1: Map[Char, BigInt], m2: Map[Char, BigInt]): Map[Char, BigInt] =
    (m1.keySet ++ m2.keySet).foldLeft(Map.empty[Char, BigInt])((m, k) => addToMap(m, k, (m1.get(k).getOrElse(BigInt(0)) + m2.get(k).getOrElse(BigInt(0)))))

  val replacements : Map[String, List[String]] = rules.map((k, v) => (k -> List(s"""${k(0)}${v}""", s"""${v}${k(1)}""")))
  
  val cachedValues: Map[Int, Map[String, Map[Char, BigInt]]] =
    (0 to steps)
      .foldLeft(Map.empty[Int, Map[String, Map[Char, BigInt]]])((a, s) =>
        a + (s -> replacements.keySet.foldLeft(Map.empty[String, Map[Char, BigInt]])((aa, kk) =>
          aa + {
            if s == 0 then
              (kk -> Map(kk(1) -> BigInt(1)))
            else
              (kk -> replacements.getOrElse(kk, List()).foldLeft(Map.empty)((aaa, kkk) => mergeMaps(aaa, a.getOrElse(s - 1, Map.empty).getOrElse(kkk, Map.empty))))
          }
        ))
      )

  sequence.sliding(2)
  .foldRight(Map((sequence(0) -> BigInt(1))))((t, a) => mergeMaps(a, cachedValues.getOrElse(steps, Map.empty).getOrElse(t, Map.empty)))
