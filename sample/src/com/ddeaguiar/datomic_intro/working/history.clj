(ns com.ddeaguiar.datomic-intro.working.history
  "Code samples for the 'History' slide."
  (:require [datomic.api :as d]
            [com.ddeaguiar.datomic-intro.working.schema :as schema]
            [com.ddeaguiar.datomic-intro.working.prospective :as prospective]))

(comment

 (d/delete-database schema/uri)

 (d/create-database schema/uri)

 (def conn (d/connect schema/uri))

 @(d/transact conn schema/schema)

 @(d/transact conn prospective/tx-data)

 (d/q '[:find ?e ?likes ?tx ?op
        :where
        [?e :person/name "Daniel"]
        [?e :person/likes ?likes ?tx ?op]]
      (d/history (d/db conn)))

 (def db-after (:db-after (d/with (d/db conn) [{:person/name  "Daniel"
                                                :person/likes "Hiking"}
                                               [:db/retract [:person/name "Daniel"] :person/drives "Subaru"]])))

 (d/q '[:find ?e ?attr ?value ?tx ?op
        :where
        [?e :person/name "Daniel"]
        [?e ?attr-id ?value ?tx ?op]
        [?attr-id :db/ident ?attr]]
      (d/history db-after))

 )
