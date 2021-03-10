[![CircleCI branch](https://img.shields.io/circleci/project/github/leftshiftone/intent-markup/master.svg?style=flat-square)](https://circleci.com/gh/leftshiftone/intent-markup)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/tag/leftshiftone/intent-markup.svg?style=flat-square)](https://github.com/leftshiftone/intent-markup/tags)
[![Maven Central](https://img.shields.io/maven-central/v/one.leftshift.intent-markup/intent-markup?style=flat-square)](https://mvnrepository.com/artifact/one.leftshift.intent-markup/intent-markup)
[![npm (scoped)](https://img.shields.io/npm/v/@leftshiftone/intent-markup?style=flat-square)](https://www.npmjs.com/package/@leftshiftone/intent-markup)
[![PyPI](https://img.shields.io/pypi/v/intent-markup?style=flat-square)](https://pypi.org/project/intent-markup/)
# intent-markup

This library is used to specify which Intents of a digital assistant may be suitable for the autocomplete feature of A.I.O.S.

This library can be used with Java, Python and Javascript. Intent Utterances can be excluded for the autocomplete feature as follows.
```
<!-- disable autocomplete, default is true -->
<intent autocomplete="false">Das ist ein Beispiel.</intent>

<!-- wird automatisch in <intent>Das ist ein Beispiel.</intent> konvertiert -->
Das ist ein Beispiel.
```

## Development

### Release
Releases are triggered locally. Just a tag will be pushed and CI pipelines take care of the rest.

#### Major
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=major` locally.

#### Minor
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=minor` locally.

#### Patch
Run `./gradlew final -x sendReleaseEmail -Prelease.scope=patch` locally.
