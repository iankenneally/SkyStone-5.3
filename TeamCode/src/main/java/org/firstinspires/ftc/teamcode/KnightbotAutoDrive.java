package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

//import org.firstinspires.ftc.robotcontroller.external.samples.HardwareKnightbot;

/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Knightbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: KnightbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *x
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Dis frontRighted line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Knightbot: Auto Drive By Time", group="Knightbot")
//@Disabled
public class KnightbotAutoDrive extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareKnightbot         robot   = new HardwareKnightbot();   // Use a Knightbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();


    static final double     FORWARD_SPEED = 0.8;
    static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

        robot.arm.setPower(1);
        sleep(500);
        robot.arm.setPower(0);

        forward(700);
        stopMotors();

        robot.arm.setPower(-1);
        sleep(490);
        robot.arm.setPower(0);

        backward(700);
        stopMotors();

        robot.arm.setPower(1);
        sleep(500);
        robot.arm.setPower(-1);
        sleep(500);

        parallelRight(1000);
        stopMotors();
    }

    private void forward(int time) {
            robot.frontLeft.setPower(-robot.FL_POWER);
            robot.frontRight.setPower(-robot.FR_POWER);
            robot.backLeft.setPower(-robot.BL_POWER);
            robot.backRight.setPower(-robot.BR_POWER);
            sleep(time);
        }

    private void backward(int time) {
            robot.frontLeft.setPower(robot.FL_POWER);
            robot.frontRight.setPower(robot.FR_POWER);
            robot.backLeft.setPower(robot.BL_POWER);
            robot.backRight.setPower(robot.BR_POWER);
            sleep(time);
        } 

        private void turnLeft(int time) {
            robot.frontLeft.setPower(robot.FL_POWER);
            robot.frontRight.setPower(-robot.FR_POWER);
            robot.backLeft.setPower(robot.BL_POWER);
            robot.backRight.setPower(-robot.BR_POWER);
            sleep(time);
        }

        private void turnRight(int time) {
            robot.frontLeft.setPower(-robot.FL_POWER);
            robot.frontRight.setPower(robot.FR_POWER);
            robot.backLeft.setPower(-robot.BL_POWER);
            robot.backRight.setPower(robot.BR_POWER);
            sleep(time);
        }

        private void parallelLeft(int time) {
            robot.frontLeft.setPower(robot.FL_POWER);
            robot.frontRight.setPower(-robot.FR_POWER);
            robot.backLeft.setPower(-robot.BL_POWER);
            robot.backRight.setPower(robot.BR_POWER);
            sleep(time);
        }

        private void parallelRight(int time) {
            robot.frontLeft.setPower(-robot.FL_POWER);
            robot.frontRight.setPower(robot.FR_POWER);
            robot.backLeft.setPower(robot.BL_POWER);
            robot.backRight.setPower(-robot.BR_POWER);
            sleep(time);
        }

        private void stopMotors() {
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);
        }
}
