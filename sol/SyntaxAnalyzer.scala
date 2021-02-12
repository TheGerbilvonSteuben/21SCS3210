/*
 * CS3210 - Principles of Programming Languages - Spring 2021
 * Instructor: Thyago Mota
 * Description: Activity 07 - SyntaxAnalyzer (an iterable syntax analyzer)
 * Student(s) Name(s):
 */

/*
expression  = term expression'
expression' = ( ´+´  | ´-´ ) term expression' | epsilon
term        = factor term'
term'       = ( ´*´ | ´/´ ) factor term' | epsilon
factor      = identifier | literal | ´(´ expression ´)´
identifier  = letter { ( letter | digit ) }
letter      = ´a´ | ´b´ | ´c´ | ´d´ | ´e´ | ´f´ | ´g´ | ´h´ | ´i´ | ´j´ | ´k´ | ´l´ | ´m´ | ´n´ | ´o´ | ´p´ | ´q´ | ´r´ | ´s´ | ´t´ | ´u´ | ´v´ | ´w´ | ´x´ | ´y´ | ´z´
literal     = digit { digit }
digit       = ´0´ | ´1´ | ´2´ | ´3´ | ´4´ | ´5´ | ´6´ | ´7´ | ´8´ | ´9´
*/

class SyntaxAnalyzer(private var source: String) {

  private val it = new LexicalAnalyzer(source).iterator
  private var current: Lexeme = null

  // returns the current lexeme
  private def getLexeme(): Lexeme = {
    if (current == null) {
      current = it.next
    }
    //    println(current)
    current
  }

  // advances the input one lexeme
  private def nextLexeme() = {
    current = it.next
  }

  // parses the program, returning its corresponding parse tree
  def parse() = {
    parseExpr()
  }

  // expression  = term expression'
  private def parseExpr() = {

    // TODOd: create a parse tree with non-terminal value "expression"
    var tree = new Tree("expression")

    // TODOd: parse a term
    tree.add(parseTerm())

    // TODOd: parse a expressionPrime
    tree.add(parseExprPrime())

    // TODOd: return the parse tree
    tree
  }

  // term = factor term'
  private def parseTerm() = {

    // TODOd: create a parse tree with non-terminal value "expression"
    var tree = new Tree("term")

    // TODOd: parse a factor
    tree.add(parseFactor())

    // TODOd: parse a termPrime
    tree.add(parseTermPrime())

    // TODOd: return the parse tree
    tree
  }

  // factor = identifier | literal | ´(´ expression ´)´
  private def parseFactor(): Tree = {

    // TODOd: create a parse tree with non-terminal value "factor"
    var tree = new Tree("factor")

    var lexeme = getLexeme()
    if (lexeme.getToken() == Token.IDENTIFIER || lexeme.getToken() == Token.LITERAL) {
      tree.add(new Tree(lexeme.getLabel()))  // or tree.add(new Tree(lexeme))
      nextLexeme() // call nextLexeme when you are done consuming a token
    }
    else if (lexeme.getToken() == Token.OPEN_PAR) {
      tree.add(new Tree(lexeme.getLabel()))
      nextLexeme()
      tree.add(parseExpr())
      lexeme = getLexeme()
      if (lexeme.getToken() == Token.CLOSE_PAR) {
        tree.add(new Tree(lexeme.getLabel()))
        nextLexeme()
      }
      else
        throw new Exception("Syntax Analyzer Error: \"closing parenthesis\" expected!")
    }
    else
      throw new Exception("Syntax Analyzer Error: identifier, literal, or \"opening parenthesis\" expected!")

    tree // return the tree (don't forget)
  }

  // term' = ( ´*´ | ´/´ ) factor term' | epsilon
  def parseTermPrime(): Tree = {

    var tree = new Tree("termPrime")

    var lexeme = getLexeme()
    if (lexeme.getToken() == Token.MULTIPLICATION || lexeme.getToken() == Token.DIVISION) {
      tree.add(new Tree(lexeme.getLabel()))
      nextLexeme()
      tree.add(parseFactor())
      tree.add(parseTermPrime())
    }
    else
      tree.add(new Tree("epsilon"))

    tree
  }

  // expression' = ( ´+´  | ´-´ ) term expression' | epsilon
  def parseExprPrime(): Tree = {

    var tree = new Tree("exprPrime")

    var lexeme = getLexeme()
    if (lexeme.getToken() == Token.ADDITION || lexeme.getToken() == Token.SUBTRACTION) {
      tree.add(new Tree(lexeme.getLabel()))
      nextLexeme()
      tree.add(parseTerm())
      tree.add(parseExprPrime())
    }
    else
      tree.add(new Tree("epsilon"))

    tree
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
