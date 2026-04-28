package fr.uga.miage.m1.Behavioral.Command

import org.junit.jupiter.api.Test

class Command3Test {
    @Test
    fun `execute command and undo`() {
        val game = Game()
        val soldier = Soldier()
        val moveCommand = moveCommand(soldier, 5,5)

        game.executeCommand(moveCommand)
        assert(soldier.getX() == 5 && soldier.getY() == 5)
        game.undoCommand()
        assert(soldier.getX() == 0 && soldier.getY() == 0)

    }

    @Test
    fun `execute guardcommand and undo`() {
        val game = Game()
        val soldier = Soldier()
        val guardCommand = GuardCommand(soldier)

        game.executeCommand(guardCommand)
        assert(soldier.getStatus() == "Guarding")
        game.undoCommand()
        assert(soldier.getStatus() == "Idle")
    }

    @Test
    fun `execute patrolcommand and undo`() {
        val game = Game()
        val soldier = Soldier()
        val moveCommand1 = moveCommand(soldier, 10,10)
        val moveCommand2 = moveCommand(soldier, 10,20)
        val patrolCommand = PatrolCommand(soldier)
        val guardCommand = GuardCommand(soldier)

        patrolCommand.addCommand(moveCommand1)
        patrolCommand.addCommand(moveCommand2)
        patrolCommand.addCommand(guardCommand)

        game.executeCommand(patrolCommand)
        assert(soldier.getX() == 20 && soldier.getY() == 30)
        assert(soldier.getStatus() == "Guarding")
        game.undoCommand()
        assert(soldier.getX() == 0 && soldier.getY() == 0)
        assert(soldier.getStatus() == "Idle")

    }
}