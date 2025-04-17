(ns clojure-noob.core
  (:gen-class)
  (:require
   [clojure.string :as string]))

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
       (string/join ", " things)))

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

(defn two-var-loop-example
  []
  ;; loop, like let, allows binding multiple values to names.
  ;; In this case we're binding 0 to x and 1 to y.
  (loop [x 0 y 1]
    (println (str "X = " x ", Y = " y))
    (if (< x 10)
      (recur (inc x) (inc y)))))

;; Contrasting loop with bog-standard recursion
;; I don't quite get the syntax for the first form though:
;; ([] (recursive-printer 0))
;; All the functions I've seen so far had [] for args without being enclosed by parens
;; Hopefully I'll learn more in the next chapter.
(defn recursive-printer
  ;; Kinda looks like it's just defining the recursion base case?
  ;; Oh yeah, that's right. Can define functions with different numbers of arguments.
  ;; If the function is called with 0 arguments, recurse with one argument-- iteration bound to 0.
  ;; If the function is called with one argument, that's when we do the actual printing.
  ([]
   (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 3)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))

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
  {:name (string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts-loop
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  ;; Binding the argument asym-body-parts to the name remaining body parts
  ;; asym-body-parts is the initial value in this loop
  ;; final-body-parts initial value is []
  ;; recur calls will bind new values for these names `remaining-asym-parts` and `final-body-parts`
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    ;; If we've processed all body parts:
    (if (empty? remaining-asym-parts)
      ;; return value bound to final-body-parts
      final-body-parts
      ;; otherwise, grab the next value in remaining-asym-parts, bind to 'part', will be passed to matching-part function.
      (let [[part & remaining] remaining-asym-parts]
        ;; Pass the rest of the parts into the next loop iteration as `remaining-asym-parts`
        (recur remaining
               ;; append the part with its matching part (if there is one) to final-body-parts,
               ;; pass the collection with the appended parts to the next loop iteration via recur
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  ;; (reduce + 15 [1 2 3 4])
  ;; reduce takes function, initial value, collection to iterate over.
  ;; Above call, function is +, initial val = 15, collection is [1 2 3 4]
  ;; below call from book, function = anonymous function that takes collection of processed parts followed by a single part
  ;; initial value = []
  ;; collection to iterate over = asym-body-parts, original argument to `better-symmetrize-body-parts`.
  ;; kinda different from how I'm used to reduce being used in other languages
  ;; I would have thought we'd map `matching-part` over the `asym-body-parts` collection.
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(defn hit
  [asym-body-parts]
  ;; first binding: take asym-parts and symmetrize them
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        ;; second binding: extract sizes from all body parts and sum them
        ;; Note: makes use of the first binding, no need for a separate let call. Woo!
        body-part-size-sum (reduce + (map :size sym-parts))
        ;; third binding: select a random number between 0 and n (exclusive), bind to target.
        ;; Again uses the result of a previous binding.
        target (rand body-part-size-sum)]
    ;; first loop-local binding: split out a part from the rest of the symmetrized parts.
    ;; Initial value: All of the symmetrized parts
    (loop [[this-part & remaining-parts] sym-parts
           ;; second loop-local binding: initialize accumulated-size to the first part's size.
           accumulated-size (:size this-part)]
      ;; If the sizes of all the parts we've iterated over so far is bigger than our random target
      (if (> accumulated-size target)
        ;; retun the randomly-selected part we're hitting
        this-part
        ;; else keep accumulating part sizes until we hit our target rand value
        (recur remaining-parts (+ accumulated-size (:size (first remaining-parts))))))))


;; Understanding let
;; Question: When to use def vs. let?
;; Answer: global binding -> def, local binding (temp name) -> let.
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
  ;; (println (str "Should have left- and right- body parts: " (symmetrize-body-parts asym-hobbit-body-parts)))
  (println (str "Should have left- and right- body parts: " (better-symmetrize-body-parts asym-hobbit-body-parts)))
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
  (two-var-loop-example)
  (recursive-printer)
  x)

(defn -main
  "I'm doing enough for now!"
  [& _args]
  (teapot)
  (ch3))
