package Tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import Config.subsystems.extend;

@Config
@TeleOp(group = "Tests", name = "Extend Test")
public class out_Test extends OpMode {
    public static int target = 0;

    extend e;
    Gamepad g1, p1;

    @Override
    public void init() {
        e = new extend(hardwareMap, telemetry);

        g1 = new Gamepad();
        p1 = new Gamepad();

    }

    @Override
    public void loop() {
        p1.copy(g1);
        g1.copy(gamepad1);

        if (g1.triangle) {
            e.to_full();
        } else if (g1.cross) {
            e.to_zero();
        } else if (g1.square) {
            e.to_third();
        } else if(g1.circle) {
            e.to_two_third();

        } else if(g1.dpad_up) {
            e.to_half();
        }
        e.periodic();
        telemetry.update();
    }
}
