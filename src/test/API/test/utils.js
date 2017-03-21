const supertest = require('supertest')
const should = require('should');

setUrl = (url) => {
   request = supertest(url)
}

module.exports = {setUrl:setUrl}
