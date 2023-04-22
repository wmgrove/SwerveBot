package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class SwerveModule {

    double xOffset;
    double yOffset;
    CANSparkMax driveMotor;
    CANSparkMax thetaMotor;
    double angleTarget = 0.0;
    PIDController thetaController = new PIDController(0, 0, 0);

    public SwerveModule(int driveCANID, int thetaCANID, double xOffset, double yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        driveMotor = new CANSparkMax(driveCANID, MotorType.kBrushless);
        thetaMotor = new CANSparkMax(thetaCANID, MotorType.kBrushless);
    }

    public Translation2d getPosition() {
        return new Translation2d(xOffset, yOffset);
    }

    public void setState(SwerveModuleState state) {
        state = SwerveModuleState.optimize(state, getAngle());
        driveMotor.set(state.speedMetersPerSecond);
        angleTarget = state.angle.getDegrees();
        thetaController.setSetpoint(angleTarget);
        thetaMotor.set(thetaController.calculate(getAngle().getDegrees()));
    }

    private Rotation2d getAngle () {
        return new Rotation2d(0.0);
    }
}
