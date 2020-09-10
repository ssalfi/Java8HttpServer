# Java8HttpServer

Assignment: Build a basic, multi-threaded HTTP server in Java 8 that is built and run with Maven, and has the following requirements met:
*	~~It handles chunked transfer encoding. You MAY copy a stream class that is open source for this if you wish, to save time.~~
*	~~It passes all headers, path info and parameters to the handling layer (e.g. a dynamic service, or a generic serve-file-from-webroot service)~~
*	It has at least one unit test that can be run from maven
*	~~You must not use a dependency that provides a full web server, the intention is that you build a server that listens right on sockets.~~
*	~~You must not blatantly copy the work of others, there is a lot of it out there including mine~~
*	It has a pluggable means of registering handlers for specific requests, e.g. http://myserver:8080/services/FooService might be handled by FooService.class
*	It has a built in and quick way to serve a directory full of files.
*	It must be checked into GitHub or Gitlab. Both have free plans I think. Go for free obviously.
*	A README that explains the design very briefly, and provides relevant instructions for usage.
*	GET and POST should be implemented, others should gracefully send a method not available message to the client.
 
Nice to haves:
*	Security considerations (XSS, access to local filesystem, etc)
*	Storage and management of session state in SQLite
*	Use of annotations, reflection, AOP, Generics
*	Logging
*	POSIX signal handling
*	PUT, PATCH, HEAD, … other verbs
