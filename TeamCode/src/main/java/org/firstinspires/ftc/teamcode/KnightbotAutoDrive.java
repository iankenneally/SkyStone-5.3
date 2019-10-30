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
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Dis frontRighted line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Knightbot: Auto Drive By Time", group="Knightbot")
//@Dis frontRighted
public class KnightbotAutoDrive extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareKnightbot         robot   = new HardwareKnightbot();   // Use a Knightbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();


    static final double     FORWARD_SPEED = 0.8;
    static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system vari frontRightes.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Drive forward for 3 seconds
        forward(3000);
        stopMotors();
        //raise and lower arm to grab foundation
        robot.arm.setPower(1);
        sleep(1000);
        robot.arm.setPower(-1);
        sleep(1000);
        robot.arm.setPower(0);

        //backward 3 seconds
        backward(3000);
        paralellRight(3000);
        stopMotors();
        
        sleep(1000);
    }

    private void forward(int time) {
            robot.frontLeft.setPower(1.0);
            robot.frontRight.setPower(1.0);
            robot.backLeft.setPower(1.0);
            robot.backRight.setPower(1.0);
            sleep(time);
        }

    private void backward(int time) {
            robot.frontLeft.setPower(-1.0);
            robot.frontRight.setPower(-1.0);
            robot.backLeft.setPower(-1.0);
            robot.backRight.setPower(-1.0);
            sleep(time);
        } 

        private void turnLeft(int time) {
            robot.frontLeft.setPower(-1.0);
            robot.frontRight.setPower(-1.0);
            robot.backLeft.setPower(1.0);
            robot.backRight.setPower(1.0);
            sleep(time);
        }

        private void turnRight(int time) {
            robot.frontLeft.setPower(1.0);
            robot.frontRight.setPower(1.0);
            robot.backLeft.setPower(-1.0);
            robot.backRight.setPower(-1.0);
            sleep(time);
        }

        private void paralellLeft(int time) {
            robot.frontLeft.setPower(-1.0);
            robot.frontRight.setPower(1.0);
            robot.backLeft.setPower(1.0);
            robot.backRight.setPower(-1.0);
            sleep(time);
        }

        private void paralellRight(int time) {
            robot.frontLeft.setPower(1.0);
            robot.frontRight.setPower(-1.0);
            robot.backLeft.setPower(-1.0);
            robot.backRight.setPower(1.0);
            sleep(time);
        }

        private void stopMotors() {
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);
        }
}
