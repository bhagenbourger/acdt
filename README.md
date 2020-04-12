# Description
acdt (Android Continuous Deployment Tools) is a toolset to  interact with the Google Play Store API and the Gitlab API.

# Features
Google Play Store:
- list all apk already published
- publish a new version of an app

Gitlab:
- create new release

# Usage
## All commands
`./acdt -h`
```
Usage: commandline [OPTIONS] COMMAND [ARGS]...

Options:
  -h, --help  Show this message and exit

Commands:
  play-store-publish    Publish app on the play store
  play-store-list-apks  List all apk published on the play store
  gitlab-release        Create gitlab release
```

## List all apk already published on the play store command
`./acdt play-store-list-apks -h`
```
Usage: commandline play-store-list-apks [OPTIONS]

  List all apk published on the play store

Options:
  -c, --credentials-p12 PATH     Credentials p12 file
  -i, --service-account-id TEXT  Service account id
  -N, --app-name TEXT            App name
  -P, --app-package-name TEXT    App package name
  -h, --help                     Show this message and exit
```

## Publish app on the play store command
`./acdt play-store-publish -h`
```
Usage: commandline play-store-publish [OPTIONS]

  Publish app on the play store

Options:
  -c, --credentials-p12 PATH       Credentials p12 file
  -i, --service-account-id TEXT    Service account id
  -N, --app-name TEXT              App name
  -P, --app-package-name TEXT      App package name
  -V, --app-version TEXT           App version
  -K, --apk PATH                   APK file
  -T, --track [alpha|beta|production]
                                   Set track (alpha / beta / production)
  --release-note-fr TEXT           Release note content in french
  --release-note-en TEXT           Release note content in english
  -h, --help                       Show this message and exit
```

## Create a new release in Gitlab
`./acdt gitlab-release -h`
```
Usage: commandline gitlab-release [OPTIONS]

  Create gitlab release

Options:
  -u, --url TEXT                  Gitlab url
  -t, --access-token TEXT         Your Gitlab access token
  -p, --project-id TEXT           Your Gitlab project id
  -N, --release-name TEXT         Release name
  -D, --release-description TEXT  Release description
  -T, --tag-name TEXT             Tag name associated to the release
  -M, --release-milestone TEXT    Milestone associated to the release, if
                                  several separated by semicolon
  -h, --help                      Show this message and exit
```

# How to build
./gradlew run => run main class  
./gradlew distZip => generate zip release: build/distributions/acdt-0.1.0.zip  
./gradlew installDist => generate runnable executable: build/install/acdt/bin/acdt  