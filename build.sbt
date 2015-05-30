
organization := "com.wesovi"

name := "cors-rest-api"

version := "0.0.1"

startYear := Some(2015)

scalaVersion := "2.11.6"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

EclipseKeys.withSource := true

unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil

unmanagedSourceDirectories in Test <<= (sourceDirectory){ src => src / "test" / "scala" :: Nil}

val sprayVersion = "1.3.3"

val sprayJsonVersion = "1.3.2"

val akkaVersion = "2.4-SNAPSHOT"

val specs2Version = "3.6"


libraryDependencies ++= Seq(
	"com.wesovi"		  %%	"scala-account-exchange" % "0.0.1",
	"com.typesafe.akka"   %% 	"akka-actor" 		% akkaVersion,
	"com.typesafe.akka"   %% 	"akka-remote" 		% akkaVersion,
	"com.typesafe.akka"   %% 	"akka-slf4j" 		% akkaVersion,
	"io.spray"            %%  	"spray-can"     	% sprayVersion,
	"io.spray"            %%  	"spray-routing" 	% sprayVersion,
  	"io.spray" 			  %% 	"spray-json" 		% sprayJsonVersion,
  	"io.spray"            %%  	"spray-testkit" 	% sprayVersion  	% "test" exclude("org.specs2", "specs2_2.11"),
  	"org.specs2" %%  "specs2-core"   % specs2Version % "test",
	"org.specs2" %% "specs2-mock" % specs2Version % "test",
	"org.specs2" %% "specs2-junit" % specs2Version % "test",
	"org.specs2" %% "specs2-matcher-extra" % specs2Version % "test",
  	 "ch.qos.logback" 	  % 	"logback-classic" 	% "1.1.2"
)


enablePlugins(JavaAppPackaging)

Revolver.settings
