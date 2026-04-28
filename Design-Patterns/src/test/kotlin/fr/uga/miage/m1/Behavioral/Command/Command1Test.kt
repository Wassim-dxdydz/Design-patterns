package fr.uga.miage.m1.Behavioral.Command

import org.junit.jupiter.api.Test

class Command1Test {
    @Test
    fun `execute command and undo`(){
        val light = Light()
        val command = DimCommand(light, 50)
        val remote = RemoteControl()

        remote.executeCommand(command)
        assert(light.getBrightness() == 50)

        remote.undo()
        assert(light.getBrightness() == 10)
    }

    @Test
    fun `execute commands and undo`(){
        val light = Light()
        val first_command = DimCommand(light, 50)
        val second_command = DimCommand(light, 100)
        val third_command = DimCommand(light, 0)
        val remote = RemoteControl()

        remote.executeCommand(first_command)
        assert(light.getBrightness() == 50)
        remote.executeCommand(second_command)
        assert(light.getBrightness() == 100)
        remote.executeCommand(third_command)
        assert(light.getBrightness() == 0)
        remote.undo()
        assert(light.getBrightness() == 100)
        remote.undo()
        assert(light.getBrightness() == 50)
        remote.undo()
        assert(light.getBrightness() == 10)
    }
}