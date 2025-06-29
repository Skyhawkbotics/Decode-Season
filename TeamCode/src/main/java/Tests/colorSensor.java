package Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;


@TeleOp (name = "colorTest", group = "Tests")
public class colorSensor extends OpMode {
    public ColorSensor color;

    @Override
    public void init() {
        color = hardwareMap.get(ColorSensor.class, "color");

    }

    @Override
    public void loop() {
        telemetry.addData("Red color", color.red());
        telemetry.addData("Green color", color.green());
        telemetry.addData("Blue color", color.blue());
    }
}
