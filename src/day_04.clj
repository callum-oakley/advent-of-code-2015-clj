(ns day-04
  (:require [clojure.test :refer [deftest are is run-tests]]
    [clojure.string :refer [starts-with?]])
  (:import java.security.MessageDigest))

(defn md5 [b]
  (->>
    b
    (.digest (MessageDigest/getInstance "MD5"))
    (biginteger)
    (format "%032x")))

(defn first-hash-that-starts-with [prefix key]
  (->>
    (range)
    (filter
      #(starts-with? (md5 (.getBytes (str key %))) prefix))
    (first)))

(def input "ckczppom")

(def part-1
  (first-hash-that-starts-with "00000" input))

(def part-2
  (first-hash-that-starts-with "000000" input))

;;;; Tests

(deftest test-first-hash-that-starts-with
  (are [key exp] (= (first-hash-that-starts-with "00000" key) exp)
    "abcdef"  609043
    "pqrstuv" 1048970))

(deftest test-part-1
  (is (= part-1 117946)))

(deftest test-part-2
  (is (= part-2 3938038)))

(run-tests)
