package Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import Config.subsystems.intake;

@TeleOp(group = "Tests", name = "intake test")
public class intake_Test extends OpMode {

    private intake in;
    private Gamepad g1, p1;
    @Override
    public void init() {
        in = new intake(hardwareMap, telemetry);
        g1 = new Gamepad();
    }

    @Override
    public void loop() {
        p1.copy(g1);
        g1.copy(gamepad1);
        if(g1.triangle && !p1.triangle) {
            in.intake_full();
        } else if(g1.cross && p1.cross) {
            in.barf();
        } else if(g1.square && p1.square) {
            in.idle();
        }

    }
}
