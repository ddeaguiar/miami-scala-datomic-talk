(defproject com.ddeaguiar/datomic-intro "0.1.0-SNAPSHOT"
  :description "Intro to Datomic code samples"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.datomic/datomic-pro "0.9.5561.54"]]
  :repositories {"my.datomic.com" {:url   "https://my.datomic.com/repo"
                                   :creds :gpg}})
