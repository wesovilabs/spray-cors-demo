## Synopsis

This project explains how to implement a REST API with Spray that has CORS support

## Assumptions

I assume you have experience and your working environment ready to work with the below technologies/frameworks:

Akka, SBT, CORS, Spray, cURL, Scala, Git

## Requisites

This post is oriented to these people who have experience working with Scala and are starting to do it with Spray for REST API implementations.

## Motivation

* Learning to declare your own Spray directives

* Developing Rest API's that have CORS support

## Installation

The repository contains a couple of branches:

* **no-cors**: Obviously this branch does not have CORS support

* **cors**: This one contains CORS support.


## Implementing CORS directive

The below pieces of code show us how to implements a new directive and have use of it in our rest API. 
The below code can be found in **com.wesovi.demo.api.Api.scala**

 

*  **Declaring a new directive**:
		
		
		trait CORSSupport extends Directives {
		  private val CORSHeaders = List(
		    `Access-Control-Allow-Methods`(GET, POST, PUT, DELETE, OPTIONS),
		    `Access-Control-Allow-Headers`("Origin, X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, User-Agent"),
		    `Access-Control-Allow-Credentials`(true)
		  )
		
		  def respondWithCORS(origin: String)(routes: => Route) = {
		    val originHeader = `Access-Control-Allow-Origin`(SomeOrigins(Seq(HttpOrigin(origin))))
		    respondWithHeaders(originHeader :: CORSHeaders) {
		      routes ~ options { complete(StatusCodes.OK) }
		    }
		  }
		}


* **Making use of the new directive**

		trait Api extends Directives with RouteConcatenation with CORSSupport with ConfigHolder{
		  this: CoreActors with Core => 
		
		  val routes =
		    respondWithCORS(config.getString("origin.domain")) {
		    
		      pathPrefix("api") { 
		        new DemoRoute().route
		      } 
		    }
		  
		  val rootService = system.actorOf(ApiService.props(config.getString("hostname"), config.getInt("port"), routes))
		}


## Tests - No CORS support

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
		

## Tests - CORS support

* **checkout branch**

		checkout cors 

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
		< HTTP/1.1 200 OK
		* Server spray-can/1.3.3 is not blacklisted
		< Server: spray-can/1.3.3
		< Date: Sat, 30 May 2015 06:43:01 GMT
		< Access-Control-Allow-Origin: http://localhost
		< Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
		< Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, User-Agent
		< Access-Control-Allow-Credentials: true
		< Content-Type: text/plain; charset=UTF-8
		< Content-Length: 2

## Contributors

* Iván Corrales Solera (@wesovi)  (https://twitter.com/wesovi) (developer@wesovi.com)
