package es.jripoll.adventofcode


import spock.lang.Specification
import spock.lang.Unroll

class Day1ChronalCalibrationSpec extends Specification {

    @Unroll
    void 'frequency result after all of the changes #frecuencies in frequency have been applied #result'() {
        expect:
        result == calculateFrecuency(frecuencies)

        where:
        frecuencies      | result
        [+1, -2, +3, +1] | 3
        [+1, +1, +1]     | 3
        [+1, +1, -2]     | 0
        [-1, -2, -3]     | -6
    }

    @Unroll
    void '#result is the first frequency your device reaches twice with #frequencies'() {
        expect:
        result == frequencyReachedTwice(frequencies)

        where:
        frequencies          | result
        [1, -1]              | 0
        [3, 3, 4, -2, -4]    | 10
        [-6, +3, +8, +5, -6] | 5
        [+7, +7, -2, -7, -4] | 14
    }

    Integer calculateFrecuency(List<Integer> fecuencies) {
        fecuencies.sum() as Integer
    }

    Integer frequencyReachedTwice(List<Integer> fecuencies, List<Integer> results = [0]) {
        Integer result = null
        for (Integer frecuency : fecuencies) {
            results << results.last() + frecuency
            if (results.count(results.last()) > 1) {
                result = results.last()
                break
            }
        }

        if (result == null) {
            result = frequencyReachedTwice(fecuencies, results)
        }

        result
    }
}
