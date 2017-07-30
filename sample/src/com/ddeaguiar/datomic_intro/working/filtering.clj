(ns com.ddeaguiar.datomic-intro.working.filtering
  "Code samples for the 'Filtering' slide."
  (:require [datomic.api :as d]
            [com.ddeaguiar.datomic-intro.working.schema :as schema]
            [com.ddeaguiar.datomic-intro.working.prospective :as prospective]))

(comment

 (d/delete-database schema/uri)

 (d/create-database schema/uri)

 (def conn (d/connect schema/uri))

 @(d/transact conn schema/schema)

 @(d/transact conn prospective/tx-data)

 (def unfiltered-db (d/db conn))

 (def filtered-db (d/filter (d/db conn) (fn [db datom]
                                          (not= (d/entid db :person/secretNumber) (:a datom)))))

 (d/q '[:find [(pull ?e [*]) ...]
        :where
        [?e :person/name]]
      filtered-db)

 ;; more efficient
 ;; refer to http://docs.datomic.com/filters.html#joining-filters
 (d/q '[:find [?ent ...]
        :in $unfiltered $filtered
        :where
        [$unfiltered ?e :person/name]
        [(datomic.api/pull $filtered '[*] ?e) ?ent]]
      unfiltered-db
      filtered-db)

 )
