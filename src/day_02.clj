(ns day-02
  (:require [clojure.test :refer [deftest are is run-tests]]
            [clojure.string :refer [split split-lines]]))

(defn area
  ([x y] (* x y))
  ([x y z] (+ (* 2 x y) (* 2 y z) (* 2 z x))))

;; Assumes x <= y <= z
(defn paper [[x y z]]
  (+ (area x y) (area x y z)))

(defn parse
  "parses a string AxBxC in to dimensions [A B C] with A <= B <= C"
  [line]
  (sort (map read-string (split line #"x"))))

(def presents
  (->> "input/02.txt"
       slurp
       split-lines
       (map parse)))

(def part-1
  (apply + (map paper presents)))

;; Assumes x <= y <= z
(defn ribbon [[x y z]]
  (+ x x y y (* x y z)))

(def part-2
  (apply + (map ribbon presents)))

;;;; Tests

(deftest test-paper
  (are [dims exp] (= (paper dims) exp)
    [2  3  4] 58
    [1  1 10] 43))

(deftest test-part-1
  (is (= part-1 1588178)))

(deftest test-ribbon
  (are [dims exp] (= (ribbon dims) exp)
    [2  3  4] 34
    [1  1 10] 14))

(deftest test-part-2
  (is (= part-2 3783758)))

(run-tests)
