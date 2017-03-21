let utils = require('../utils.js');

describe('test get calender data from redis', function() {
  this.timeout(10000)

  before('set url', () => {
      utils.setUrl('http://localhost:8080')
  })

  it('可以从redis中取到数据', (done) => {
    request
    .get('/')
    .expect(200,/"roomName":"London"/,done)
  })


})
