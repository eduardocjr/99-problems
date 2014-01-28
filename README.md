99-problems
===========

Solution for [99 problems][] in as many languages as possible!!1 Based on the
[LISP version][] of this thingie.


## Le Problems

Examples and reference implementation are written in Clojure :3.

### Working with lists

01) Find the last item of a List.

```clj
(last [:a :b :c :d]) ;; => :d
```

02) Find the last but one item of a List.

```clj
(last-but-one [:a :b :c :d]) ;; => :c
```

03) Find the K'th element of a List.
> The first element in the list is number 1.

```clj
(at [:a :b :c :d :e] 3) ;; => :c
```

04) Find the number of elements of a List.

```clj
(size [:a :b :c :d]) ;; => 4
```

05) Reverse a List.

```clj
(reverse [:a :b :c :d]) ;; => [:d :c :b :a]
```

06) Find out whether a List is a palindrome.
> A palindrome can be read forward or backward;

```clj
(palindrome? [:a :n :a]) ;; => true
(palindrome? [:a :n :e]) ;; => false
```

07) Flatten a nested List structure.
> Transform a list, possibly holding lists as elements into a `flat' list by
> replacing each list with its elements (recursively).

```clj
(flatten [:a [:b [:c :d] :e]]) ;; => [:a :b :c :d :e]
```

08) Eliminate consecutive duplicates of List elements.
> If a list contains repeated elements they should be replaced with a single
> copy of the element. The order of the elements should not be changed.

```clj
(compress [:a :a :a :a :b :c :c :a :a :d :e :e :e :e])
;; => [:a :b :c :a :d :e]
```

09) Pack consecutive duplicates of List elements into sub-lists.
> If a list contains repeated elements they should be placed in separate
> sub-lists.

```clj
(pack [:a :a :a :a :b :c :c :a :a :d :e :e :e :e])
;; => [[:a :a :a :a] [:b] [:c :c] [:a :a] [:d] [:e :e :e :e]]
```

10) Run-length encoding of a List.
> Use the result of problem P09 to implement the so-called run-length encoding
> data compression method. Consecutive duplicates of elements are encoded as
> lists (N E) where N is the number of duplicates of the element E.

```clj
(encode [:a :a :a :a :b :c :c :a :a :d :e :e :e :e])
;; => [[4 :a] [1 :b] [2 :c] [2 :a] [1 :d] [4 :e]]
```

11) Modified run-length encoding.
> Modify the result of problem P10 in such a way that if an element has no
> duplicates it is simply copied into the result list. Only elements with
> duplicates are transferred as (N E) lists. 

```clj
(encode [:a :a :a :a :b :c :c :a :a :d :e :e :e :e])
;; => [[4 :a] :b [2 :c] [2 :a] :d [4 :e]]
```

12) Decode a run-length encoded list.
> Given a run-length code list generated as specified in problem P11. Construct
> its uncompressed version.

```clj
(decode [[4 :a] :b [2 :c] [2 :a] :d [4 :e]])
;; => [:a :a :a :a :b :c :c :a :a :d :e :e :e :e]
```

13) Run-length encoding of a List (direct solution).
> Implement the so-called run-length encoding data compression method
> directly. I.e. don't explicitly create the sublists containing the
> duplicates, as in problem P09, but only count them. As in problem P11,
> simplify the result list by replacing the singleton lists (1 X) by X.

```clj
(encode-direct [:a :a :a :a :b :c :c :a :a :d :e :e :e :e])
;; => [[4 :a] :b [2 :c] [2 :a] :d [4 :e]]
```

14) Duplicate the elements of a List.

```clj
(duplicate [:a :b :c :c :d])
;; => [:a :a :b :b :c :c :c :c :d :d]
```

15) Replicate the elements of a List a given number of times.

```clj
(replicate [:a :b :c] 3)
;; => [:a :a :a :b :b :b :c :c :c]
```

16) Drop every N'th element from a List.

```clj
(drop [:a :b :c :d :e :f :g :h :i :k] 3)
;; => [:a :b :d :e :g :h :k]
```

17) Split a List into two parts; the length of the first part is given.
> Do not use any predefined predicates.

```clj
(split [:a :b :c :d :e :f :g :h :i :k] 3)
;; => [[:a :b :c] [:d :e :f :g :h :i :k]]
```

18) Extract a slice from a List.
> Given two indices, I and K, the slice is the list containing the elements
> between the I'th and K'th element of the original list (both limits
> included). Start counting the elements with 1.

```clj
(slice [:a :b :c :d :e :f :g :h :i :k] 3 7)
;; => [:c :d :e :f :g]
```

19) Rotate a List N places to the left.

```clj
(rotate [:a :b :c :d :e :f :g :h :i :k] 3)
;; => [:d :e :f :g :h :i :k :a :b :c]

