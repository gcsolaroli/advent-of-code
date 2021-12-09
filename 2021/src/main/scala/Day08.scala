package it.imolab.adventOfCode._2021.Day08

import scala.io.Source

private val puzzle: List[String] = Source.fromFile("2021/src/main/resources/Day08.input.txt").getLines.toList

/*
You barely reach the safety of the cave when the whale smashes into the cave mouth, collapsing it.
Sensors indicate another exit to this cave at a much greater depth, so you have no choice but to press on.

As your submarine slowly makes its way through the cave system, you notice that the four-digit seven-segment
displays in your submarine are malfunctioning; they must have been damaged during the escape.
You'll be in a lot of trouble without them, so you'd better figure out what's wrong.

Each digit of a seven-segment display is rendered by turning on or off any of seven segments named a through g:

  0:      1:      2:      3:      4:
 aaaa    ....    aaaa    aaaa    ....
b    c  .    c  .    c  .    c  b    c
b    c  .    c  .    c  .    c  b    c
 ....    ....    dddd    dddd    dddd
e    f  .    f  e    .  .    f  .    f
e    f  .    f  e    .  .    f  .    f
 gggg    ....    gggg    gggg    ....

  5:      6:      7:      8:      9:
 aaaa    aaaa    aaaa    aaaa    aaaa
b    .  b    .  .    c  b    c  b    c
b    .  b    .  .    c  b    c  b    c
 dddd    dddd    ....    dddd    dddd
.    f  e    f  .    f  e    f  .    f
.    f  e    f  .    f  e    f  .    f
 gggg    gggg    ....    gggg    gggg
So, to render a 1, only segments c and f would be turned on; the rest would be off.
To render a 7, only segments a, c, and f would be turned on.

The problem is that the signals which control the segments have been mixed up on each display.
The submarine is still trying to display numbers by producing output on signal wires a through g,
but those wires are connected to segments randomly. Worse, the wire/segment connections are mixed
up separately for each four-digit display! (All of the digits within a display use the same connections, though.)

So, you might know that only signal wires b and g are turned on, but that doesn't mean segments b and g are turned on:
the only digit that uses two segments is 1, so it must mean segments c and f are meant to be on.
With just that information, you still can't tell which wire (b/g) goes to which segment (c/f).
For that, you'll need to collect more information.

For each display, you watch the changing signals for a while, make a note of all ten unique signal patterns you see,
and then write down a single four digit output value (your puzzle input).
Using the signal patterns, you should be able to work out which pattern corresponds to which digit.

For example, here is what you might see in a single entry in your notes:

acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf
(The entry is wrapped here to two lines so it fits; in your notes, it will all be on a single line.)

Each entry consists of ten unique signal patterns, a | delimiter, and finally the four digit output value.
Within an entry, the same wire/segment connections are used (but you don't know what the connections actually are).
The unique signal patterns correspond to the ten different ways the submarine tries to render a digit using the
current wire/segment connections. Because 7 is the only digit that uses three segments, dab in the above example
means that to render a 7, signal lines d, a, and b are on. Because 4 is the only digit that uses four segments, eafb
means that to render a 4, signal lines e, a, f, and b are on.

Using this information, you should be able to work out which combination of signal wires corresponds to each of the ten digits.
Then, you can decode the four digit output value. Unfortunately, in the above example, all of the digits in the output value
(cdfeb fcadb cdfeb cdbaf)
use five segments and are more difficult to deduce.

For now, focus on the easy digits. Consider this larger example:

be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | _fdgacbe_ cefdb cefbgd _gcbe_
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb _cgb_ _dgebacf_ _gc_
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | _cg_ _cg_ fdcagb _cbg_
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec _cb_
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | _gecf_ _egdcabf_ _bgf_ bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | _gebdcfa_ _ecba_ _ca_ _fadegcb_
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | _cefg_ dcbef _fcge_ _gbcadfe_
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | _ed_ bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | _gbdfcae_ _bgc_ _cg_ _cgb_
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | _fgae_ cfgab _fg_ bagce

Because the digits 1, 4, 7, and 8 each use a unique number of segments, you should be able to tell which combinations of signals correspond to those digits.
Counting only digits in the output values (the part after | on each line), in the above example, there are 26 instances of digits that use a unique number
of segments (highlighted above).

In the output values, how many times do digits 1, 4, 7, or 8 appear?
*/
def solve_1 (p: List[String]): Int = 
  p.map(parseInput)
    .flatMap(_._2)
    .filter(d => List(2, 3, 4, 7).contains(d.pattern.length))
    .size

