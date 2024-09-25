package starcraft.unitUseInterface;

public interface Unit {
    int getAttackPoint();

    void status();

    void attacked(int otherUnitAttackPoint);

    void upgrade();
}
