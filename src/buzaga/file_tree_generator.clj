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
        {(output file) ;; Directory name as the key
         (into {} (map path->tree contents))}) ;; Recur for each item in directory
      {(output file) nil}))) ;; File name as the key with nil as the value

(comment
  (path->tree "src")
  (def my-tree (path->tree "src"))
  (println my-tree)
  (output (io/file "src")) ;; src/
  (output (io/file "src/buzaga/views.clj"))) ;; views
