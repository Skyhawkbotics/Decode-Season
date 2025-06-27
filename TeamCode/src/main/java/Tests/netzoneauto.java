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
    private Pose sample1 = new Pose(63.2, 108);
    private Pose netZone1 = new Pose(17,128);
    private Pose sample2 = new Pose(70, 125);
    private Pose netZone2 = new Pose(20,132);
    private Pose sample3 = new Pose(64.5, 134.25);
    private Pose netZone3 = new Pose(26.6,134.25);
    private Pose endZone = new Pose(17,17);

    private Path line1, line2;

    public void buildPaths() {
        pushSample = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(startPose), new Point(49, 111, Point.CARTESIAN), new Point(sample1)))
                    .setLinearHeadingInterpolation(0,Math.toRadians(-30))

                .addPath(new BezierCurve(new Point(sample1), new Point(32, 126, Point.CARTESIAN), new Point(netZone1)))
                    .setTangentHeadingInterpolation()
                    .setReversed(true)

                .addPath(new BezierCurve(new Point(netZone1), new Point(63.5,105), new Point(sample2)))
                    .setConstantHeadingInterpolation(Math.toRadians(30))

                .addPath(new BezierCurve(new Point(sample2), new Point(45.5, 123.7, Point.CARTESIAN), new Point(netZone2)))
                    .setTangentHeadingInterpolation()
                    .setReversed(true)

                .addPath(new BezierCurve(new Point(netZone2), new Point(44.4, 118.75), new Point(sample3)))
                    .setLinearHeadingInterpolation(Math.toRadians(-18),0)

                .addPath(new BezierLine(new Point(sample3), new Point(netZone3)))
                    .setTangentHeadingInterpolation()
                    .setReversed(true)

                .addPath(new BezierLine(new Point(netZone3), new Point(endZone)))
                    .setConstantHeadingInterpolation(0)

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
