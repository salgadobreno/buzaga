(ns buzaga.file-tree-generator
  (:require [clojure.java.io :as io]))

(defn output [file]
  (if (.isDirectory file)
    (str (.getName file) "/")
    (org.apache.commons.io.FilenameUtils/removeExtension (.getName file))))

(defn path->tree [path]
  (let [file (io/file path)]
    (if (.isDirectory file)
      (let [contents (->> (.listFiles file)
                          (sort-by #(.getName %)))] ;; Sort contents alphabetically
        (into [(output file)]
              (map path->tree contents))) ;; Recur for each item in directory
      [(output file)])))

(comment
  (def my-tree (path->tree "src"))
  (println my-tree)
  (output (io/file "src")) ;; src/
  (output (io/file "src/buzaga/views.clj"))) ;; views
