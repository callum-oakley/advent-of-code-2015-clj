(ns day-06
  (:require
    [clojure.test :refer [deftest is run-tests]]
    [clojure.string :refer [split-lines]]))

(defn parse-instruction [parse-op s]
  (let [[_ op x1 y1 x2 y2]
        (re-matches #"(.*) (\d+),(\d+) through (\d+),(\d+)" s)]
    (into [(parse-op op)] (map read-string [x1 y1 x2 y2]))))

(defn instructions [parse-op]
  (->> "input/06.txt"
    slurp
    split-lines
    (map #(parse-instruction parse-op %))))

(defn apply-instruction [grid [op x1 y1 x2 y2]]
  (reduce
    #(update %1 %2 op)
    grid
    (for [x (range x1 (inc x2))
          y (range y1 (inc y2))]
      [x y])))

(defn parse-op-1 [op]
  (case op
    "turn on"  (constantly true)
    "turn off" (constantly false)
    "toggle"   not))

(def part-1
  (->> parse-op-1
    instructions
    (reduce apply-instruction {})
    vals
    (filter true?)
    count))

(defn parse-op-2 [op]
  (case op
    "turn on"  #(inc (or % 0))
    "turn off" #(max 0 (dec (or % 0)))
    "toggle"   #(+ 2 (or % 0))))

(def part-2
  (->> parse-op-2
    instructions
    (reduce apply-instruction {})
    vals
    (apply +)))

;;;; Tests

(deftest test-part-1
  (is (= part-1 543903)))

(deftest test-part-2
  (is (= part-2 14687245)))

(run-tests)
