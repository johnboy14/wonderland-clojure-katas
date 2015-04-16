(ns alphabet-cipher.coder)

(def alphabet (map char (range 97 123)))

(defn get-char-idx [char]
  (.indexOf alphabet char))

(defn construct-keyword [keyword message]
  (take (count message) (cycle keyword)))

(defn pair-messages [keyword message]
  (map list (construct-keyword keyword message) message))

(defn translate-char [shift-fn]
  (fn [pair]
    (->> (mod (shift-fn (get-char-idx (nth pair 1)) (get-char-idx (first pair))) 26)
         (nth alphabet))))

(defn encode [keyword message]
  (transduce (map (translate-char +)) str (pair-messages keyword message)))

(defn decode [keyword message]
  (transduce (map (translate-char -)) str (pair-messages keyword message)))
