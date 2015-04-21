(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[[:fox :goose :corn :you] [:boat] []]])

(defn finished? [[left-bank boat [crossing-plan]]]
  (and (empty? left-bank) (= [:boat] boat)))

(defn fox-alone-with-goose? [])

(defn goose-alone-with-corn? [])

(defn has-driver? [boat]
  (pos? (.indexOf boat :you)))

(defn not-driver? [passenger]
  (false? (= passenger :you)))

(defn add-driver [crossing-plan]
  (conj crossing-plan [(filterv not-driver? (first crossing-plan))
                       (conj (nth crossing-plan 1) :you)
                       (last crossing-plan)]))

(defn add-passenger [crossing-plan]
  (if-not (has-driver? (nth crossing-plan 1))
    (add-passenger (add-driver crossing-plan))))

(defn river-crossing-plan [] 
  (loop [river-crossing-plan start-pos]
    (if (finished? (last river-crossing-plan))
      river-crossing-plan
      (recur (conj river-crossing-plan (add-passenger (last river-crossing-plan)))))))

;(river-crossing-plan)