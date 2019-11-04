![GitHub tag (latest SemVer)](https://img.shields.io/github/v/tag/faph/icalendar-validator?label=release&sort=semver)
![Docker Pulls](https://img.shields.io/docker/pulls/faph/icalendar-validator)

icalendar-validator
===================

[iCalendar file][ical] validator command-line application available as a Docker image.

Run the validator like this:

    docker run --volume=<local dir>:/data faph/icalendar-validator /data/calendar.ics

where `<local dir>` is a directory containing the file `calendar.ics`.

Based on the [ical4j][ical4j] library.


Terms & conditions
------------------

Source code licensed under the [MIT License](/LICENSE).

ical4j library copyright Ben Fortuna, licensed under the BSD 3-Clause License.

[ical]: https://en.wikipedia.org/wiki/ICalendar
[ical4j]: https://github.com/ical4j/ical4j
