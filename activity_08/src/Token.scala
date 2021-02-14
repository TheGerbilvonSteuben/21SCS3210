/*
 * CS3210 - Principles of Programming Languages - Spring 2021
 * Instructor: Thyago Mota
 * Description: Activity 08 - Token
 * Student(s) Name(s):
 */

object Token extends Enumeration {
  val EOF             = Value
  val ADDITION        = Value // +
  val SUBTRACTION     = Value // -
  val MULTIPLICATION  = Value // *
  val DIVISION        = Value // /
  val IDENTIFIER      = Value
  val LITERAL         = Value
  val OPEN_PAR        = Value // (
  val CLOSE_PAR       = Value // )
}
