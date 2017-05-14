package vendor

import scala.io.Source.fromFile

class CommandParser extends ProgramParser {
  /**
    * Parses a file representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param file the file to parse
    * @return an instruction list
    */

  def parse(file: String): InstructionList =
    fromFile(file).getLines().map(parseLine).toVector

  /**
    * Parses a string representation of a bytecode program
    * into an `InstructionList`.
    *
    * @param string the string to parse
    * @return an instruction list
    */
  def parseString(string: String): InstructionList =
    string.split ("\n").map(parseLine).toVector

  /**
    * Parse a line into a single instruction
    */
  def parseLine(line: String): Instruction = {
    val tokens = line.split (" ")
    val name = tokens.head
    val args: Vector[Int] = tokens.tail.filter (_.length > 0).map (x => x.toInt).toVector
    new Instruction (name, args)
  }
}