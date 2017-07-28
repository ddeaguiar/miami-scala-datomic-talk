(ns com.ddeaguiar.datomic-intro.datalog
  (:require [datomic.api :as d]))

;; Entity representation
(def entities [{:user/firstName "Daniel"
                :user/hobby     "Fishing"}
               {:user/firstName "Kolby"
                :user/hobby     "Gaming"}
               {:user/firstName "Juan"
                :user/hobby     "Fishing"}])

;; Can filter out the entity maps whose :user/hobby is 'Fishing'
(filter #(= "Fishing" (:user/hobby %)) entities)

























;; Fact representation
(def facts [[1 :user/firstName "Daniel"]
            [1 :user/hobby "Fishing"]
            [2 :user/firstName "Kolby"]
            [2 :user/hobby "Gaming"]
            [3 :user/firstName "Juan"]
            [3 :user/hobby "Fishing"]])


;; Can filter the collection
(filter #(and (= :user/hobby (second %))
              (= "Fishing" (last %)))
        facts)























;; Can treat the collection as a db and query with Datomic Datalog
(d/q '[:find ?name
       :in $ ?hobby
       :where
       [?entity :user/hobby ?hobby]
       [?entity :user/firstName ?name]]
     facts
     "Fishing")




























;; Return as a collection
(d/q '[:find [?name ...]
       :in $ ?hobby
       :where
       [?entity :user/hobby ?hobby]
       [?entity :user/firstName ?name]]
     facts
     "Fishing")




























;; Return a scalar
(d/q '[:find ?name .
       :in $ ?hobby
       :where
       [?entity :user/hobby ?hobby]
       [?entity :user/firstName ?name]]
     facts
     "Gaming")




























;; Return all fact tuples for matching entities
(d/q '[:find ?entity ?attribute ?value
       :in $ ?hobby
       :where
       [?entity :user/hobby ?hobby]
       [?entity ?attribute ?value]]
     facts
     "Fishing")
