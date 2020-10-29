(ns day-03
  (:require [clojure.test :refer [deftest are is run-tests]]))

(defn move [[x y] direction]
  (case direction
    \^ [x (+ y 1)]
    \v [x (- y 1)]
    \> [(+ x 1) y]
    \< [(- x 1) y]))

(defn unique-houses [directions]
  (set (reductions move [0 0] directions)))

(def input-directions (seq (slurp "input/03.txt")))

(def part-1
  (count (unique-houses input-directions)))

(defn unique-houses-robot [directions]
  (let [santas-directions (take-nth 2 directions)
        robots-directions (take-nth 2 (rest directions))]
    (into
     (unique-houses santas-directions)
     (unique-houses robots-directions))))

(def part-2
  (count (unique-houses-robot input-directions)))

;;;; Tests

(deftest test-count-unique-houses
  (are [directions exp] (= (count (unique-houses (seq directions))) exp)
    ">"          2
    "^>v<"       4
    "^v^v^v^v^v" 2))

(deftest test-part-1
  (is (= part-1 2592)))

(deftest test-count-unique-houses-robot
  (are [directions exp] (= (count (unique-houses-robot (seq directions))) exp)
    "^v"          3
    "^>v<"       3
    "^v^v^v^v^v" 11))

(deftest test-part-2
  (is (= part-2 2360)))

(run-tests)
