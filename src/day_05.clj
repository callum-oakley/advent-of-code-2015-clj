(ns day-05
  (:require
    [clojure.test :refer [deftest is run-tests]]
    [clojure.string :refer [includes? split-lines]]))

(defn windows-of [n xs]
  (if (< (count xs) n)
    []
    (cons
      (take n xs)
      (windows-of n (rest xs)))))

(defn legacy-nice? [s]
  (and
    (>=
      (count (filter #{\a \e \i \o \u} (seq s)))
      3)
    (some
      (fn [[x y]] (= x y))
      (windows-of 2 (seq s)))
    (every?
      #(not (includes? s %))
      ["ab" "cd" "pq" "xy"])))

(def input
  (split-lines (slurp "input/05.txt")))

(def part-1
  (count (filter legacy-nice? input)))

(defn non-overlapping-pairs [s]
  (when (>= (count s) 4)
    (or
      (includes? (subs s 2) (subs s 0 2))
      (non-overlapping-pairs (subs s 1)))))

(defn nice? [s]
  (and
    (non-overlapping-pairs s)
    (some (fn [[x _ y]] (= x y)) (windows-of 3 (seq s)))))

(def part-2
  (count (filter nice? input)))

;;;; Tests

(deftest test-nice?-legacy
  (is (legacy-nice? "ugknbfddgicrmopn"))
  (is (legacy-nice? "aaa"))
  (is (not (legacy-nice? "jchzalrnumimnmhp")))
  (is (not (legacy-nice? "haegwjzuvuyypxyu")))
  (is (not (legacy-nice? "dvszwmarrgswjxmb"))))

(deftest test-part-1
  (is (= part-1 236)))

(deftest test-nice?
  (is (nice? "qjhvhtzxzqqjkmpb"))
  (is (nice? "xxyxx"))
  (is (not (nice? "uurcxstgmygtbstg")))
  (is (not (nice? "ieodomkazucvgmuy"))))

(deftest test-part-2
  (is (= part-2 51)))

(run-tests)
