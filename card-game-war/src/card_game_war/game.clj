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

(defn play-game [player1-cards player2-cards]
  (loop [player1 player1-cards
         player2 player2-cards]
    (cond
      (empty? player1) "Player 2 Wins!!"
      (empty? player2) "Player 1 Wins!!" 
      :else (if (= (first player1) (play-round (first player1) (first player2))) 
              (recur (vec (rest (conj player1 (first player1) (first player2)))) 
                     (vec (rest player2))) 
              (recur (vec (rest player1))
                     (vec (rest (conj player2 (first player2) (first player1)))))))))

(defn deal-cards []
  (loop [deck (shuffle cards)
         player1-cards []
         player2-cards []]
    (if (empty? deck)
      {:p1 player1-cards :p2 player2-cards}
      (recur (vec (drop 2 deck)) 
             (conj player1-cards (first deck)) 
             (conj player2-cards (nth deck 1))))))