(ns buzaga.views
  (:require [hiccup.page :as page]))

(defn head-section
  [title]
  [:head
   [:title title]
   (page/include-js "https://cdn.tailwindcss.com")])

(def file-tree-data
  ["."
   "deps.edn"
   "Justfile"])

(def file-tree
  ["."
   "deps.edn"
   "Justfile"
   ["resources"
    ["public"
     ["css"
      "styles.css"]]]
   ["src"
    ["buzaga"
     "index.clj"]]])

(defn render-tree [tree]
  (let [[name & contents] tree]
    (println name)
    [:ul.space-x-6
     [:li name]
     (when contents
       (for [item contents]
         (if (vector? item)
           (render-tree item)  ;; Recursive call for directories
           [:li item])))]))  ;; Simple <li> for files

(comment
  (render-tree file-tree)
  (render-tree file-tree-data))

(defn home-page
  []
  (page/html5
   (head-section "Buzaga")
   [:div.flex.flex-row
    [:div {:class "w-1/4 bg-blue-500"}
     "Navigation"
     (render-tree file-tree)]
    [:div {:class "flex-1 bg-gray-200"}
     "Main"
     [:h1.text-3xl.font-bold
      "Home"]]]))
