package starcraft.unitUseInterface;

public class Marin implements Unit {
    private int healthPoint;
    private int attackPoint;
    private int unitGrade = 0;

    public Marin(int healthPoint, int attackPoint) {
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
                "Marin" ,getAttackPoint(), healthPoint, unitGrade);
    }

    @Override
    public void attacked(int otherUnitAttackPoint) {
        healthPoint -= otherUnitAttackPoint;
    }

    @Override
    public void upgrade() {
        healthPoint += 10;
        attackPoint += 5;
        unitGrade++;
    }
}
