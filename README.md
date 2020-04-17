# Description
acdt (Android Continuous Deployment Tools) is a toolset to  interact with the Google Play Store API and the Gitlab API.

# Features
Google Play Store:
- list all apk already published
- publish a new version of an app

Gitlab:
- create a new release
- update a release
- delete a release

Changelog:
- Generate a changelog from release note files

# Usage
## All commands
`./acdt -h`
```
Usage: commandline [OPTIONS] COMMAND [ARGS]...

Options:
  --version   Show the version and exit
  -h, --help  Show this message and exit

Commands:
  play-store-publish     Publish app on the play store
  play-store-list-apks   List all apk published on the play store
  gitlab-create-release  Create gitlab release
  gitlab-update-release  Update gitlab release
  gitlab-delete-release  Delete gitlab release
  changelog-generate     Generate changelog
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
  -R, --release-notes PATH         Release notes folder
  -h, --help                       Show this message and exit
```

### Release notes
You can specify release notes in text files for each locale, or each language or a default one for all your languages.

#### Tree folder
First level must be the language code and second level must be the country code.  
A default file named `default.txt` can be defined at each level.  
A version file named `<app-version>.txt` can also be defined at each level. The filename should exactly match the version set as parameter `--app-version` to be considered.

#### Priorities
Release notes files are selected in this order:
1. Looking for corresponding version file in the country folder
2. Fallback on the default file in the country folder
3. Fallback on the version file in the language folder
4. Fallback on the default file in the country folder
5. Fallback on the version file at the top-level
6. Fallback on the default file at the top-level

#### Example
```bash
changelogs
├── default.txt
├── en
│   ├── AU
│   │   └── default.txt
│   ├── GB
│   │   ├── default.txt
│   │   └── v1.1.0.txt
│   ├── US
│   └── default.txt
├── fr
│   ├── CA
│   ├── FR
│   │   └── v1.1.0.txt
│   ├── v1.0.0.txt
└── v1.0.0.txt
```
Considering the tree above, the release notes for the version v1.1.0 will be:
- en-AU: changelogs/en/AU/default.txt
- en-GB: changelogs/en/GB/v1.1.0.txt
- en-US: changelogs/en/default.txt
- fr-CA: changelogs/default.txt
- fr-FR: changelogs/fr/FR/v1.1.0.txt

## Create a new release in Gitlab
`./acdt gitlab-create-release -h`
```
Usage: commandline gitlab-create-release [OPTIONS]

  Create gitlab release

Options:
  -u, --url TEXT                   Gitlab url
  -t, --access-token TEXT          Your Gitlab access token
  -p, --project-id TEXT            Your Gitlab project id
  -T, --tag-name TEXT              Tag name associated to the release
  -N, --release-name TEXT          Release name
  -D, --release-description VALUE  Release description
  -M, --release-milestone TEXT     Milestone associated to the release, if
                                   several separated by semicolon
  -h, --help                       Show this message and exit
```

## Update a release in Gitlab
`./acdt gitlab-update-release -h`
```
Usage: commandline gitlab-update-release [OPTIONS]

  Update gitlab release

Options:
  -u, --url TEXT                   Gitlab url
  -t, --access-token TEXT          Your Gitlab access token
  -p, --project-id TEXT            Your Gitlab project id
  -T, --tag-name TEXT              Tag name associated to the release
  -N, --release-name TEXT          Release name
  -D, --release-description VALUE  Release description
  -M, --release-milestone TEXT     Milestone associated to the release, if
                                   several separated by semicolon
  -h, --help                       Show this message and exit
```

## Delete a release in Gitlab
`./acdt gitlab-delete-release -h`
```
Usage: commandline gitlab-delete-release [OPTIONS]

  Delete gitlab release

Options:
  -u, --url TEXT           Gitlab url
  -t, --access-token TEXT  Your Gitlab access token
  -p, --project-id TEXT    Your Gitlab project id
  -T, --tag-name TEXT      Tag name associated to the release
  -h, --help               Show this message and exit
```

## Generate changelog
`./acdt changelog-generate -h`
```
Usage: commandline changelog-generate [OPTIONS]

  Generate changelog

Options:
  -R, --release-notes PATH         Release notes folder
  -V, --version TEXT               Version
  -F, --output-format [MARKDOWN|JSON|XML]
                                   Set output format (Default MARKDOWN)
  -h, --help                       Show this message and exit
```

Changelog is generated from a release notes folder which must be structured like for `play-store-publish` command. But unlike for `play-store-publish` command, `changelog-generate` command considers only folders containing `version.txt` file and ignores `default.txt` files.  

# How to build
`./gradlew run` : run main class  
`./gradlew distZip` : generate zip release: build/distributions/acdt-0.1.0.zip  
`./gradlew distTar` : generate zip release: build/distributions/acdt-0.1.0.tar  
`./gradlew installDist` : generate runnable executable: build/install/acdt/bin/acdt
  