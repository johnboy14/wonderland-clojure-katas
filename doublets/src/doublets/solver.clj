(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn exist? [word]
  (> (.indexOf words word) -1))

(defn wrap-alphabet-from [letter]
  (let [ascii (int letter)
        first-half (mapv char (range 97 ascii))
        second-half (mapv char (range (inc ascii) 123))]
    (into [] (concat second-half first-half))))

;;given index and last matching doublet
;;loop until you find a match, returning a match or nil
(defn loop-thru [head doublets]
  (loop [letter (wrap-alphabet-from (nth (last doublets) head))
         word (last doublets)]
    (if-not (empty? letter)
      (let [idx-split (split-at head word)
            new-word (reduce str (concat (first idx-split) (conj (rest (last idx-split)) (first letter))))]
        (if (exist? new-word)
          (conj doublets new-word)
          (recur (rest letter) new-word)))
      doublets)))

(defn doublets [word1 word2]
  (if (= (count word1) (count word2))
    (loop [doublets (conj [] word1)
           head 0]
      (if (= word2 (last doublets))
        doublets
        (if (> (inc head) (count word1))
          (recur doublets 0)
          (recur (loop-thru head doublets) (inc head)))))
    []))
