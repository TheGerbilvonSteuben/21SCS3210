/*
 * CS3210 - Principles of Programming Languages - Spring 2021
 * Instructor: Thyago Mota
 * Description: Activity 07 - Symbol (a String label)
 */

class Symbol(private var label: String) {

  def getLabel() = label

  def setLabel(label: String) = { this.label = label }

  override def toString: String = "(label:" + label + ")"
}
