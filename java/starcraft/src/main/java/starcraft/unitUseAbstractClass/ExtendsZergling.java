package starcraft.unitUseAbstractClass;

public class ExtendsZergling extends AbstractUnit {
    public ExtendsZergling(int healthPoint, int attackPoint) {
        super(healthPoint, attackPoint);
    }

    @Override
    public void upgrade() {
        super.healthPoint += 3;
        super.attackPoint += 6;
        super.unitGrade++;
    }

    @Override
    public String getUnitName() {
        return "Zergling";
    }
}
