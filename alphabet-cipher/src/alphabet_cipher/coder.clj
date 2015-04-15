(ns alphabet-cipher.coder)

(def alphabet (map char (range 97 123)))

(defn get-char-idx [char]
  (.indexOf alphabet char))

(defn construct-keyword [keyword message]
  (take (count message) (cycle keyword)))

(defn encode-char [key-char msg-char]
  (let [offset (mod (+ (get-char-idx msg-char) (get-char-idx key-char)) 26)]
    (nth alphabet offset)))

(defn decode-char [key-char msg-char]
  (let [offset (mod (- (get-char-idx msg-char) (get-char-idx key-char)) 26)]
    (nth alphabet offset)))

(defn encode [keyword message]
  (reduce str (map encode-char (construct-keyword keyword message) (seq message))))

(defn decode [keyword message]
  (reduce str (map decode-char (construct-keyword keyword message) (seq message))))