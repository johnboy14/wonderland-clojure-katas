(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[[:fox :goose :corn :you] [:boat] []]])

(defn finished? [[left-bank boat [crossing-plan]]]
  (and (empty? left-bank) (= [:boat] boat)))

(defn remove-passenger
  ([plan] (vec (pop plan)))
  ([plan passenger] (vec (remove #{passenger} plan))))

(defn has-driver? [boat]
  (> (.indexOf boat :you) -1))

(defn fox-and-goose-together? [passengers]
  (and (> (.indexOf passengers :fox) -1)
       (> (.indexOf passengers :goose) -1)
       (= (count passengers) 2)))

(defn goose-and-corn-together? [passengers]
  (and (> (.indexOf passengers :corn) -1)
       (> (.indexOf passengers :goose) -1)
       (= (count passengers) 2)))

(defn board-boat [crossing-plan passenger]
  (conj crossing-plan
        (vec [(remove-passenger (first (last crossing-plan)) passenger)
              (conj (nth (last crossing-plan) 1) passenger)
              (last (last crossing-plan))])))

(defn leave-boat [crossing-plan]
  (conj crossing-plan
        (vec [(first (last crossing-plan))
              (remove-passenger (nth (last crossing-plan) 1))
              (conj (last (last crossing-plan)) (last (nth (last crossing-plan) 1)))])))

(defn next-passenger [remaining-passengers]
  (first (filterv #(false? (or (fox-and-goose-together? (remove-passenger remaining-passengers %))
                         (goose-and-corn-together? (remove-passenger remaining-passengers %))))
            remaining-passengers)))

(defn add-passenger [crossing-plan]
  (if-not (has-driver? (nth (last crossing-plan) 1))
    (add-passenger (board-boat crossing-plan :you))
    (board-boat crossing-plan (next-passenger (first (last crossing-plan))))))

(defn dissembark [crossing-plan]
  (let [left-bank (first (last crossing-plan))]
    (if (empty? left-bank)
      (leave-boat (leave-boat crossing-plan))
      (leave-boat crossing-plan))))

(dissembark (add-passenger start-pos))

(defn river-crossing-plan []
  (loop [crossing-plan start-pos]
    (println (last crossing-plan))
    (if-not (finished? (last crossing-plan))
      (-> crossing-plan
          add-passenger
          dissembark
          recur)
      crossing-plan)))