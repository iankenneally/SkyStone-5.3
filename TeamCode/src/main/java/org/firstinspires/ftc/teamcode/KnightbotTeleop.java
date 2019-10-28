/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This OpMode uses the common Knightbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwareKnightbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Pushbot: Teleop POV", group="Pushbot")
//@Disabled
public class KnightbotTeleop extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareKnightbot robot           = new HardwareKnightbot();   // Use a Knightbot's hardware
    double          clawOffset      = 0;                       // Servo mid position
    final double    CLAW_SPEED      = 0.02 ;                   // sets rate to move servo

    @Override
    public void runOpMode() {
        double FL;
        double FR;
        double BL;
        double BR;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //set power levels to power variables (thomas)
            FL = gamepad1.left_stick_y-gamepad1.left_stick_x;
            FR = gamepad1.left_stick_y+gamepad1.left_stick_x;
            BL = gamepad1.right_stick_y+gamepad1.left_stick_x;
            BR = gamepad1.right_stick_y-gamepad1.left_stick_x;

            //clip power variables to be within -1 and 1 (thomas)
            FL = Range.clip(FL, -1, 1);
            FR = Range.clip(FR, -1, 1);
            BL = Range.clip(BL, -1, 1);
            BR = Range.clip(BR, -1, 1);

            // Output the safe vales to the motor drives.
            robot.frontLeft.setPower(FL);
            robot.frontRight.setPower(FR);
            robot.backLeft.setPower(BL);
            robot.backRight.setPower(BR);

            // send wheel telemetry data
            telemetry.addData("Front Left", robot.frontLeft.getPower());
            telemetry.addData("Front Right", robot.frontRight.getPower());
            telemetry.addData("Back Left", robot.backLeft.getPower());
            telemetry.addData("Back Right", robot.backRight.getPower());

            //raise and lower arm based on gamepad 2 left stick
            robot.arm.setPower(gamepad2.left_stick_y/2);

            if(gamepad2.x){
                robot.leftClaw.setPosition(0.4);
                robot.rightClaw.setPosition(0.4);
            }else{
                robot.leftClaw.setPosition(0);
                robot.rightClaw.setPosition(0);
            }

            // Send telemetry message to signify robot running;
            telemetry.addData("left",  "%.2f", robot.leftClaw);
            telemetry.addData("right", "%.2f", robot.rightClaw);
            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(50);
        }
    }
}
