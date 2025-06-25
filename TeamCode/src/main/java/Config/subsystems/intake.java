package Config.subsystems;

import static Config.core.robotConstants.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class intake {
    public DcMotor in;
    private Telemetry telemetry;

    public intake(HardwareMap hardwareMap, Telemetry telemetry) { // init method
        in = hardwareMap.get(DcMotor.class, "in");
        in.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.telemetry = telemetry; // init telemetry

    }
    public void intake_full() {
        in.setPower(intake_speed);
    }
    public void barf() {
        in.setPower(barf_speed);
    }
    public void idle() {
        in.setPower(0);
    }
    public void intake_telemetry() {
        telemetry.addData("intake motor power", in.getPower());
    }
}
