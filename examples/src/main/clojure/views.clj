(ns views
  (:import
    (org.jdesktop.swingx JXTaskPane JXTaskPaneContainer JXHyperlink)
    (viewa.widget.controller EditActionController ExitActionController AboutActionController)
    (viewa.annotation Controllers Controller)
    (viewa.view DefaultViewContainerFrame)))

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

(gen-class
  :name
    ^{Controllers [
        (Controller {:type EditActionController :pattern "edit"})
        (Controller {:type ExitActionController :pattern "exit"})
        (Controller {:type AboutActionController :pattern "about"}) ]}
    views.MyApplicationRootView
  :extends viewa.view.DefaultViewContainerFrame)

(gen-class
  :name views.NavigationView
  :init "init"
  :prefix "navigation-"
  :constructors { [] [String java.awt.Component] }
  :extends viewa.view.DefaultViewContainer)

(defn navigation-init []
   "Setting id and inner component"
   [ ["navigationViewID" (navigationPanel)]])



