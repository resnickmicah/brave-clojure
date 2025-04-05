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

(defn varargsmaybe
  [& args]
  ;; Needed apply to unpack, but & is the right syntax for variable args
  (apply + args))

(defn no-params
  []
  "I take no parameters!")
(defn one-param
  [x]
  (str "I take one parameter: " x))
(defn two-params
  [x y]
  (str "Two parameters! That's nothing! Pah! I will smoosh them "
       "together to spite you! " x y))

;; multi-arity args example
(defn x-punch
  "Describe the kind of punch you're inflicting on someone"
  ([name punch-type insult]
   (str "I " punch-type " punch " name "! Take that you " punch-type " " insult "!"))
  ([name]
   (x-punch name "Nazi" "Bastard")))

(defn weird-arity
  ([]
   "Destiny dressed you this morning, my friend, and now Fear is
     trying to pull off your pants. If you give up, if you give in,
     you're gonna end up naked with Fear just standing there laughing
     at your dangling unmentionables! - the Tick")
  ([number]
   (inc number)))

(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(defn favorite-things
  ;; first params by name, '&' means 'rest parameter' which I'm reading as:
  ;; "... and the rest of the args"
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       ;; TODO: figure out why vscode is complaining about missing require
       ;;  for `clojure.string/join` but `lein repl` runs this just fine
       (clojure.string/join ", " things)))



(defn ch3
  []
  ;; I don't understand why a do isn't needed here...
  (println (+ 1 2 3))
  (ifexample)
  (whenexample)
  ;; data structures
  (println (str "Same name, different functions: " (= (getnamepart :middle) (getgetnamepart :middle))))
  (println (str "What does a keyword look like in a string? " (getconjvector 3)))
  (println (str "Is this three? " (varargsmaybe 1 2)))
  (println (str "Is this six? " (varargsmaybe 1 2 3)))
  (println (str "Conj on a vector [1 2 3]: " (conj [1 2 3] 4)))
  (println (str "Conj on a list (1 2 3): " (conj '(1 2 3) 4)))
  (println (str "(get #{:a :b} nil): " (pr-str (get #{:a :b} nil))))
  (println (str "(get #{:a :b} \"kurt vonnegut\"): " (pr-str (get #{:a :b} "kurt vonnegut"))))
  ;; functions
  (println (no-params))
  (println (one-param 1))
  (println (x-punch "Kanye West"))
  (println (x-punch "Donald Trump" "Fascist" "Fuck"))
  (println (codger "random neighbors" "racing thoughts"))
  (println (favorite-things "sweetie" "vidya games" "couch"))
  (two-params 1 2))

(defn -main
  "I don't do a whole lot ... yet."
  [& _args]
  (teapot)
  (ch3))
