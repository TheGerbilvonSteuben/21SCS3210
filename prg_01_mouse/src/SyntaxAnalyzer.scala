/*
 * CS3210 - Principles of Programming Languages - Spring 2021
 * Instructor: Thyago Mota
 * Description: Prg 01 - SyntaxAnalyzer (an iterable syntax analyzer)
 * Student(s) Name(s):
 */

class SyntaxAnalyzer(private var source: String) {

  private val it = new LexicalAnalyzer(source).iterator
  private var current: Lexeme = null

  // returns the current lexeme
  private def getLexeme(): Lexeme = {
    if (current == null) {
      current = it.next
    }
    current
  }

  // advances the input one lexeme
  private def nextLexeme() = {
    current = it.next
  }

  // TODO: parses the program, returning its corresponding parse tree
  def parse(): Tree = {
  }
}

object SyntaxAnalyzer {
  def main(args: Array[String]): Unit = {

    // check if source file was passed through the command-line
    if (args.length != 1) {
      print("Missing source file!")
      System.exit(1)
    }

    val syntaxAnalyzer = new SyntaxAnalyzer(args(0))
    val parseTree = syntaxAnalyzer.parse()
    print(parseTree)
  }
}
