const express = require('express')
const data = require('./data.js')
const app = express()

app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*')
  res.header('Access-Control-Allow-Methods', 'GET, PUT, POST, DELETE')
  res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept')
  next()
})

app.get('/get', (req, res) => {
  res.send(data)
})

app.get('/', (req, res) => {
  res.send(data)
})

const server = app.listen(8081, () => {
  let host = server.address().address
  let port = server.address().port

  console.log(`App listening at http://${host}:${port}`)
})
