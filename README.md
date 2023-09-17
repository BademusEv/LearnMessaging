### How to run

In the current directory run</br>
`docker-compose up --build`

To add a message to the order topic run</br>
`curl -X POST http://localhost:8081/order -H "Content-Type: application/json" -d '{
"dishName": "vegeterian",
"specialPreferences": "without tomatoes"
}'
`