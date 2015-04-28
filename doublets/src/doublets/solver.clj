(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))


(def alphabet (map char (range 97 123)))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn wrap-alphabet-from [letter]
  (let [ascii (int letter)
        first-half (mapv char (range 97 ascii))
        second-half (mapv char (range (inc ascii) 123))]
    (into [] (concat first-half second-half))))

(defn doublets [word1 word2]
  )
