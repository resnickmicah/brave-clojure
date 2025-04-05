(ns clojure-noob.core
  (:gen-class))

;; Ch. 1: Building, running, and the REPL
(defn teapot
  []
  (println "I'm a little teapot!"))

(defn ch2
  []
  (do (+ 1 2 3)
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
            "BAT!"))))
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (do (teapot)
      (println (ch2))))
