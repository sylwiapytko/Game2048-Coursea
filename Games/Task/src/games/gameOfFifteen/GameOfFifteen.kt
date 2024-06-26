package games.gameOfFifteen


import board.Direction
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */

class GameOfFifteen(val initializer: GameOfFifteenInitializer) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        board.getAllCells().mapIndexed() { index, cell -> board.set(cell, initializer.initialPermutation.getOrNull(index)) }
    }

    override fun canMove(): Boolean = board.any { it == null }

    override fun hasWon(): Boolean =
        isTrivial(board.getAllCells().mapNotNull { board.get(it) })


    override fun processMove(direction: Direction) {
        with(board) {
            find { it == null }?.let { emptyCell ->
                emptyCell.getNeighbour(direction.reversed())?.let { movedCell ->
                    set(emptyCell, get(movedCell))
                    set(movedCell, null)
                }
            }
        }

    }

    override fun get(i: Int, j: Int): Int? = board.get(board.getCell(i, j))?.toInt()

}

fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game = GameOfFifteen(initializer)


