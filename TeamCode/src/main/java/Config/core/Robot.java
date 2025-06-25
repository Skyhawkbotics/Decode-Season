package Config.core;

import static Config.core.robotConstants.*;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Config.subsystems.extend;
import Config.subsystems.intake;
import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

public class Robot {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    private Gamepad currentG1,currentG2, prevG1, prevG2;
    private Follower follower;
    private extend e;
    private intake i;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry, Gamepad currentG1, Gamepad currentG2) { // robot
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        follower = new Follower(this.hardwareMap, FConstants.class, LConstants.class);
        e = new extend(hardwareMap, telemetry);
        i = new intake(hardwareMap, telemetry);
        this.currentG1 = new Gamepad();
        this.currentG2 = new Gamepad();



    }
    public void solo_drive() {
        prevG1.copy(currentG1);
        prevG2.copy(currentG2);
        follower.setTeleOpMovementVectors(-currentG1.left_stick_y * speed,  speed * -currentG1.left_stick_x , speed * -currentG1.right_stick_x);
        if(currentG1.a && prevG1.a) {
            e.to_full();
        } else if(currentG1.square && currentG1.square) {
            e.to_zero();
        }
    }
}