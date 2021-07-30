(ns comp.core
(:require [compojure.core :refer [defroutes GET POST]]
          [ring.adapter.jetty :as jetty]
          [compojure.route :as route]
          [hiccup.core :as hiccup]
          [hiccup.page :as page]
          [ring.middleware.json :as json]
          [ring.util.http-response :as response]
          [ring.middleware.keyword-params :refer [wrap-keyword-params]]
          [cheshire.core :as ch]))

(defn foo-response []
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body {}})

(defn index []
  (page/html5
   [:head
    [:title "Hello World"]]
   [:body
    [:div {:id "content"} "Hello World"]]))

(defn json-get [request]
  (response/ok {:json "some json"
                :array [1 2 3]}))

(defn json-post [key request]
  (spit "json" (ch/generate-string {key (:json-params request)}))
  (response/ok (slurp "json")))

(defn results-post [request]
  (spit "res.json"
        (ch/generate-string
         (merge 
          (ch/parse-string 
           (slurp "res.json") true)
                (:json-params request))))
  (response/ok (slurp "res.json")))

(defroutes handler
  (GET "/" [] "Hey!")
  (GET "/1" [] (index))
  (GET "/questions" [] (slurp "json"))
  (GET "/json" [] json-get)
  (POST "/json/:key" [key :as request] (json-post key request))
  (POST "/results" [] results-post)
  )


(def myapp
  (-> handler
      (json/wrap-json-response)
      (json/wrap-json-params)))

(defn -main []
  (jetty/run-jetty #'myapp {:port 8080 :join? false}))
