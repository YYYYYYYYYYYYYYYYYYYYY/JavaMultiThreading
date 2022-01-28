(ns ru.nsu.fit.db.cl3
  (:gen-class))
(use 'clojure.test)


(defn p-filter
  [condition coll]
  (let [chunk-size (int (Math/ceil (Math/sqrt (count coll)))),
        parts (partition-all chunk-size coll)]
    (->> parts
      (map (fn [coll1]
          (future (doall (filter condition coll1)))))
      (doall)
      (map deref)
      (flatten)
    )
  )
)


(defn p-filter-inf
  [condition coll]
  (if (empty? coll)
    '()
    (concat (p-filter condition (take 100 coll))
            (lazy-seq (p-filter-inf condition (drop 100 coll)))
    )
  )
)


(defn long-even?
  "Returns true if n is even, throws an exception if n is not an integer"
  {:added "1.0"
   :static true}
   [n] 
   (Thread/sleep 100)
   (if (integer? n)
        (zero? (bit-and (clojure.lang.RT/uncheckedLongCast n) 1))
        (throw (IllegalArgumentException. (str "Argument must be an integer: " n)))))


(defn positive-numbers 
	([] (positive-numbers 1))
	([n] (lazy-seq (cons n (positive-numbers (inc n))))))


(deftest addition-tests
  (is (= 90 (reduce + (p-filter long-even? (range 1 20)))))
  (is (= (filter even? [1 2 3 4]) (p-filter even? [1 2 3 4]))))


(run-tests 'ru.nsu.fit.db.cl3)


(time (reduce + (p-filter long-even? (range 1 20))))  ; ~ 502 msecs
(time (reduce + (filter long-even? (range 1 20))))    ; ~ 1903 msecs

(time (reduce + (take 20 (p-filter-inf long-even? (positive-numbers)))))  ; ~ 1004 msecs
(time (reduce + (take 20 (filter long-even? (positive-numbers)))))        ; ~ 4006 msecs