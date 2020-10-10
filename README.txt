1. To run the assignment please put both the java files in the same package.

2. Please feel free to test these sample commands :-



httpc get http://httpbin.org/get?course=networking&assignment=1

httpc get -v http://httpbin.org/get?course=networking&assignment=1

httpc get -v -h Content-Type:application/json http://httpbin.org/get?course=networking&assignment=1

httpc get -h Content-Type:application/json http://httpbin.org/get?course=networking&assignment=1

httpc get -v http://httpbin.org/get?course=networking&assignment=1 -o test.txt

httpc post -h Content-Type:application/json -d '{"Assignment":1}' http://httpbin.org/post

httpc post -h Content-Type:application/json -f hello.txt http://httpbin.org/post

httpc post -d '{"Assignment":"1"}' http://httpbin.org/post

httpc post -v -h Content-Type:application/json -d '{"Assignment":1}' http://httpbin.org/post       

httpc post -v -h Content-Type:application/json -d '{"Assignment":1}' http://httpbin.org/post -o sample.txt



3 For Redirect use command 

httpc get -v -h Content-Type:application/json http://google.com