(rotate [:a :b :c :d :e :f :g :h :i :k] -2)
;; => [:i :k :a :b :c :d :e :f :g :h]
```

20) Remove the K'th element from a List.

```clj
(remove-at [:a :b :c :d] 2)
;; => [:a :c :d]
```

21) Insert an element at a given position into a List.

```clj
(insert-at :alfa [:a :b :c :d] 2)
;; => [:a :alfa :b :c :d]
```

22) Create a List containing all integers within a given range.
> If first argument is smaller than second, produce a list in decreasing
> order.

```clj
(range 4 9)
;; => [4 5 6 7 8 9]
```

23) Extract a given number of randomly selected elements from a List.
> The selected items shall be returned in a list.

```clj
(random-select [:a :b :c :d :e :f :g :h :i :k] 3)
;; => [:e :d :a]
```

24) Lotto: Draw N different random numbers from the set 1..M.
> The selected numbers shall be returned in a list.

```clj
(lotto-select 6 49)
;; => [23 1 17 33 21 37]
```

25) Generate a random permutation of the elements of a List.

```clj
(random-permutation [:a :b :c :d :e :f])
;; => [:b :a :d :c :e :f]
```

26) Generate the combinators of K distinct objects chosen from the N elements
of a List.
> In how many ways can a committee of 3 be chosen from a group of 12 people? We
> all know that there are C(12,3) = 220 possibilities (C(N,K) denotes the
> well-known binomial coefficients). For pure mathematicians, this result may be
> great. But we want to really generate all the possibilities in a list. 

```clj
(combination 3 [:a :b :c :d :e :f])
;; => [[:a :b :c] [:a :b :d] [:a :b :e] ...]
```

27) Group the elements of a set into disjoint subsets.
> a) In how many ways can a group of 9 people work in 3 disjoint subgroups of
> 2, 3 and 4 persons? Write a function that generates all the possibilities and
> returns them in a list. 

```clj
(group 3 [:aldo :beat :carla :david :evi :flip :gary :hugo :ida])
[[[:aldo :beat] [:carla :david :evi] [:flip :gary :hugo :ida]] ...]
```

> Generalize the above predicate in a way that we can specify a list of group
> sizes and the predicate will return a list of groups. 

```clj
(group 3 [:aldo :beat :carla :david :evi :flip :gary :hugo :ida] [2 2 5])
[[[:aldo :beat] [:carla :david] [:evi :flip :gary :hugo :ida]] ...]b
```

> Note that we do not want permutations of the group members; i.e. [[:aldo :beat]
> ...] is the same solution as [[:beat :aldo] ..]. However, we make a difference
> between [[:aldo :beat] [:carla :david] ...] and [[:carla :david]
> [:aldo :beat] ...].

> You may find more about this combinatorial problem in a good book on discrete
> mathematics under the term "multinomial coefficients". 

28) Sorting a List of Lists according to the length of sub-lists.

> a) We suppose that a list contains elements that are lists themselves. The
> objective is to sort the elements of this list according to their
> **length**. E.g. short lists first, longer lists later, or vice versa. 

```clj
(lsort [[:a :b :c] [:d :e] [:f :g :h] [:d :e] [:i :j :k :l] [:m :n] [:o]])
;; => [[:o] [:d :e] [:d :e] [:m :n] [:a :b :c] [:f :g :h] [:i :j :k :l]]
```

> b) Again, we suppose that a list contains elements that are lists
> themselves. But this time the objective is to sort the elements of this list
> according to their length frequency; i.e., in the default, where sorting is
> done ascendingly, lists with rare lengths are placed first, others with a
> more frequent length come later. 

```clj
(lfsort [[:a :b :c] [:d :e] [:f :g :h] [:d :e] [:i :j :k :l] [:m :n] [:o]])
;; => [[:i :j :k :l] [:o] [:a :b :c] [:f :g :h] [:d :e] [:d :e] [:m :n]]
```

### Arithmetic

31) Determine whether a given integer number is prime.

```clj
(is-prime? 7) ;; => true
```

32) Determine the greatest common divisor of two positive integer numbers.
> Use Euclid's algorithm.

```clj
(gcd 36 63) ;; => 9
```

33) Determine whether two positive integer numbers are co-prime.
> Two numbers are coprime if their greatest common divisor equals 1.

```clj
(co-prime? 35 64) ;; => true
```

34) Calculate Euler's totient function phi(m).
> Euler's so-called totient function phi(m) is defined as the number of positive
> integers r (1 <= r < m) that are coprime to m. 
>
> Example: m = 10: r = 1,3,7,9; thus phi(m) = 4. Note the special case: phi(1)
> = 1. 

```clj
(totient-phi 10) ;; => 4
```

> Find out what the value of phi(m) is if m is a prime number. Euler's totient
> function plays an important role in one of the most widely used public key
> cryptography methods (RSA). In this exercise you should use the most
> primitive method to calculate this function (there are smarter ways that we
> shall discuss later).

35) Determine the prime factors of a given positive integer.
> Construct a flat list containing the prime factors in ascending order.

```clj
(prime-factors 315) ;; => [3 3 5 7]
```

36) Determine the prime factors of a given positive integer (2).
> Construct a list containing the prime factors and their multiplicity.

```clj
(prime-factors-mult 315) ;; => [[3 2] [5 1] [7 1]]
```

37) Calculate Euler's totient function phi(m) (improved).
> See problem P34 for the definition of Euler's totient function. If the list
> of the prime factors of a number m is known in the form of problem P36 then the
> function phi(m) can be efficiently calculated as follows: Let ((p1 m1) (p2 m2)
> (p3 m3) ...) be the list of prime factors (and their multiplicities) of a given
> number m. Then phi(m) can be calculated with the following formula: 
>
> phi(m) = (p1 - 1) * p1 ** (m1 - 1) + (p2 - 1) * p2 ** (m2 - 1) + (p3 - 1) *
> p3 ** (m3 - 1) + ... 

> Note that a ** b stands for the b'th power of a.

38) Compare the two methods of calculating Euler's totient function.
> Use the solutions of problems P34 and P37 to compare the algorithms. Take the
> number of logical inferences as a measure for efficiency. Try to calculate
> phi(10090) as an example. 

39) A List of prime numbers.
> Given a range of integers by its lower and upper limit, construct a list of
> all prime numbers in that range. 

40) Goldbach's conjecture.
> Goldbach's conjecture says that every positive even number greater than 2 is
> the sum of two prime numbers. Example: 28 = 5 + 23. It is one of the most
> famous facts in number theory that has not been proved to be correct in the
> general case. It has been numerically confirmed up to very large numbers (much
> larger than we can go with our Prolog system). Write a predicate to find the
> two prime numbers that sum up to a given even integer.

```clj
(goldbach 28) ;; => [5 23]
```

41) A List of Goldbach compositions.
> Given a range of integers by its lower and upper limit, print a list of all
> even numbers and their Goldbach composition. 

```clj
(goldbach-list 9 20)

10 = 3 + 7
12 = 5 + 7
14 = 3 + 11
16 = 3 + 13
18 = 5 + 13
20 = 3 + 17
```

> In most cases, if an even number is written as the sum of two prime numbers,
> one of them is very small. Very rarely, the primes are both bigger than say
> 50. Try to find out how many such cases there are in the range 2..3000. 

```clj
(goldbach-list 1 2000 50)

992 = 73 + 919
1382 = 61 + 1321
1856 = 67 + 1789
1928 = 61 + 1867
```

### Logic and Codes

### Binary Trees

### Multi-way Trees

### Graphs

#### Miscellaneous Problems

## Licence

You just DO WHAT THE FUCK YOU WANT TO.


[99 problems]: https://sites.google.com/site/prologsite/prolog-problems
[LISP version]: http://www.ic.unicamp.br/~meidanis/courses/mc336/2006s2/funcional/L-99_Ninety-Nine_Lisp_Problems.html
