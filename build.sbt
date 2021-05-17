name := "web-push"

scalaVersion := "2.13.6"

libraryDependencies ++= {
  val akkaV     = "2.6.10"
  val akkaHttpV = "10.2.1"

  Seq(
    "com.pauldijou"             %% "jwt-core"            % "4.3.0",
    "org.apache.httpcomponents" % "fluent-hc"            % "4.5.13",
    "org.bouncycastle"          % "bcprov-jdk15on"       % "1.67",
    "com.typesafe.akka"         %% "akka-actor"          % akkaV,
    "com.typesafe.akka"         %% "akka-stream"         % akkaV,
    "com.typesafe.akka"         %% "akka-stream-testkit" % akkaV % Test,
    "com.typesafe.akka"         %% "akka-http"           % akkaHttpV,
    "com.typesafe.akka"         %% "akka-http-testkit"   % akkaHttpV % Test,
    "org.scalatest"             %% "scalatest"           % "3.3.0-SNAP2" % Test,
    "com.github.tomakehurst"    % "wiremock"             % "2.27.2" % Test,
  )
}

addCommandAlias("check", "fmtCheck test")
addCommandAlias("fmtCheck", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")

organization in ThisBuild := "com.wanari"
homepage := Some(url("https://github.com/TeamWanari/web-push"))
licenses := Seq("MIT" -> url("https://www.wanari.com/licenses/mit-license"))

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ =>
  false
}

publishTo in ThisBuild := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

scmInfo := Some(
  ScmInfo(
    url("https://github.com/TeamWanari/web-push"),
    "scm:git:git@github.com:TeamWanari/web-push.git"
  )
)

developers := List(
  Developer("Csabi", "Csaba Pálfi", "csibcsab@wanari.com", url("https://www.wanari.com"))
)