// ===================================================================

/*
Through a little deduction, you should now be able to determine the remaining digits.
Consider again the first example above:

acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf
After some careful analysis, the mapping between signal wires and segments only make sense in the following configuration:

 dddd
e    a
e    a
 ffff
g    b
g    b
 cccc
So, the unique signal patterns would correspond to the following digits:

acedgfb: 8
cdfbe: 5
gcdfa: 2
fbcad: 3
dab: 7
cefabd: 9
cdfgeb: 6
eafb: 4
cagedb: 0
ab: 1
Then, the four digits of the output value can be decoded:

cdfeb: 5
fcadb: 3
cdfeb: 5
cdbaf: 3
Therefore, the output value for this entry is 5353.

Following this same process for each entry in the second, larger example above, the output value of each entry can be determined:

fdgacbe cefdb cefbgd gcbe: 8394
fcgedb cgb dgebacf gc: 9781
cg cg fdcagb cbg: 1197
efabcd cedba gadfec cb: 9361
gecf egdcabf bgf bfgea: 4873
gebdcfa ecba ca fadegcb: 8418
cefg dcbef fcge gbcadfe: 4548
ed bcgafe cdgba cbgef: 1625
gbdfcae bgc cg cgb: 8717
fgae cfgab fg bagce: 4315
Adding all of the output values in this larger example produces 61229.

For each entry, determine all of the wire/segment connections and decode the four-digit output values.
What do you get if you add up all of the output values?

*/

def solve_2 (p: List[String]): Int =
  val signalMatching = Map(
    List('a', 'b', 'c', 'e', 'f', 'g')      -> '0',
    List('c', 'f')                          -> '1',
    List('a', 'c', 'd', 'e', 'g')           -> '2',
    List('a', 'c', 'd', 'f', 'g')           -> '3',
    List('b', 'c', 'd', 'f')                -> '4',
    List('a', 'b', 'd', 'f', 'g')           -> '5',
    List('a', 'b', 'd', 'e', 'f', 'g')      -> '6',
    List('a', 'c', 'f')                     -> '7',
    List('a', 'b', 'c', 'd', 'e', 'f', 'g') -> '8',
    List('a', 'b', 'c', 'd', 'f', 'g')      -> '9'
  )

  val t = signalMatching.transform((k, v) => (k, v)).values

  // val decodedSignals : Map[Char, Char] = Map('a' -> 'a', 'b' -> 'b', 'c' -> 'c', 'd' -> 'd', 'e' -> 'e', 'f' -> 'f', 'g' -> 'g')

  def decodeValues (input: (List[Digit], List[Digit])): Int =
    def encode (signals: List[Char])(c: Char): Char = signals(c.toInt - 'a'.toInt)

    def isValidPermutation (inputs: List[Digit])(permutation: List[Char]): Boolean =
      inputs.forall(d => signalMatching.keySet.contains(d.pattern.map(encode(permutation)).toList.sorted))

    def toDigit (segments: List[Char]): Char = signalMatching.get(segments.sorted).getOrElse('0')

    val validPermutation = List('a', 'b', 'c', 'd', 'e', 'f', 'g').permutations.filter(isValidPermutation(input._1)).toList

    input._2.map(d => toDigit(d.pattern.map(encode(validPermutation(0))).toList)).mkString.toInt

  p .map(parseInput)
    .map(decodeValues)
    .sum

  // println(s"x: ${x.mkString}")
  // 2


@main def answer_1 = println("2021 - Day 08 - answer 1: " + solve_1(puzzle))

@main def answer_2 = println("2021 - Day 08 - answer 2: " + solve_2(puzzle))


// ===============================================

// private case class SignalPattern(pattern: String)
private case class Digit(pattern: String)

private def parseInput (s: String) : (List[Digit], List[Digit]) =
  //  acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf
  val splitPattern = """([[a-z]\s]+) \| ([[a-z]\s]+)""".r
  val m = splitPattern.findAllIn(s).matchData.next
  val signals = m.group(1)
  val outputs = m.group(2)

  def parseDigits (s: String) : List[Digit] =
    val pattern = """([a-z]+)""".r
    val matches = pattern.findAllIn(s).matchData
    matches.map(m => Digit(m.toString)).toList

  (parseDigits(signals), parseDigits(outputs))

  