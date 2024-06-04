package games.gameOfFifteen

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation by lazy {
        getPermutation()


    }

    private fun getPermutation(): List<Int> {
        val permutationSet = mutableSetOf<Int>()
        do {
            while (permutationSet.size < 15) {
                permutationSet.add((1..15).random())
            }
        } while (!isEven(permutationSet.toList()) || isTrivial(permutationSet.toList()))
        return permutationSet.toList()
    }

    private fun isTrivial(permutation: List<Int>): Boolean{
        return (1..15).toList().equals(permutation)
    }

}

