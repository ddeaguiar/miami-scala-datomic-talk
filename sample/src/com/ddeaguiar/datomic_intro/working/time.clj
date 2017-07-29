(ns com.ddeaguiar.datomic-intro.working.time
  "Code samples for the 'Time' slide."
  (:require [datomic.api :as d]
            [com.ddeaguiar.datomic-intro.working.schema :as schema]
            [com.ddeaguiar.datomic-intro.working.prospective :as prospective]))

(comment

 (d/delete-database schema/uri)

 (d/create-database schema/uri)

 (def conn (d/connect schema/uri))

 @(d/transact conn schema/schema)

 @(d/transact conn prospective/tx-data)

 (def basis-t (d/basis-t (d/db conn)))

 @(d/transact conn [{:person/name "Juan" :person/likes "Biking"}])

 (d/pull (d/db conn) '[*] [:person/name "Juan"])

 (def past-db (d/as-of (d/db conn) basis-t))

 (d/pull past-db '[*] [:person/name "Juan"])

 )
