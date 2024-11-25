(ns buzaga.views
  (:require [hiccup.page :as page]
            [buzaga.file-tree-generator :as ftg]))

(defn head-section
  [title]
  [:head
   [:title title]
   (page/include-js "https://cdn.tailwindcss.com")])

(defn render-tree [tree]
  (let [[name & contents] tree]
    [:ul.space-x-6
     [:li (str name)]
     (when contents
       (for [item contents]
         (if (vector? item)
           (render-tree item)  ;; Recursive call for directories
           [:li
            [:a {:href ""} (str item)]])))]))  ;; Simple <li> for files

(defn home-page
  []
  (page/html5
   (head-section "Buzaga")
   [:div.flex.flex-row
    [:div {:class "w-1/4 bg-blue-500"}
     (render-tree (ftg/path->tree "resources/site"))]
    [:div {:class "flex-1 bg-gray-200"}
     [:h1.text-3xl.font-bold]]]))

(comment
  (def file-tree-data
    ["."
     "deps.edn"
     "Justfile"
     ["resources"]
     ["public"
      ["css"]
      "styles.css"]
     ["src"]
     ["buzaga"
      "index.clj"]])
  (render-tree file-tree-data)
  (render-tree (ftg/path->tree "src"))
  (render-tree (ftg/path->tree "resources/site")))
