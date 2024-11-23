(ns buzaga.handler
  (:require [buzaga.views :as views]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/"
    []
    (views/home-page))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  ;; use #' prefix for REPL-friendly code -- see note below
  (wrap-defaults #'app-routes site-defaults))

(defn -main []
  (jetty/run-jetty #'app {:port 8080}))

(comment
  ;; evaluate this def form to start the webapp via the REPL:
  ;; :join? false runs the web server in the background!
  (def server (jetty/run-jetty #'app {:port 8080 :join? false}))
  ;; evaluate this form to stop the webapp via the the REPL:
  (.stop server)
  (server))

(println "oi")
