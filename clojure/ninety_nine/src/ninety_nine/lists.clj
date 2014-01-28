(ns ninety-nine.lists)

;;; P01 ----------------------------------------------------------------
(defn last* [xs]
  "Finds the last item of a List."
  (if (empty? (rest xs))
    (first xs)
    (recur (rest xs))))



;;; P02 ----------------------------------------------------------------
(defn last-but-one* [xs]
  "Finds the last but one box of a List."
  (if (empty? (rest (rest xs)))
    (first xs)
    (recur (rest xs))))



;;; P03 ----------------------------------------------------------------
(defn at* [xs k]
  "Finds the K'th element of a List."
  (if (= k 1)
    (first xs)
    (recur (rest xs) (- k 1))))



;;; P04 ----------------------------------------------------------------
(defn size* [xs]
  "Finds the number of elements of a List."
  (reduce (fn [a _] (inc a)) 0 xs))



;;; P05 ----------------------------------------------------------------
(defn reverse* [xs]
  "Reverses a List."
  (reduce (fn [as a] (cons a as)) [] xs))



;;; P06 ----------------------------------------------------------------
(defn palindrome? [xs]
  "Finds out whether a List is a palindrome."
  (= xs (reverse* xs)))



;;; P07 ----------------------------------------------------------------
(declare flattening-one)

(defn flatten* [xs]
  "Flattens a nested List structure."
  (mapcat flattening-one xs))


(defn- flattening-one [a]
  "Flattens a List structure a single level."
  (cond
   (coll? a) (flatten* a)
   :else     [a]))



;;; P08 ----------------------------------------------------------------
(defn- keep-if-different [as a]
  "Keeps the value in the resulting list if it's different from the last."
  (if (= (last as) a)
    as
    (conj as a)))


(defn compress* [xs]
  "Elminates consecutive duplicates of List elements."
  (reduce keep-if-different [] xs))



;;; P09 ----------------------------------------------------------------
(defn- pack-one-value [as a]
  "Packs one value into the List."
  (cond
   (empty? as)              [[a]]
   (= a (first (last as)))  (concat (drop-last as) [(cons a (last as))])
   :else                    (concat as [[a]])))

(defn pack* [xs]
  "Packs consecutive duplicates of List elements into sub-lists."
  (reduce pack-one-value [] xs))



;;; P10 ----------------------------------------------------------------
(defn encode* [xs]
  (map #(cons (count %) (take 1 %)) (pack* xs)))



;;; P11 ----------------------------------------------------------------
(defn- compress-one [[size a]]
  "Compress a single value."
  (cond
   (> size 1)  [size a]
   :else       a))

(defn encode2* [xs]
  "Modified run-length encoding."
  (map compress-one (encode* xs)))



;;; P12 ----------------------------------------------------------------
(defn- decode-one [a]
  "Decodes a single component of an RLE."
  (cond
   (coll? a)  (replicate (first a) (second a))
   :else      [a]))

(defn decode* [xs]
  "Decodes a run-length encoded list."
  (mapcat decode-one xs))



;;; P13 ----------------------------------------------------------------

;; TODO



;;; P14 ----------------------------------------------------------------
(defn duplicate* [xs]
  "Duplicates the elements of a List."
  (mapcat #(replicate 2 %) xs))



;;; P15 ----------------------------------------------------------------
(defn replicate* [xs n]
  "Replicates the elements of a List a given number of times."
  (mapcat #(replicate n %) xs))



;;; P16 ----------------------------------------------------------------
(defn drop* [xs n]
  "Drops every N'th element from a List."

  (letfn [(just-value [[_ n]]
            n)

          (divisible-by-n [[i _]]
            (= 0 (rem (inc i) n)))

          (pairs [i n]
            [i n])]
  
    (map just-value
         (remove divisible-by-n
                 (map-indexed pairs xs)))))



;;; P17 ----------------------------------------------------------------
(defn split* [xs n]
  "Splits a List into two parts.

The length of the first part is given as N."
    [(take n xs) (drop n xs)])



;;; P18 ----------------------------------------------------------------
(defn slice* [xs start end]
  "Extracts a slice from a List.

Given two indices, I and K, the slice is the list containing the elements
between the I'th and K'th element of the original list (both limits
included). Start counting the elements with 1."
  (let [s (dec start)
        n (- end s)]
    (take n (drop s xs))))



;;; P19 ----------------------------------------------------------------
(defn rotate* [xs n]
  "Rotates a List N places to the left."
  (cond
   (= n 0) xs
   (> n 0) (concat (drop n xs) (take n xs))
   (< n 0) (let [size (count xs)
                 pos  (+ size n)]
             (concat (drop pos xs)
                     (take pos xs)))))



;;; P20 ----------------------------------------------------------------
(defn remove-at* [xs n]
  "Removes the K'th element from a List."
  (concat (take (dec n) xs)
          (drop n xs)))



;;; P21 ----------------------------------------------------------------
(defn insert-at* [x xs n]
  "Inserts an element at a given position into a List."
  (concat (take (dec n) xs)
          [x]
          (drop (dec n) xs)))



;;; P22 ----------------------------------------------------------------
(defn range* [start end]
  "Creates a List containing all integers within a given range."
  (take (- end (dec start))
        (iterate inc start)))



;;; P23 ----------------------------------------------------------------
(defn random-select* [xs n]
  "Extracts a given number of randomly selected elements from a List."
  (take n (shuffle xs)))



;;; P24 ----------------------------------------------------------------
(defn lotto-select* [n m]
  "Draw N different random numbers from the set 1..M."
  (random-select* (range* 1 m) n))



;;; P25 ----------------------------------------------------------------
;; TODO: consider writing my own version of Shuffle?
(defn random-permutation* [xs]
  "Generates a random permutation of the elements of a List."
  (random-select* xs (count xs)))



;;; P26 ----------------------------------------------------------------
