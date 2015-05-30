## Synopsis

This project explain how to implement a REST API with Spray that has CORS support

## Assumptions

I assume you have experience and your working environment ready to work with the below technologies/frameworks:

Akka, SBT, CORS, Spray, cURL, Scala, Git

## Requisites

This post is oriented to these people who have experience working with Scala and are starting to work with Spray for REST API implementations.

## Motivation

* Learning to declare your own Spray directives

* Developing Rest API's that have CORS support

## Installation

The repository contains a couple of branches:

* **no-cors**: Obviously this branch does not have CORS support

* **cors**: This onw contains CORS support.


## Tests

First of all we test the no-cors branch.

Then after clonning the repository to your working environment run the below steps:

* **checkout branch**

		checkout no-cors 

* **Run spray-can server**:

		sbt compile run

* **Run curl command** : We will simulate a CORS request with the below command,

		curl \
		--verbose \
		--header 'Origin: http://localhost:9292' \
		--request OPTIONS \
		--header 'Access-Control-Request-Headers: Origin, Accept, Content-Type' \
		--header 'Access-Control-Request-Method: GET' \
		--header 'Access-Control-Max-Age: 0' \
		--header 'Set-Cookie: test=test' \
		http://localhost:8878/api/demo/status  
		
*  **The server response**
		
		* Hostname was NOT found in DNS cache
		*   Trying 127.0.0.1...
		* Connected to localhost (127.0.0.1) port 8878 (#0)
		> OPTIONS /api/demo/status HTTP/1.1
		> User-Agent: curl/7.37.1
		> Host: localhost:8878
		> Accept: */*
		> Origin: http://localhost:9292
		> Access-Control-Request-Headers: Origin, Accept, Content-Type
		> Access-Control-Request-Method: GET
		> Access-Control-Max-Age: 0
		> Set-Cookie: test=test
		>
		< HTTP/1.1 405 Method Not Allowed
		* Server spray-can/1.3.3 is not blacklisted
		< Server: spray-can/1.3.3
		< Date: Sat, 30 May 2015 07:01:44 GMT
		< Allow: GET
		< Content-Type: text/plain; charset=UTF-8
		< Content-Length: 47
		<

## Contributors

* Iván Corrales Solera (@wesovi)  (https://twitter.com/wesovi) (developer@wesovi.com)
