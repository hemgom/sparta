package starcraft.unitUseAbstractClass;

public abstract class AbstractUnit {
    protected int healthPoint;
    protected int attackPoint;
    protected int unitGrade = 0;

    AbstractUnit(int healthPoint, int attackPoint) {
        this.healthPoint = healthPoint;
        this.attackPoint = attackPoint;
        System.out.println("유닛이 생성 되었습니다.");
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void status() {
        System.out.printf("%s 상태창 : 공격력 - %d, 체력 - %d, 등급 - %d\n",
                getUnitName() ,getAttackPoint(), healthPoint, unitGrade);
    }

    public void attacked(int otherUnitAttackPoint) {
        healthPoint -= otherUnitAttackPoint;
    }

    public abstract void upgrade();

    public abstract String getUnitName();
}
