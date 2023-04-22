package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class Drivebase {
    private static Drivebase self;

    private ChassisSpeeds chassis = new ChassisSpeeds();
    private SwerveModuleState[] states = new SwerveModuleState[4];
    private SwerveDriveKinematics kinematics; //TODO: Define modules
    private SwerveModule[] modules = new SwerveModule[4];

    public static Drivebase getDrivebase () {
        if (self == null) {
            self = new Drivebase();
        }
        return self;
    }

    private Drivebase () {
        modules[0] = new SwerveModule(0, 0, 0, 0);
        modules[1] = new SwerveModule(0, 0, 0, 0);
        modules[2] = new SwerveModule(0, 0, 0, 0);
        modules[3] = new SwerveModule(0, 0, 0, 0);
        kinematics = new SwerveDriveKinematics(
            modules[0].getPosition(), 
            modules[1].getPosition(), 
            modules[2].getPosition(), 
            modules[3].getPosition()
            );
    }

    public void run () {
        states = kinematics.toSwerveModuleStates(chassis);
        for (int i = 0; i < 4; i++) {
            modules[i].setState(states[i]);
        }
    }

    public void setSpeeds (double speed, double strafe, double theta) {
        chassis = ChassisSpeeds.fromFieldRelativeSpeeds(speed, strafe, theta, getRotation());
    }

    private Rotation2d getRotation () {
        return new Rotation2d(0.0); //TODO
    }
}
