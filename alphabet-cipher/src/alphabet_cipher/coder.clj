(ns alphabet-cipher.coder)

(def alphabet (map char (range 97 123)))

(defn get-char-idx [char]
  (.indexOf alphabet char))

(defn construct-keyword [keyword message]
  (take (count message) (cycle keyword)))

(defn translate-char [shift-fn]
  (fn [key-char msg-char]
    (->> (mod (shift-fn (get-char-idx msg-char) (get-char-idx key-char)) 26)
         (nth alphabet))))

(defn encode [keyword message]
  (reduce str (map (translate-char +) (construct-keyword keyword message) (seq message))))

(defn decode [keyword message]
  (reduce str (map (translate-char -) (construct-keyword keyword message) (seq message))))