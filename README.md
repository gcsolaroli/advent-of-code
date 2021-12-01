# Advent of code container project

This project contains code used to solve [Advent of Code](https://adventofcode.com) puzzles.

Puzzles are solved using **Scala 3**.

## What you need

In order to run this project, you need to use `sbt`.

You can find more detailed instructions on how to setup your environment [here](https://www.scala-lang.org/download/scala3.html).

## Multi Year project

It may be a little bit of overthinking, but the `sbt` project is structured to contain solutions for puzzle for multiple years. 😅

Once the `sbt` prompt shows up, you need to *switch* to the current year in order to run its puzzles:

    sbt:AdventOfCode> l
    [info] In file:/Volumes/Backup%20CURA/Workarea/personal/Advent_of_Code/scala/
    [info]   * AdventOfCode
    [info]     Year_2021
    sbt:AdventOfCode> cd Year_2021
    [info] set current project to 2021 (in build file:…)
    sbt:Year_2021> 

## Solving puzzles

### Puzzle inputs

In order to run puzzles, you first need to replace the puzzle **inputs** (the ones included in the project are [@gcsolaroli](https://github.com/gcsolaroli)'s ones); these files are located in the `<YEAR>/src/main/resources` folder.

It should be enough to just save the page with your input into the matching file (named `Day__.input.txt`) as plain text.

### Run solutions

Once started `sbt`, in order to run a solution, you just need to use the `runMain` command. eg:

    runMain it.imolab.adventOfCode._2021.Day01.answer_1

`sbt` provided an autocompletion feature, so you don't have to type the fully qualified name of the method you want to run, but you can use `tab` key to let `sbt` helps you in selecting the right method.