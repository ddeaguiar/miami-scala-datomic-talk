(ns com.ddeaguiar.datomic-intro.datalog
  "Code samples for the 'Datalog' section."
  (:require [datomic.api :as d]))

;; Person entity representation
(def entities [{:name   "Daniel"
                :likes  "Fishing"
                :drives "Subaru"}
               {:name  "Kolby"
                :likes "Gaming"}
               {:name   "Juan"
                :likes  "Fishing"
                :drives "Ford"}])

;; Can filter out the entity maps who :likes 'Fishing'
(filter #(= "Fishing" (:likes %)) entities)























;; Fact representation
(def facts [["Daniel" :likes "Fishing"]
            ["Daniel" :drives "Subaru"]
            ["Kolby" :likes "Gaming"]
            ["Juan" :likes "Fishing"]
            ["Juan" :drives "Ford"]])


;; Can filter the collection
(filter #(and (= :likes (second %))
              (= "Fishing" (last %)))
        facts)
























;; Can treat the collection as a db and query with Datomic Datalog
(d/q '[:find ?entity
       :in $ ?likes
       :where
       [?entity :likes ?likes]]
     facts
     "Fishing")





























;; Return as a collection
(d/q '[:find [?entity ...]
       :in $ ?likes
       :where
       [?entity :likes ?likes]]
     facts
     "Fishing")





























;; Return a scalar
(d/q '[:find ?entity .
       :in $ ?likes
       :where
       [?entity :likes ?likes]]
     facts
     "Gaming")





















;; Return all fact tuples for matching entities
(d/q '[:find ?entity ?attribute ?value
       :in $ ?likes
       :where
       [?entity :likes ?likes]
       [?entity ?attribute ?value]]
     facts
     "Fishing")
