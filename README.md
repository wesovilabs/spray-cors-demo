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

* **checkout noo-branch**

* **sbt compile run** : You will running the spray-can server

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

## Contributors

* Iván Corrales Solera (@wesovi)  (https://twitter.com/wesovi) (developer@wesovi.com)
