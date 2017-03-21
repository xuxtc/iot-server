let utils = require('./utils.js');

describe('test get calender data online', function() {
  this.timeout(10000)

  before('set url', () => {
      utils.setUrl('http://localhost:8081')
  })

  it('成功从google拿到数据', (done) => {
    request
    .get('/get')
    .expect(200)
    .expect((res) => {
      MeetingTitle = res.body[1].calendar.title
    })
    .end(done)
  })

  it('数据被存到了redis', (done) => {
    request
    .get('/')
    .expect((res) => {
      redisMeetingTitle = res.body[1].calendar.title
      redisMeetingTitle.should.eql(MeetingTitle)
    })
    .end(done)
  })


})
