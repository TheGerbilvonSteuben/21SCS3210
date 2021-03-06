; CS3210 - Principles of Programming Languages
; Intructor: Thyago Mota
; Description: Performing a simple user IO

; class directive
.class SimpleIO
.super java/lang/Object

; method directive
.method public static main([Ljava/lang/String;)V

  ; limit the stack's size
  .limit stack 5

  ; number of local variables
  .limit locals 2

  ; display a prompt
  getstatic java/lang/System/out Ljava/io/PrintStream;
  ldc "Name? "
  invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

  ; instantiate Scanner
  new java/util/Scanner
  dup
  astore 0
  getstatic java/lang/System/in Ljava/io/InputStream;
  invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V

  ; call readLine on Scanner
  aload 0
  invokevirtual java/util/Scanner/nextLine()Ljava/lang/String;
  astore 1

  ; display the input
  getstatic java/lang/System/out Ljava/io/PrintStream;
  aload 1
  invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

  ; invoke return on main
	return

; end the method definition
.end method
