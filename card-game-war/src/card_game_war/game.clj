(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn highest-rank [card1 card2]
  (cond
    (> (.indexOf ranks (last card1)) (.indexOf ranks (last card2))) card1
    (= (.indexOf ranks (last card1)) (.indexOf ranks (last card2))) nil
    :else card2))

(defn highest-suit [card1 card2]
  (cond
    (> (.indexOf suits (first card1)) (.indexOf suits (first card2))) card1
    :else card2))

(defn play-round [player1-card player2-card]
  (or (highest-rank player1-card player2-card)
      (highest-suit player1-card player2-card)))

(defn play-game [player1-cards player2-cards])
