package main

import (
	"encoding/json"
	"fmt"
	"net/http"
)

type Customer struct {
	Codigo         int    `json:"codigo"`
	Identificacion string `json:"identificacion"`
	Nombre         string `json:"nombre"`
	Email          string `json:"email"`
	Channel        int    `json:"chanel"`
}

var customers = []Customer{
	{Codigo: 3, Identificacion: "1722207701", Nombre: "John Doe", Email: "johndoe@example.com", Channel: 2},
	{Codigo: 4, Identificacion: "1722207702", Nombre: "Jane Doe", Email: "janedoe@example.com", Channel: 2},
	{Codigo: 5, Identificacion: "1722207703", Nombre: "Bob Smith", Email: "bobsmith@example.com", Channel: 2},
}

func main() {
	http.HandleFunc("/customers", func(w http.ResponseWriter, r *http.Request) {
		switch r.Method {
		case "GET":
			json.NewEncoder(w).Encode(customers)
			fmt.Fprintf(w, "Respuesta a una solicitud GET")
		case "POST":
			var searchTerm Customer
			json.NewDecoder(r.Body).Decode(&searchTerm)

			for _, customer := range customers {
				if customer.Codigo == searchTerm.Codigo {
					json.NewEncoder(w).Encode(customer)
					return
				}
			}

			http.Error(w, "Cliente no encontrado", http.StatusNotFound)
		default:
			http.Error(w, "Método no permitido", http.StatusMethodNotAllowed)
		}
	})

	http.HandleFunc("/customer", func(w http.ResponseWriter, r *http.Request) {
		switch r.Method {
		case "POST":
			var newCustomer Customer
			json.NewDecoder(r.Body).Decode(&newCustomer)

			customers = append(customers, newCustomer)
			json.NewEncoder(w).Encode(newCustomer)
		default:
			http.Error(w, "Método no permitido", http.StatusMethodNotAllowed)
		}
	})

	err := http.ListenAndServe(":8081", nil)
	if err != nil {
		fmt.Println(err)
	}
}
