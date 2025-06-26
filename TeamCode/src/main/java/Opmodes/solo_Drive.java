package Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import Config.core.Robot;
@TeleOp (name = "solo dive ", group = "opmode")
public class solo_Drive extends OpMode{
    Robot bot;

    @Override
    public void init() {
        bot = new Robot(hardwareMap, telemetry, gamepad1, gamepad2);

    }

    @Override
    public void loop() {
        bot.solo_drive();

    }
}
