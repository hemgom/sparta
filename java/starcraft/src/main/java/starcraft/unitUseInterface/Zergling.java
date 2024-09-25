package starcraft.unitUseInterface;

public class Zergling implements Unit {
    private int healthPoint;
    private int attackPoint;
    private int unitGrade = 0;

    public Zergling(int healthPoint, int attackPoint) {
        this.healthPoint = healthPoint;
        this.attackPoint = attackPoint;
    }

    @Override
    public int getAttackPoint() {
        return attackPoint;
    }

    @Override
    public void status() {
        System.out.printf("%s 상태창 : 공격력 - %d, 체력 - %d, 등급 - %d\n",
                "Zergling" ,getAttackPoint(), healthPoint, unitGrade);
    }

    @Override
    public void attacked(int otherUnitAttackPoint) {
        healthPoint -= otherUnitAttackPoint;
    }

    @Override
    public void upgrade() {
        healthPoint += 3;
        attackPoint += 6;
        unitGrade++;
    }
}
