package fr.uga.miage.m1.Behavioral.Command

import kotlin.test.Test

class Command2Test {
    @Test
    fun `execute Command2 and undo` (){
        val account = BankAccount()
        val depositCommand = DepositCommand(account, 100)
        val withdrawCommand = WithdrawCommand(account, 200)
        val app = BankingApp()

        app.executeCommand(depositCommand)
        assert(account.balance == 1100)
        app.executeCommand(withdrawCommand)
        assert(account.balance == 900)
        app.undo()
        assert(account.balance == 1100)
        app.undo()
        assert(account.balance == 1000)
    }

    @Test
    fun `execute Command2 and undo with withdraw fail` (){
        val account = BankAccount()
        val depositCommand = DepositCommand(account, 100)
        val withdrawCommand = WithdrawCommand(account, 200)
        val withdrawCommandfail = WithdrawCommand(account, 2000)
        val app = BankingApp()

        app.executeCommand(depositCommand)
        assert(account.balance == 1100)
        app.executeCommand(withdrawCommand)
        assert(account.balance == 900)
        app.executeCommand(withdrawCommandfail)
        assert(account.balance == 900)
    }
}