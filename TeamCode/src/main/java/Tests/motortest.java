package Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.jar.Attributes;

@TeleOp (name = "odo test", group = "jaw")

public class motortest extends OpMode {
    private DcMotor odoemtry;

    @Override
    public void init() {
        odoemtry = hardwareMap.get(DcMotor.class, "odo");
        odoemtry.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odoemtry.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        telemetry.addData("odoemetry power", odoemtry.getPower());
        telemetry.addData("odo position", odoemtry.getCurrentPosition());
        if(gamepad1.left_stick_y > 0.1) {
            odoemtry.setPower(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y < -0.1) {
            odoemtry.setPower(gamepad1.left_stick_y);
        } else {
            odoemtry.setPower(0);
        }




    }
}
