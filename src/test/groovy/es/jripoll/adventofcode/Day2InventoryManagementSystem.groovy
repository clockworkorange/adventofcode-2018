package es.jripoll.adventofcode

import spock.lang.Specification
import spock.lang.Unroll

class Day2InventoryManagementSystem extends Specification {

    void 'the checksum for your list of box IDs'() {
        expect:
        4 * 3 == checksum(['abcdef', 'bababc', 'abbcde', 'abcccd', 'aabcdd', 'abcdee', 'ababab'])
    }

    void 'characters are common between the two correct box IDs'() {
        expect:
        'fgij' == commonCharacters(['abcde', 'fghij', 'klmno', 'pqrst', 'fguij', 'axcye', 'wvxyz'])
    }

    @Unroll
    void 'The occurrences #result of box Id #boxId'() {
        expect:
        result == calculateOccurrences(boxId)

        where:
        boxId    | result
        'abcdef' | [twoTimes: Boolean.FALSE, threeTimes: Boolean.FALSE]
        'bababc' | [twoTimes: Boolean.TRUE, threeTimes: Boolean.TRUE]
        'abbcde' | [twoTimes: Boolean.TRUE, threeTimes: Boolean.FALSE]
        'abcccd' | [twoTimes: Boolean.FALSE, threeTimes: Boolean.TRUE]
        'aabcdd' | [twoTimes: Boolean.TRUE, threeTimes: Boolean.FALSE]
        'abcdee' | [twoTimes: Boolean.TRUE, threeTimes: Boolean.FALSE]
        'ababab' | [twoTimes: Boolean.FALSE, threeTimes: Boolean.TRUE]
    }

    Integer checksum(List<String> codes) {
        List<Map<String, Boolean>> occurrences = codes.collect { calculateOccurrences(it) }
        return (occurrences.count { it.twoTimes }) * (occurrences.count { it.threeTimes })
    }

    String commonCharacters(List<String> ids) {
        Integer length = ids.first().length()
        Integer goal = length - 1
        List<String> result = ids.findAll { id ->
            ids.findAll {
                Integer coincidences = 0
                it.toList().eachWithIndex { String entry, int index ->
                    if (entry == id.toList()[index]) {
                        coincidences++
                    }
                }
                coincidences == goal
            }
        }
        result.findAll().first().toList().intersect(result.findAll().last().toList()).join()
    }

    private Map calculateOccurrences(String boxIDs) {
        Map result = [twoTimes: Boolean.FALSE, threeTimes: Boolean.FALSE]

        Set<String> chars = boxIDs.toSet()
        chars.each { character ->
            Integer occurrences = boxIDs.count(character)
            if (occurrences == 2) {
                result.twoTimes = Boolean.TRUE
            }
            if (occurrences == 3) {
                result.threeTimes = Boolean.TRUE
            }
        }
        result
    }
}
