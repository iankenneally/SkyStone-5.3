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

    @Override
    public void runOpMode() {
        double FL;
        double FR;
        double BL;
        double BR;
        int    clawReverse = 0;

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

            FL = Range.clip(FL, -robot.FL_POWER, robot.FL_POWER);
            FR = Range.clip(FR, -robot.FR_POWER, robot.FR_POWER);
            BL = Range.clip(BL, -robot.BL_POWER, robot.BL_POWER);
            BR = Range.clip(BR, -robot.BR_POWER, robot.BR_POWER);

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

            if(gamepad2.y){
                clawReverse++;
            }

            if(clawReverse>=1) {
                if (gamepad2.x) {
                    robot.leftClaw.setPosition(1);
                    robot.rightClaw.setPosition(0);
                    //robot.leftClaw.setPosition(.4);
                    //robot.rightClaw.setPosition(1);
                } else {
                    robot.leftClaw.setPosition(.4);
                    robot.rightClaw.setPosition(1);
                    //robot.leftClaw.setPosition(1);
                    //robot.rightClaw.setPosition(0);
                }
            }

            robot.liftL.setPower(gamepad2.right_stick_y);
            robot.liftR.setPower(gamepad2.right_stick_y*.8);

            sleep(50);
        }
    }
}
