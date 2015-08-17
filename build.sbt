name := """bart-api"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"     % "2.3.9",
  "com.typesafe.akka" %% "akka-slf4j"     % "2.3.9",
  "io.spray"          %% "spray-can"      % "1.3.3",
  "io.spray"          %% "spray-routing"  % "1.3.3",
  "io.spray"          %% "spray-json"     % "1.3.2",
  "com.typesafe.akka" %% "akka-testkit"   % "2.3.9" % "test",
  "org.scalatest"     %% "scalatest"      % "2.2.4" % "test",
  "org.json4s"        %% "json4s-native"  % "3.2.11",
  "org.json4s"        %% "json4s-jackson" % "3.2.11"
)


fork in run := true
