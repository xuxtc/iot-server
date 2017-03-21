let utils = require('./utils.js');

describe('testHelloWord', function() {
  this.timeout(10000)

  before('set url', () => {
      utils.setUrl('http://localhost:8080')
  })

  it('默认值是Stranger', (done) => {
    request
    .get('/hello-world')
    .expect(200, /"content":"Hello, Stranger!"/, done)
  })

  it('会返回传过去的参数', () => {

  })
})
