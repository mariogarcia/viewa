(ns app
  (:gen-class)
  (:import
      (views NavigationView MyApplicationRootView)
      (viewa.annotation Views View)
      (viewa.view.perspective PerspectiveConstraint)
      (viewa.widget.view AboutView)
      (viewa.core DefaultApplicationLauncher)))

(gen-class
  :name
    ^{Views [
        (View {:type NavigationView :position PerspectiveConstraint/LEFT})
        (View {:type MyApplicationRootView :isRoot true})]}
    app.MyApplication
  :extends viewa.core.DefaultApplication)

(defn -main []
  (let [app app.MyApplication
        launcher (DefaultApplicationLauncher.)]
    (.execute launcher app)))

