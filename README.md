[![CircleCI branch](https://img.shields.io/circleci/project/github/leftshiftone/intent-markup/master.svg?style=flat-square)](https://circleci.com/gh/leftshiftone/intent-markup)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/leftshiftone/intent-markup.svg?style=flat-square)](https://github.com/leftshiftone/intent-markup/tags)
[![Bintray](https://img.shields.io/badge/dynamic/json.svg?label=bintray&query=name&style=flat-square&url=https%3A%2F%2Fapi.bintray.com%2Fpackages%2Fleftshiftone%2Fintent-markup%2Fone.leftshift.intent-markup.intent-markup%2Fversions%2F_latest)](https://bintray.com/leftshiftone/intent-markup/one.leftshift.intent-markup.intent-markup/_latestVersion)
[![npm (scoped)](https://img.shields.io/npm/v/@leftshiftone/intent-markup?style=flat-square)](https://www.npmjs.com/package/@leftshiftone/intent-markup)
[![PyPI](https://img.shields.io/pypi/v/intent-markup?style=flat-square)](https://pypi.org/project/intent-markup/)
# intent-markup

This library is used to specify which Intents of a digital assistant may be suitable for the autocomplete feature of A.I.O.S.

## Development

### Release
Releases are triggered locally. Just a tag will be pushed and CI pipelines take care of the rest.

#### Major
Run `./gradlew final -x bintrayUpload -x sendReleaseEmail -Prelease.scope=major` locally.

#### Minor
Run `./gradlew final -x bintrayUpload -x sendReleaseEmail -Prelease.scope=minor` locally.

#### Patch
Run `./gradlew final -x bintrayUpload -x sendReleaseEmail -Prelease.scope=patch` locally.
