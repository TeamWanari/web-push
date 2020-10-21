name := "web-push"

version := "1.0"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "com.pauldijou"             %% "jwt-core"      % "4.3.0",
  "org.apache.httpcomponents" % "fluent-hc"      % "4.5.13",
  "org.bouncycastle"          % "bcprov-jdk15on" % "1.66",
)

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
