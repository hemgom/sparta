package starcraft;

import starcraft.unitUseAbstractClass.ExtendsMarin;
import starcraft.unitUseAbstractClass.AbstractUnit;
import starcraft.unitUseAbstractClass.ExtendsZealot;
import starcraft.unitUseAbstractClass.ExtendsZergling;
import starcraft.unitUseInterface.Marin;
import starcraft.unitUseInterface.Unit;
import starcraft.unitUseInterface.Zealot;
import starcraft.unitUseInterface.Zergling;

public class Main {
    public static void main(String[] args) {
        useAbstractClass();

        System.out.println();
        System.out.println("============================================");
        System.out.println();

        useInterface();
    }

    public static void useAbstractClass() {
        System.out.println("추상 클래스를 사용한 결과");
        AbstractUnit marin = new ExtendsMarin(10, 30);
        marin.status();
        marin.upgrade();
        marin.status();
        System.out.println();

        AbstractUnit zealot = new ExtendsZealot(15, 10);
        zealot.status();
        zealot.upgrade();
        zealot.status();
        System.out.println();

        AbstractUnit zergling = new ExtendsZergling(20, 10);
        zergling.status();
        zergling.upgrade();
        zergling.status();
        System.out.println();

        System.out.println("마린이 질럿에게 공격 받음");
        marin.attacked(zealot.getAttackPoint());
        marin.status();
        System.out.println();

        System.out.println("질럿이 저글링에게 공격 받음");
        zealot.attacked(zergling.getAttackPoint());
        zealot.status();
    }

    public static void useInterface() {
        System.out.println("인터페이스를 사용한 결과");
        Unit marin = new Marin(10, 30);
        marin.status();
        marin.upgrade();
        marin.status();
        System.out.println();

        Unit zealot = new Zealot(15, 10);
        zealot.status();
        zealot.upgrade();
        zealot.status();
        System.out.println();

        Unit zergling = new Zergling(20, 10);
        zergling.status();
        zergling.upgrade();
        zergling.status();
        System.out.println();

        System.out.println("마린이 질럿에게 공격 받음");
        marin.attacked(zealot.getAttackPoint());
        marin.status();
        System.out.println();

        System.out.println("질럿이 저글링에게 공격 받음");
        zealot.attacked(zergling.getAttackPoint());
        zealot.status();
    }
}