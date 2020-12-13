; CS3210 - Principles of Programming Languages
; Intructor: Thyago Mota
; Description: Displaying "Hello Jasmin!"

; class directive
.class Hello
.super java/lang/Object

; method directive
; type descriptors always start with L (as in "local"?) and it is followed by the full path to the type, ending in ;
; the [ denotes a static array
; V is short for void
.method public static main([Ljava/lang/String;)V

	; limiting the stack's size
	.limit stack 2

	; a method call in Jasmin:
	; 1. push the callee onto the stack using getstatic (because the callee is a static field in System.out)
	getstatic java/lang/System/out Ljava/io/PrintStream;

	; 2. push required parameters onto the stack using ldc; in the example, the parameter is a constant String
	ldc "Hello Jasmin!"

	; 3. make the method call using invokevirtual
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	; invoke return on main
	return

; end the method definition
.end method