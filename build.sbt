name := "web-push"

version := "1.0"

scalaVersion := "2.13.3"

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

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ =>
  false
}

pomExtra :=
  <url>https://github.com/TeamWanari/web-push</url>
    <licenses>
      <license>
        <name>MIT License</name>
        <url>http://www.opensource.org/licenses/mit-license.php</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:TeamWanari/web-push.git</url>
      <connection>scm:git:git@github.com:TeamWanari/web-push.git</connection>
    </scm>
    <developers>
      <developer>
        <id>wanari</id>
        <name>Wanari Kft.</name>
        <url>https://www.wanari.com</url>
      </developer>
    </developers>
