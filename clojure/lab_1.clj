(ns ru.nsu.fit.db.cl1
  (:gen-class))

;; remove all ch occurences in l 
(defn remove-char-from-list 
  [ch, l]
  (remove #(= ch %) l))

;; add acc to every element in l
(defn add-seq-to-every-element
  [l, acc]
  (map #(str % acc) l))

;; sequentially add each element from l to each element from acc
(defn process 
  [l, acc]
  (map #(add-seq-to-every-element (remove-char-from-list % l) %) acc))

(defn join 
  [l]
  (reduce concat l))

(def input '("a", "b", "c"))

(defn comb-util
  [acc, x]
  (join (process input acc)))

(defn generate-combinations
  [n]
  (reduce comb-util input (range (- n 1)))) 

(generate-combinations 2)
;; ("ba" "ca" "ab" "cb" "ac" "bc")

