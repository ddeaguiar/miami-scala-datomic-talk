(ns com.ddeaguiar.datomic-intro.working.schema
  "Code samples for the 'Schema' slide."
  (:require [datomic.api :as d]))

(comment
 ;; Recall our Person entities
 [{:name   "Daniel"
   :likes  "Fishing"
   :drives "Subaru"}
  {:name  "Kolby"
   :likes "Gaming"}
  {:name   "Juan"
   :likes  "Fishing"
   :drives "Ford"}])

;; Let's create a schema for our them.
(def schema [{:db/ident       :person/name
              :db/doc         "The person's name"
              :db/valueType   :db.type/string
              :db/unique      :db.unique/identity
              :db/cardinality :db.cardinality/one}
             {:db/ident       :person/likes
              :db/doc         "The person's hobby"
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :person/drives
              :db/doc         "The model of vehicle the person drives."
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}])

(def uri "datomic:mem://people")

(comment

 (d/create-database uri)

 (def conn (d/connect uri))

 (def t1 @(d/transact conn schema))

 (keys t1)

 (d/q '[:find (pull ?e [*])
        :where [?e :db/ident :person/name]]
      (:db-before t1))

 (d/q '[:find (pull ?e [*])
        :where [?e :db/ident :person/name]]
      (:db-after t1))
 )
