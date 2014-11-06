(ns views
  (:require [ui])
  (:import
    (viewa.widget.controller EditActionController ExitActionController AboutActionController)
    (viewa.annotation Controllers Controller)
    (viewa.view DefaultViewContainerFrame)))

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
   [ ["navigationViewID" (ui/navigationPanel)]])


