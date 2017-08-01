(ns com.ddeaguiar.datomic-intro.working.prospective
  "Code samples for the 'Prospective changes' slide."
  (:require [datomic.api :as d]
            [com.ddeaguiar.datomic-intro.working.schema :as schema]))

(def tx-data [[:db/add (d/tempid :people -1) :person/name "Daniel"]
              [:db/add (d/tempid :people -1) :person/likes "Fishing"]
              [:db/add (d/tempid :people -1) :person/drives "Subaru"]
              [:db/add (d/tempid :people -1) :person/secretNumber 42]

              [:db/add (d/tempid :people -2) :person/name "Kolby"]
              [:db/add (d/tempid :people -2) :person/likes "Gaming"]
              [:db/add (d/tempid :people -2) :person/secretNumber 21]

              [:db/add (d/tempid :people -3) :person/name "Juan"]
              [:db/add (d/tempid :people -3) :person/likes "Fishing"]
              [:db/add (d/tempid :people -3) :person/drives "Ford"]
              [:db/add (d/tempid :people -3) :person/secretNumber 84]])

(comment

 (d/delete-database schema/uri)

 (d/create-database schema/uri)

 (def conn (d/connect schema/uri))

 @(d/transact conn schema/schema)

 (d/q '[:find (count ?e) .
        :where [?e :person/name]]
      (d/db conn))

 (def tx @(d/transact conn tx-data))

 (let [prospective-db (:db-after (d/with (d/db conn)
                                         [{:person/name "Daniel" :person/likes "Hiking"}]))]
   (d/pull prospective-db '[*] [:person/name "Daniel"]))

 (d/pull (d/db conn) '[*] [:person/name "Daniel"])


 ;; retraction
 (let [prospective-db (:db-after (d/with (d/db conn)
                                         [{:person/name "Daniel" :person/likes "Hiking"}
                                          [:db/retract [:person/name "Daniel"] :person/drives "Subaru"]]))]
   (d/pull prospective-db '[*] [:person/name "Daniel"]))

 )
