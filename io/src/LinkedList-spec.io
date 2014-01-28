describe(linkedList, LinkedList,
  setup(
    l      := LinkedList with(1, 2, 3, 4, 5)
    nested := LinkedList with(1, LinkedList with(2, LinkedList with(3, 4), 5))
    dup    := LinkedList with(1, 1, 1, 1, 2, 3, 3, 1, 1, 4, 5, 5, 5, 5)
  )

  teardown(
    l = nil
  )

  linkedList("01) Find the last item of a List.",
    l verify(last == 5)
  )

  linkedList("02) Find the last but one item of a List.",
    l verify(lastButOne == 4)
  )

  linkedList("03) Find the K'th element of a list (1-index).",
    l verify(at(3) == 3)
  )

  linkedList("04) Find the number of elements of a list.",
    l verify(size == 5)
  )

  linkedList("05) Reverse a list.",
    l reverse verify(asString == "5:4:3:2:1:()")
  )

  linkedList("06) Find out whether a List is a palindrome.",
    l verify(isPalindrome not)
    LinkedList with(1, 2, 1) verify(isPalindrome)
  )

  linkedList("07) Flatten a nested list structure.",
    nested flatten verify(asString == l asString)
  )

  linkedList("08) Eliminate consecutive duplicates of List elements.",
    dup compress verify(asString == "1:2:3:1:4:5:()")
  )

  linkedList("09) Pack consecutive duplicates of List elements into sublists.",
    dup pack verify(asString == "(1:1:1:1:()):(2:()):(3:3:()):(1:1:()):(4:()):(5:5:5:5:()):()")
  )

  linkedList("10) Run-length encoding of a list.",
    dup encode verify(asString == "(4:1:()):(1:2:()):(2:3:()):(2:1:()):(1:4:()):(4:5:()):()")
  )

  linkedList("11) Modified run-length encoding.",
    dup encode2 verify(asString == "(4:1:()):2:(2:3:()):(2:1:()):4:(4:5:()):()")
  )

  linkedList("12) Decode a run-length encoded list.",
    dup encode2 decode verify(asString == dup asString)
  )


)