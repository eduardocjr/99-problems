(ns ninety-nine.test.core
  (:use [ninety-nine.core]
        [ninety-nine.lists])
  (:use [clojure.test]))

(deftest p01
  (is (= (last* [:a :b :c :d]) :d)))

(deftest p02
  (is (= (last-but-one* [:a :b :c :d]) :c)))

(deftest p03
  (is (= (at* [:a :b :c :d :e] 3) :c)))

(deftest p04
  (is (= (size* [:a :b :c :d]) 4)))

(deftest p05
  (is (= (reverse* [:a :b :c :d]) [:d :c :b :a])))

(deftest p06
  (is (palindrome? [:a :n :a]))
  (is (not (palindrome? [:a :n :e]))))

(deftest p07
  (is (= (flatten* [:a [:b [:c :d] :e]]) [:a :b :c :d :e])))

(deftest p08
  (is (= (compress* [:a :a :a :a :b :c :c :a :a :d :e :e :e :e])
         [:a :b :c :a :d :e])))

(deftest p09
  (is (= (pack* [:a :a :a :a :b :c :c :a :a :d :e :e :e :e])
         [[:a :a :a :a] [:b] [:c :c] [:a :a] [:d] [:e :e :e :e]])))

(deftest p10
  (is (= (encode* [:a :a :a :a :b :c :c :a :a :d :e :e :e :e])
         [[4 :a] [1 :b] [2 :c] [2 :a] [1 :d] [4 :e]])))

(deftest p11
  (is (= (encode2* [:a :a :a :a :b :c :c :a :a :d :e :e :e :e])
         [[4 :a] :b [2 :c] [2 :a] :d [4 :e]])))

(deftest p12
  (is (= (decode* [[4 :a] :b [2 :c] [2 :a] :d [4 :e]])
         [:a :a :a :a :b :c :c :a :a :d :e :e :e :e])))

(deftest p14
  (is (= (duplicate* [:a :b :c :c :d])
         [:a :a :b :b :c :c :c :c :d :d])))

(deftest p15
  (is (= (replicate* [:a :b :c] 3)
         [:a :a :a :b :b :b :c :c :c])))

(deftest p16
  (is (= (drop* [:a :b :c :d :e :f :g :h :i :k] 3)
         [:a :b :d :e :g :h :k])))

(deftest p17
  (is (= (split* [:a :b :c :d :e :f :g :h :i :k] 3)
         [[:a :b :c] [:d :e :f :g :h :i :k]])))

(deftest p18
  (is (= (slice* [:a :b :c :d :e :f :g :h :i :k] 3 7)
         [:c :d :e :f :g])))

(deftest p19
  (is (= (rotate* [:a :b :c :d :e :f :g :h :i :k] 3)
         [:d :e :f :g :h :i :k :a :b :c]))

  (is (= (rotate* [:a :b :c :d :e :f :g :h :i :k] -2)
         [:i :k :a :b :c :d :e :f :g :h])))

(deftest p20
  (is (= (remove-at* [:a :b :c :d] 2)
         [:a :c :d])))

(deftest p21
  (is (= (insert-at* :alfa [:a :b :c :d] 2)
         [:a :alfa :b :c :d])))

(deftest p22
  (is (= (range* 4 9)
         [4 5 6 7 8 9])))

(deftest p23
  ;; TODO: write a universally quantified property for this
  ;; for all (random-select* [:a :b :c :d :e :f :g :h :i :k] 3)
  ;; the result list should be distinct | (list ∋ x)
  )

(deftest p24
  ;; TODO: write a universally quantified property for this
  ;; for all (lotto-select n m) the result list should be distinct
  ;; where all X are within the set 1..M
  )

(deftest p25
  ;; TODO: write a universally quantified property for this
  ;; for all (random-permutation xs) the resulting list should be a
  ;; valid permutation of the original list.
  )



