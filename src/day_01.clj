(ns day-01
  (:require [clojure.test :refer [deftest are is run-tests]]))

(defn floors [parens]
  (->> parens
    seq
    (map #(case % \( 1 \) -1))
    (reductions + 0)))

(defn last-floor [parens]
  (last (floors parens)))

(def part-1
  (last-floor (slurp "input/01.txt")))

(defn steps-to-basement [parens]
  (->> parens
    floors
    (take-while (complement neg?))
    count))

(def part-2
  (steps-to-basement (slurp "input/01.txt")))

;;;; Tests

(deftest test-last-floor
  (are [x y] (= (last-floor x) y)
    "(())"     0
    "((("      3
    "(()(()("  3
    "))((((("  3
    "())"     -1
    "))("     -1
    ")))"     -3
    ")())())" -3))

(deftest test-part-1
  (is (= part-1 138)))

(deftest test-steps-to-basement
  (are [x y] (= (steps-to-basement x) y)
    ")"      1
    "()())"  5
    "))))))" 1
    "()()))" 5))

(deftest test-part-2
  (is (= part-2 1771)))

(run-tests)
