#Project for IoT

###StringRedisController
get/set data from redis for testing redis purely

###RedisController
/sensor: set sensor state to redis
/get: get source data from Google calendar and set it into redis
/ : get data from redis

###ScheduledTasks
auto send 'get' request rhythmically


###RequestController
for fun..

##How it works
1. Auto send 'get' request to get data from Google calendar rhythmically
2. Save the data into redis
3. When consumers call '/', it returns data from redis
4. When consumers call '/sensor?para1&para2', it gets data from redis to match the parameter
5. Reset the data and put it back into redis
