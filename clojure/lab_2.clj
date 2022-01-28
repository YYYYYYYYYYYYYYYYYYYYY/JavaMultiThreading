(ns ru.nsu.fit.db.cl2
  (:gen-class))
(use 'clojure.test)


(defn primes-step 
  [x table]
  (if (table x)
    (primes-step (inc x)
      (reduce #(update %1 (+ x %2) conj %2)
      (dissoc table x)
      (table x)))
    (lazy-seq (cons x (primes-step (inc x) (assoc table (* x x) [x]))))))


(def primes
  (lazy-seq (primes-step 2 {})))


(deftest tests
  (is (= '(2 3 5 7 11 13 17 19 23 29) (take 10 primes)))
  (is (= '(2 3 5) (take 3 primes))))

(run-tests 'ru.nsu.fit.db.cl2)

(take 5 primes)