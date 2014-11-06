(ns ui
  (:import
    (org.jdesktop.swingx JXTaskPaneContainer JXTaskPane JXHyperlink)))

(defn link []
  "Creates an action link"
  (doto
    (JXHyperlink.)
    (.setName "textPanelLinkName")
    (.setText "Show text panel")))

(defn taskPanel []
    "Creates a task panel"
    (doto
      (JXTaskPane.)
      (.setName "imageView")
      (.setTitle "Images")
      (.add (link))))

(defn navigationPanel[]
    "Creates de navigation panel"
    (doto
      (JXTaskPaneContainer.)
      (.setName "navigationPanel")
      (.add (taskPanel))))
