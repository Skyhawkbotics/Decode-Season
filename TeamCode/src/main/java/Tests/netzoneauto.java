package Tests;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

@Autonomous(name="Netzone Auto", group="Tests")
public class netzoneauto extends OpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;

    private int pathState;
    private PathChain pushSample;

    private Pose startPose = new Pose(10, 110);
    private Pose point1 = new Pose(63.2, 108);
    private Pose netZone = new Pose(17,128);

    private Path line1, line2;

    public void buildPaths() {
        pushSample = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(startPose), new Point(49, 111, Point.CARTESIAN), new Point(point1)))
                    .setTangentHeadingInterpolation()

                .addPath(new BezierCurve(new Point(point1), new Point(32, 126, Point.CARTESIAN), new Point(netZone)))
                    .setTangentHeadingInterpolation()
                .setReversed(true)
                .build();
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                setPathState(1);
                break;
            case 1:
                if(!follower.isBusy()) {
                    follower.followPath(pushSample);
                    setPathState(2);
                }
                break;
        }
    }
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    @Override
    public void loop() {
        autonomousPathUpdate();
        follower.update();
    }

    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(startPose);
        buildPaths();
    }

    /** This method is called continuously after Init while waiting for "play". **/
    @Override
    public void init_loop() {}

    /** This method is called once at the start of the OpMode.
     * It runs all the setup actions, including building paths and starting the path system **/
    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }

    /** We do not use this because everything should automatically disable **/
    @Override
    public void stop() {
    }
}
