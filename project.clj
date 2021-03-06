(defproject comp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring "1.9.4"]
                 [compojure "1.6.2"]
                 [http-kit "2.5.3"]
                 [hiccup "2.0.0-alpha2"]
                 [ring/ring-json "0.5.1"]
                 [metosin/ring-http-response "0.9.2"]
                 [ring-middleware-format "0.7.4"]
                 [cljs-ajax "0.8.3"]]
  :repl-options {:init-ns comp.core})
