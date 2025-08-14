package main

import (
	"net/http"
)

func main() {
	// Servindo a pasta "static" (coloque teste.html e style.css dentro dela)
	fs := http.FileServer(http.Dir("/Users/arthurandrade/Desktop/artking28/SamsungFakeStore/SamsungFakeStoreFront/src/main/webapp"))
	http.Handle("/", fs)

	err := http.ListenAndServe("0.0.0.0:8080", nil)
	if err != nil {
		panic(err)
	}
}
