RangeException := Exception clone

Cons := Object clone do(
  with := method(first, /* rest, */
    result         := clone
    result msg     := call
    result head    := first
    result tail    := lazySlot(msg evalArgAt(1))
    result isEmpty := false
    result
  )

  isEmpty := true

  asString := method(
    if(isEmpty,
      "()"
    ,
      if(head isKindOf(Cons),
        "(#{head}):#{tail}" interpolate
      ,
        "#{head}:#{tail}" interpolate
      )
    )
  )
)

ListSemigroup := Object clone do(
  // @Cons a => Cons a -> Cons a
  concat := method(xs,
    """Concatenates two conses."""
    if (xs isEmpty,
      self
    ,
      if(self isEmpty,
        xs
      ,
        if(tail isEmpty,
          cons(head, xs)
        ,
          cons(head, tail concat(xs))
        )
      )
    )
  )
)

ListMonoid := Object clone do(
  empty := method(
    """Constructs a new empty cons."""
    cons(nil, nil) do(
      isEmpty := true
    )
  )
)

ListFunctor := Object clone do(
  // @Cons a => (a -> b) -> Cons b
  map := method(f,
    """Transforms each item in the List by way of `f`."""
    cons(f call(head), if(tail isEmpty,
                         tail
                       ,
                         tail map(f)))
  )
)

ListMonad := Object clone do(
  // @Cons a => (a -> stream b) -> stream b
  chain := method(f,
    """Monadic bind"""
    f call(head)
  )

  // a -> Cons a
  of := method(a,
    """Shoves something into a Monad."""
    cons(a, empty)
  )
)

ListFoldable := Object clone do(
  // @Cons a => b, (a, b -> b) -> b
  foldRight := method(initial, f,
    """Right associative fold."""
    if(tail isEmpty,
      f call(head, initial)
    ,
      f call(head, tail foldRight(initial, f))
    )
  )

  every := method(f,
    """True if `f` applies to all items in the list."""
    if(isEmpty,
      true
    ,
      f call(head) and(tail every(f))
    )
  )

  mapConcat := method(f,
    """Applies `f`, which should return a list, concats the list."""
    foldRight(empty, block(x, xs,
      f call(x) concat(xs)
    ))
  )

  flatten := method(
    """Flattens a newsted list structure."""
    mapConcat(block(x,
      if(x hasSlot("flatten"),
        x flatten
      ,
        of(x)
      )
    ))
  )
)

ListRepeatable := Object clone do(
  replicate := method(times,
    """Replicates itself a given number of times."""
    if(times <= 1,
      self
    ,
      self concat(replicate(times - 1))
    )
  )
)

ListPacking := Object clone do(
  compress := method(
    """Removes consecutive duplicates of a List."""
    foldRight(empty, block(x, xs,
      if(xs isEmpty,
        cons(x, empty)
      ,
        if(xs head == x,
          xs
        ,
          cons(x, xs)
        )
      )
    ))
  )

  pack := method(
    """Packs consecutive duplicates of List elements into sublists."""
    foldRight(empty, block(x, xs,
      if(xs isEmpty,
        of(of(x))
      ,
        if(xs head head == x,
          of(cons(x, xs head)) concat(xs tail)
        ,
          of(of(x)) concat(xs)
        )
      )
    ))
  )

  encode := method(
    """Run-length encoding of a list."""
    pack map(block(xs, cons(xs size, xs of(xs head))))
  )

  encode2 := method(
    """Run-length encoding of a list (only puts duplicates in sublists)"""
    pack map(block(xs,
      if(xs size == 1,
        xs head
      ,
        cons(xs size, xs of(xs head))
      )
    ))
  )

  decode := method(
    """Decode a run-length encoded list."""
    mapConcat(block(xs,
      if(xs isKindOf(Cons),
        xs tail replicate(xs head)
      ,
        of(xs)
      )
    ))
  )
)

ListZippable := Object clone do(
  zipWith := method(xs, f,
    """Zips two lists using `f` as glue."""
    if(tail isEmpty or(xs tail isEmpty),
      cons(f call(head, xs head), empty)
    ,
      cons(f call(head, xs head), tail zipWith(xs tail, f))
    )
  )

  zip := method(xs,
    """Zips two lists together with cons."""
    zipWith(xs, block(a, b, cons(a, b)))
  )
)

ListSliceable := Object clone do(
  butLast := method(
    """Takes every value but the last in a List."""
    if(tail isEmpty,
      empty
    ,
      cons(head, tail butLast)
    )
  )

  last := method(
    """Returns the last item of a List."""
    if(tail isEmpty,
      head
    ,
      tail last
    )
  )

  lastButOne := method(
    """Returns the last but one item of a List."""
    if(tail tail isEmpty,
      head
    ,
      tail lastButOne
    )
  )

  at := method(position,
    """Finds the K'th element of a List - 1-based index."""
    if(position < 1,
      RangeException raise("Position should be >= 1")
    ,
      if(position == 1,
        head
      ,
        tail at(position - 1)
      )
    )
  )
)

ListFinite := Object clone do(
  size := method(
    """Calculates the number of items in a list."""
    foldRight(0, block(_, acc, acc + 1))
  )
)

ListSortable := Object clone do(
  reverse := method(
    """Sorts a list in reverse order."""
    foldRight(empty, block(x, xs,
      if(xs isEmpty
        cons(x, empty)
      ,
        xs concat(cons(x, empty))
      )
    ))
  )

  isPalindrome := method(
    """Checks if the list is a palindrome."""
    zip(reverse) every(block(xs, xs head == xs tail))
  )
)



LinkedList := Object clone do(
  setProtos(list(
    Cons,
    ListSemigroup,
    ListMonoid,
    ListFunctor,
    ListMonad,
    ListFoldable,
    ListSliceable,
    ListFinite,
    ListSortable,
    ListZippable,
    ListPacking,
    ListRepeatable
  ))

  with := method(/* items..., */
    """Constructs a new lazy linked list."""
    call evalArgs reverse reduce(xs, x, cons(x, xs), empty)
  )

  cons := method(head, tail,
    """Constructs a new cons cell."""
    super(with(head, tail)) setProto(self)
  )
)