package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;


@Autonomous(name="AutonomousBlueRight", group="Linear Opmode")
//@Disabled
public class AutonomousBlueRight extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    //private DcMotor leftDrive0 = null;
    //private DcMotor rightDrive = null;
    
    DcMotor leftDrive0; //motor 0
    DcMotor leftDrive3; // motor 1
    DcMotor rightDrive1; //motor 2
    DcMotor rightDrive2; //motor 3
    DcMotor carousel; //motor 0: Hub 4
    
    //Servos
    Servo rightServo;
    Servo leftServo;
    
    //Arms
    DcMotor gearArm;
    DcMotor yoyoArm;
   
    public void main(String[] args){
        runOpMode();
    }
    
    //sets motor power simultaneously
    public void setMotorPower(double power){
        leftDrive0.setPower(power);
        leftDrive3.setPower(power);
        
        rightDrive1.setPower(power);
        rightDrive2.setPower(power);
    }
    //sets left and right motors power seperately  
    public void setMotorPower(double powerLeft, double powerRight){
        leftDrive0.setPower(powerLeft);
        leftDrive3.setPower(powerLeft);
        
        rightDrive1.setPower(powerRight);
        rightDrive2.setPower(powerRight);
    }
    
    //Turning
    public void turnRight(int time){
        setMotorPower(0.2, -0.2);
        sleep(time);
    }
    
    public void turnLeft(int time){
        setMotorPower(-0.2, 0.2);
        sleep(time);
    }

    //strafing
    public void strafe(double power, String direction){
        power = Math.abs(power);
        if (direction.equals("left")){
            leftDrive3.setPower(-power);
            rightDrive2.setPower(power);
            
            rightDrive1.setPower(-power);
            leftDrive0.setPower(power);
        }
        else if (direction.equals("right")){
            leftDrive3.setPower(power);
            rightDrive2.setPower(-power);
            
            rightDrive1.setPower(power);
            leftDrive0.setPower(-power);
        }
        
    }
    
    
    //stops motor power by setting it to 0
    public void stopMotors(){
        leftDrive0.setPower(0);
        leftDrive3.setPower(0);
        
        rightDrive1.setPower(0);
        rightDrive2.setPower(0);
    } 
    

    
    //set arm servo position
    public void grab(){
        leftServo.setPosition(0.65);
        rightServo.setPosition(0.45);
    }
    public void release(){
        leftServo.setPosition(0.4);
        rightServo.setPosition(0.6);
    }
    public void servoRest(){
        leftServo.setPosition(0);
        rightServo.setPosition(0.9);
    }
    
    @Override
    public void runOpMode(){
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        leftDrive0 = hardwareMap.get(DcMotor.class, "leftDrive0");
        rightDrive1 = hardwareMap.get(DcMotor.class, "rightDrive1");

        leftDrive3  = hardwareMap.get(DcMotor.class, "leftDrive3");
        rightDrive2 = hardwareMap.get(DcMotor.class, "rightDrive2");
        
        carousel = hardwareMap.get(DcMotor.class, "carousel");
        
        rightServo = hardwareMap.get(Servo.class, "rightServo");
        leftServo = hardwareMap.get(Servo.class, "leftServo");
        
        gearArm = hardwareMap.get(DcMotor.class, "gearArm");
        yoyoArm = hardwareMap.get(DcMotor.class,"yoyoArm");
    

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        leftDrive0.setDirection(DcMotor.Direction.FORWARD);
        rightDrive1.setDirection(DcMotor.Direction.REVERSE);
        
        leftDrive3.setDirection(DcMotor.Direction.FORWARD);
        rightDrive2.setDirection(DcMotor.Direction.REVERSE);
        
        carousel.setDirection(DcMotor.Direction.FORWARD);
        gearArm.setDirection(DcMotor.Direction.REVERSE);
        yoyoArm.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)

        waitForStart();
        runtime.reset();
        
        stopMotors();
        sleep(100);
        
        grab();
        sleep(1300);
        
        setMotorPower(0.3);
        sleep(300);
        
        turnLeft(100);
    
        
        gearArm.setPower(0.8);
        sleep(1800);
        setMotorPower(0.2);
        sleep(100);
        release();
        
        //yoyoArm.setPower(0.8);
        //sleep(1000);
        //gearArm.setPower(-0.8);
        //sleep(1500);
        
        
        stopMotors();
        sleep(500);
        
        // setMotorPower(0.5);
        // sleep(100);
        
        // release();
        
        // setMotorPower(-0.7);
        // sleep(1000);
        
        // turnLeft(1500);
        
        //carousel.setPower(-0.6);
        //sleep(6000);
        
        // turnRight(1500);
        
        // setMotorPower(0.7);
        // sleep(3000);
        
        // turnLeft(1500);
    }
}
