package starcraft.unitUseAbstractClass;

public class ExtendsMarin extends AbstractUnit {
    public ExtendsMarin(int healthPoint, int attackPoint) {
        super(healthPoint, attackPoint);
    }

    @Override
    public void upgrade() {
        super.healthPoint += 10;
        super.attackPoint += 5;
        super.unitGrade++;
    }

    @Override
    public String getUnitName() {
        return "Marin";
    }
}
