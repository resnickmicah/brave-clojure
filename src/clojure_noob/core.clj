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

(defn chooser
  ;; Destructuring vector: this example accesses the first and second elements
  ;; and throws out the rest of them.
  ;; Had to prefix rest param ignore with _
  [[first-choice second-choice & _ignore]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println "We're ignoring the rest of your choices."))

(defn announce-treasure-location
  ;; alternate syntax:
  ;; get the values for the keys :lat and :lng
  ;; [{lat :lat lng :lng}]
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")

(defn receive-treasure-location
  ;; Destructure accessing values for particular keys, but also retain original map
  ;; Do the variable names need to match the keywords?
  ;; yes! I tried passing foo and bar to this fn and got nils back.
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  (println (str "Thankyee fer this beautiful map: " treasure-location)))

(def my-special-multiplier (fn [x] (* x 3)))
(def itty-bitty-multiplier #(* % 4))

;; higher-order fn example, returns an anon function
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

;; Closure example, inc3 knows to increment by 3 even outside (def inc3) scope.
(def inc3 (inc-maker 3))


(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part] ;; part is a map data structure
  ;; return a map with key :name equal to the resulting string after replacing left- with right-
  ;; that was stored in the argument body part map's :name key
  ;; also include a :size field with the same size as the input argument map's :size field.
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        ;; If we didn't use the above let binding, this is how the below would look:
        ;; (recur (rest remaining-asym-parts) <-- interesting note: Clojure uses first and rest instead of car and cdr.
        ;;        (into final-body-parts
        ;;              (set [(first remaining-asym-parts) (matching-part (first remaining-asym-parts))])))
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

;; Understanding let
;; QUESTION: When to use def vs. let?
(def x 0)

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
  (two-params 1 2)
  (println (x-punch "Kanye West"))
  (println (x-punch "Donald Trump" "Fascist" "Fuck"))
  (println (codger "random neighbors" "racing thoughts"))
  (println (favorite-things "sweetie" "vidya games" "couch"))
  (chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])
  (announce-treasure-location {:lat 28.22 :lng 81.33})
  (receive-treasure-location {:foo 28.22 :bar 81.33})
  (println (str "Return the last form evaluated in the fn scope: " (illustrative-function)))
  ;; anonymous function example:
  (println (str "Should be 24: " ((fn [x] (* x 3)) 8)))
  ;; Use def with anonymous function, works just like a regular fn:
  (println (str "Should be 21 with a def'd anonymous fn: " (my-special-multiplier 7)))
  (println (str "Should be 32: " (itty-bitty-multiplier 8)))
  (println (str "Anonymous fn using reader macro syntax with multiple args: "
                (#(str %1 " and " %2) "cornbread" "butter beans")))
  ;; AAAAGH TOO MANY SPECIAL CHARS... OWW MY BRAIN
  (println (#(identity %&) 1 "blarg" :yip))
  (println (inc3 7))
  ;; Putting it all together
  (println (str "Should be right-foot: " (matching-part {:name "left-foot" :size 2})))
  (println (str "Should have left- and right- body parts: " (symmetrize-body-parts asym-hobbit-body-parts)))
  ;; Understanding let 
  ;; "So, let is a handy way to introduce local names for values, which helps simplify the code."
  (println (str "4 times 0 equals : " (* x 4)))
  (println (str "4 times x where x is now rebound to 2 in a new scope equals : "
                (let [x (inc (inc x))] (* x 4))))
  ;; Understanding into and sets
  
  ;; Trying to define a set with set literal syntax and duplicate elements is a syntax error:
  ;; clojure-noob.core=> #{:a :a}
  ;; Syntax error reading source at (REPL:1:9).
  ;; Duplicate key: :a
  (println (str "Adding :a twice to a set, then adding that to an empty vector: " (into [] (set [:a :a]))))
  x)

(defn -main
  "I'm doing enough for now!"
  [& _args]
  (teapot)
  (ch3))
