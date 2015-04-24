(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= [:spade 8] (play-round [:spade 8] [:club 2]))))
  (testing "queens are higher rank than jacks"
    (is (= [:spade :queen] (play-round [:spade :jack] [:spade :queen]))))
  (testing "kings are higher rank than queens"
    (is (= [:spade :king] (play-round [:spade :king] [:spade :queen]))))
  (testing "aces are higher rank than kings"
    (is (= [:spade :ace] (play-round [:spade :king] [:spade :ace]))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= [:club :5] (play-round [:club :5] [:spade :5]))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= [:diamond :5] (play-round [:diamond :5] [:club :5]))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= [:heart :5] (play-round [:heart :5] [:diamond :5])))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    ;(is (= "Player 1 Wins" (play-game [[:spade 2] [:club 5]] [[:heart 1]])))
    ))

