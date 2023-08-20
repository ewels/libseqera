/*
 *  Copyright (c) 2023, Seqera Labs.
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  License, v. 2.0. If a copy of the MPL was not distributed with this
 *  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 *  This Source Code Form is "Incompatible With Secondary Licenses", as
 *  defined by the Mozilla Public License, v. 2.0.
 */

package io.seqera.wave.util

import spock.lang.Specification
import spock.lang.Unroll

import java.nio.file.Path
/**
 *
 * @author Munish Chouhan munish.chouhan@seqera.io
 */
class DockerIgnorePathFilterTest extends Specification {

    @Unroll
    def 'should filter the paths based on ignore patterns'(){
        given:
        Set<String> ignorePatterns = ["*/ignore*","main.??","*/*/exclude*", "*.md", "!README.md"]
        def pathFilter = new DockerIgnorePathFilter(ignorePatterns)

        when:
        boolean accepted = pathFilter.accept(Path.of(PATH))

        then:
        accepted == VALID

        where:
        PATH                    | VALID
        'this/ignore'           | false
        'this/that/exclude.txt' | false
        'this/hola.txt'         | true
        'this/hello.txt'        | true
        'this/that/ciao.txt'    | true
        'main.nf'               | false
        'main.txt'              | true
        'file.md'               | false
        'README.md'             | true
    }

    @Unroll
    def 'should ignore everything'(){
        given:
        Set<String> ignorePatterns = ["**"]
        def pathFilter = new DockerIgnorePathFilter(ignorePatterns)

        when:
        boolean accepted = pathFilter.accept(Path.of(PATH))

        then:
        accepted == VALID

        where:
        PATH                    | VALID
        'this/ignore'           | false
        'this/that/exclude.txt' | false
        'this/hola.txt'         | false
        'this/hello.txt'        | false
        'this/that/ciao.txt'    | false
        'main.nf'               | false
        'main.txt'              | false
        'file.md'               | false
        'README.md'             | false
    }

    @Unroll
    def 'should filter the paths based on ignore and exception patterns'(){
        given:
        Set<String> ignorePatterns = ["*/ignore*","main.??","*/*/exclude*", "!README.md", "*.md"]
        def pathFilter = new DockerIgnorePathFilter(ignorePatterns)

        when:
        boolean accepted = pathFilter.accept(Path.of(PATH))

        then:
        accepted == VALID

        where:
        PATH                    | VALID
        'this/ignore'           | false
        'this/that/exclude.txt' | false
        'this/hola.txt'         | true
        'this/hello.txt'        | true
        'this/that/ciao.txt'    | true
        'main.nf'               | false
        'main.txt'              | true
        'file.md'               | false
        'README.md'             | false
    }
}
