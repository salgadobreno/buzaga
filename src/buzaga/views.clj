(ns buzaga.views
  (:require [hiccup.page :as page]
            [buzaga.file-tree-generator :as ftg]))

(defn head-section
  [title]
  [:head
   [:title title]
   (page/include-js "https://cdn.tailwindcss.com")])

(defn render-tree [tree]
  (for [[name content] tree]
    (if (map? content) ;; Check if it's a directory
      [:ul.space-x-6
       [:li name]
       (render-tree content)]
      [:li {:href "teste"} name]))) ;; Render files as <li>

(comment
  (def file-tree-data
    {"src/"
     {"buzaga/"
      {"file_tree_generator" nil,
       "handler" nil,
       "views" nil}}})
  (render-tree file-tree-data)
  (render-tree (ftg/path->tree "src"))
  (render-tree (ftg/path->tree "resources/site")))

(defn home-page
  []
  (page/html5
   (head-section "Buzaga")
   [:div.flex.flex-row
    [:div {:class "w-1/4 bg-blue-500"}
     (render-tree (ftg/path->tree "resources/site"))]
    [:div {:class "flex-1 bg-gray-200"}
     [:h1.text-3xl.font-bold]]]))
