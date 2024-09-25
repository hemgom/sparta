package starcraft.unitUseAbstractClass;

import starcraft.unitUseInterface.Unit;

public class ExtendsZealot extends AbstractUnit {
    public ExtendsZealot(int healthPoint, int attackPoint) {
        super(healthPoint, attackPoint);
    }

    @Override
    public void upgrade() {
        super.healthPoint += 5;
        super.attackPoint += 3;
        super.unitGrade++;
    }

    @Override
    public String getUnitName() {
        return "Zealot";
    }
}
