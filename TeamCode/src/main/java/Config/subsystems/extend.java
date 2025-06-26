package Config.subsystems;



import static Config.core.robotConstants.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class extend {


    private Telemetry telemetry;

    public DcMotor out;

    public int pos;
    public PIDController pid;
    public int pidLevel = 0;
    public static int target;


    public extend(HardwareMap hardwareMap, Telemetry telemetry) { // init method.
        this.telemetry = telemetry;
        this.telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        out = hardwareMap.get(DcMotor.class, outmotorName);
        out.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        out.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        out.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        pid = new PIDController(ep, ei, ed);
    }
    public void update() { // actual method
        if(pidLevel == 1) { // PID mode
            /* PID has many methods
            SetPID sets the Coefficients
            pid.calculate ( pv, sp) , sets the target to sp, and then calculates the pid output required for it to work based on the current position pv
             */

            pid.setPID(ep,ei,ed);

            out.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); // Sets it so it floats, no active resistance, active power for pid?

            double pid_output = pid.calculate(out.getCurrentPosition(), target); // pid usage
            double power = pid_output + f; // adding f from the pid
            // setting tolerances
            out.setPower(power);

        }
    }
    public void setTarget(int b) {
        pidLevel = 1;
        target = b;
    }

    public void init() {
        pid.setPID(ep,ei,ed);
    }
    public void to_zero() {
        setTarget(zero);

    }

    public void to_third() {
        setTarget(max/3);
    }
    public void to_half() {
        setTarget(max/2);
    }
    public void to_two_third() {
        setTarget(2*max/3);
    }
    public void to_full() {
        setTarget(max);
    }
    public void telemetry() {
        telemetry.addData("out Pos: ", out.getCurrentPosition());
        telemetry.addData("out Target: ", target);
    }
    public void periodic() {
        update();
        telemetry();
    }


}
