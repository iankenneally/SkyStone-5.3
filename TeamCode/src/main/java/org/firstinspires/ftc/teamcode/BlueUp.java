package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="blue full auto", group="Knightbot")
//@Disabled
public class BlueUp extends LinearOpMode {

    HardwareKnightbot         robot   = new HardwareKnightbot();   // Use a Knightbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

        openClaw();
        robot.arm.setPower(-.8);
        sleep(500);
        robot.arm.setPower(-.2);
        stopMotors();

        sleep(1000);

        forward(1200);
        stopMotors();

        sleep(1000);

        robot.arm.setPower(.1);
        sleep(300);
        stopMotors();

        sleep(1000);

        backward(1300);
        stopMotors();

        sleep(1000);

        robot.arm.setPower(-.8);
        sleep(200);
        stopMotors();

        sleep(1000);

        parallelLeft(1000);
        sleep(1000);
        forward(700);
        robot.arm.setPower(.05);
        sleep(10);
        robot.arm.setPower(0);
        sleep(1000);
        parallelRight(1300);
        stopMotors();
    }

    private void forward(int time) {
            robot.frontLeft.setPower(-robot.FL_POWER);
            robot.frontRight.setPower(-robot.FR_POWER);
            robot.backLeft.setPower(-robot.BL_POWER);
            robot.backRight.setPower(-robot.BR_POWER);
            sleep(time);
            stopMotors();
        }

    private void backward(int time) {
            robot.frontLeft.setPower(robot.FL_POWER);
            robot.frontRight.setPower(robot.FR_POWER);
            robot.backLeft.setPower(robot.BL_POWER);
            robot.backRight.setPower(robot.BR_POWER);
            sleep(time);
            stopMotors();
        } 

        private void turnLeft(int time) {
            robot.frontLeft.setPower(robot.FL_POWER);
            robot.frontRight.setPower(-robot.FR_POWER);
            robot.backLeft.setPower(robot.BL_POWER);
            robot.backRight.setPower(-robot.BR_POWER);
            sleep(time);
            stopMotors();
        }

        private void turnRight(int time) {
            robot.frontLeft.setPower(-robot.FL_POWER);
            robot.frontRight.setPower(robot.FR_POWER);
            robot.backLeft.setPower(-robot.BL_POWER);
            robot.backRight.setPower(robot.BR_POWER);
            sleep(time);
            stopMotors();
        }

        private void parallelLeft(int time) {
            robot.frontLeft.setPower(robot.FL_POWER);
            robot.frontRight.setPower(-robot.FR_POWER);
            robot.backLeft.setPower(-robot.BL_POWER);
            robot.backRight.setPower(robot.BR_POWER);
            sleep(time);
            stopMotors();
        }

        private void parallelRight(int time) {
            robot.frontLeft.setPower(-robot.FL_POWER);
            robot.frontRight.setPower(robot.FR_POWER);
            robot.backLeft.setPower(robot.BL_POWER);
            robot.backRight.setPower(-robot.BR_POWER);
            sleep(time);
            stopMotors();
        }

        private void stopMotors() {
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);
        }

        private void openClaw() {
            robot.leftClaw.setPosition(1);
            robot.rightClaw.setPosition(0);
        }

        private void closeClaw() {
            robot.leftClaw.setPosition(.4);
            robot.rightClaw.setPosition(1);
        }
}
