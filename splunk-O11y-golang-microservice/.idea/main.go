package main

import (
	"context"
	"fmt"
	"log"
	"net/http"

	"github.com/gorilla/mux"
	"github.com/signalfx/splunk-otel-go/distro"
	"go.opentelemetry.io/contrib/instrumentation/github.com/gorilla/mux/otelmux"
)

func main() {

	sdk, err := distro.Run()
	if err != nil {
		panic(err)
	}
	// Flush all spans before the application exits
	defer func() {
		if err := sdk.Shutdown(context.Background()); err != nil {
			panic(err)
		}
	}()

	r := mux.NewRouter()
	r.Use(otelmux.Middleware("greetings"))
	r.HandleFunc("/greeting", handleGreeting).Methods("GET")
	log.Fatal(http.ListenAndServe(":8080", r))
}

func handleGreeting(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, "Hello from Go!")
}