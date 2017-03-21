let utils = require('../utils.js');
let data = require('../testData/postSensorStatus')

describe('test get calender data from redis', function() {
  this.timeout(10000)

  before('set url', () => {
      utils.setUrl('http://localhost:8080')
  })

  it('可以不传参数,默认把Melbourne改成B', (done) => {
    request
    .post('/sensor')
    .expect((res) => {
      res.body[0].roomName.should.be.equal("Melbourne")
      res.body[0].sensor.should.equal("B")
    })
    .end(done)
  })

  it('room名字传空字符', (done) => {
    request
    .post('/sensor')
    .query(
      {room:''}
    )
    .expect(200, done)
  })

  it('status名字传空字符', (done) => {
    request
    .post('/sensor')
    .query({
      room: "Melbourne",
      status: ''
    })
    .expect((res) => {
      res.body[0].sensor.should.equal('B')
    })
    .end(done)
  })

data.forEach((item) => {
  it('修改' + item.room +'状态为' + item.status, (done) => {
    request
    .post('/sensor')
    .expect(200)
    .query({
      room: item.room,
      status: item.status
    })
    .end((err) => {
      if (err) return done(err)

      request
      .get('/')
      .expect(200)
      .end((err, res) => {
        if (err) return done(err)
        for (var i = 0; i < 5; i++) {
          if(res.body[i].roomName == item.room){
          res.body[i].sensor.should.equal(item.status)}
        }
        done()
      })
    })

  })
})


})
