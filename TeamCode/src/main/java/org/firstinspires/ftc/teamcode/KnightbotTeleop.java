package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * This OpMode uses the common Knightbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwareKnightbot class.
 * The code is structured as a LinearOpMode
 */

@TeleOp(name="Knightbot Movement", group="Knightbot")
//@Disabled
public class KnightbotTeleop extends LinearOpMode {

    HardwareKnightbot robot = new HardwareKnightbot();   // Use Knightbot's hardware
    double closedPosition = .4;

    @Override
    public void runOpMode() {
        double FL;
        double FR;
        double BL;
        double BR;

        robot.init(hardwareMap);

        telemetry.addData("Say", "Hello Driver");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // set drive power levels to power variables (very nice thomas)
            FL = gamepad1.left_stick_y-gamepad1.left_stick_x;
            FR = gamepad1.right_stick_y+gamepad1.left_stick_x;
            BL = gamepad1.left_stick_y+gamepad1.left_stick_x;
            BR = gamepad1.right_stick_y-gamepad1.left_stick_x;

            FL = Range.clip(FL, -1, 1);
            FR = Range.clip(FR, -1, 1);
            BL = Range.clip(BL, -1, 1);
            BR = Range.clip(BR, -1, 1);

            robot.frontLeft.setPower(FL);
            robot.frontRight.setPower(FR);
            robot.backLeft.setPower(BL);
            robot.backRight.setPower(BR);

            telemetry.addData("Front Left", robot.frontLeft.getPower());
            telemetry.addData("Front Right", robot.frontRight.getPower());
            telemetry.addData("Back Left", robot.backLeft.getPower());
            telemetry.addData("Back Right", robot.backRight.getPower());
            telemetry.update();

            robot.arm.setPower(gamepad2.left_stick_y/2);

            if(gamepad2.x){
                robot.leftClaw.setPosition(robot.OPEN_SERVO + closedPosition);
                robot.rightClaw.setPosition(robot.OPEN_SERVO + closedPosition);
            }else{
                robot.leftClaw.setPosition(robot.OPEN_SERVO);
                robot.rightClaw.setPosition(robot.OPEN_SERVO);
            }

            robot.liftL.setPower(gamepad2.right_stick_y);
            robot.liftR.setPower(gamepad2.right_stick_y*.8);

            // Send telemetry message to signify robot running;
            //telemetry.addData("left1",  "%.2f", robot.leftClaw);
            //telemetry.addData("right1", "%.2f", robot.rightClaw);
            //telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(50);
        }
    }
}
