(ns clojure-noob.core
  (:gen-class))

;; Ch. 1: Building, running, and the REPL
(defn teapot
  []
  (println "I'm a little teapot!"))

(defn ifexample
  []
  (if true
    "By Zeus's Hammer!"
    "By Aquaman's Trident!")
  (if false
    "BZH!"
    "BAT!")
  (if true
    (do (println "Success!")
        "BZH!")
    (do (println "Failure!")
        "BAT!")))

(defn whenexample
  []
  (when true
    (println "Success!")
    "abra cadabra"))

(def jjj {:name {:first "John" :middle "Jacob" :last "Jingleheimerschmidt"}})
(def jjjdoppelganger (hash-map :name (hash-map :first "John" :middle "Jacob" :last "Jingleheimerschmidt")))
(defn getnamepart
  [part]
  (get-in jjj [:name part]))

(defn getgetnamepart
  [part]
  ;; (get (get jjjdoppelganger :name) part))
  ;; Keywords can be used as functions that look up the corresponding value in a data structure. For example, you can look up :a in a map:
  ;; (:a {:a 1 :b 2 :c 3})
  ;; ... Using a keyword as a function is pleasantly succinct, and Real Clojurists do it all the time. You should do it too!
  (part (:name jjjdoppelganger)))

(def fbbb (conj (vector :foo :bar :baz) :blah))
(defn getconjvector
  [idx]
  (get fbbb idx))

(defn ch3
  []
  ;; I don't understand why a do isn't needed here...
  (println (+ 1 2 3))
  (ifexample)
  (whenexample)
  (println (str "Same name, different functions: " (= (getnamepart :middle) (getgetnamepart :middle))))
  (println (str "What does a keyword look like in a string? " (getconjvector 3))))

(defn -main
  "I don't do a whole lot ... yet."
  [& _args]
  (teapot)
  (ch3))
