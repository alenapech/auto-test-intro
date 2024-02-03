package org.max.home;

public class Composite {

    public static void main(String[] args) {
        WorkingStructure team1 = new CompositeWorkingStructure()
                .addInnerWorkingStructure(new LinearManager())
                .addInnerWorkingStructure(new ProjectManager())
                .addInnerWorkingStructure(new Developer())
                .addInnerWorkingStructure(new Tester())
                .addInnerWorkingStructure(new Analyst());
        WorkingStructure company1 = new CompositeWorkingStructure()
                .addInnerWorkingStructure(team1);

        WorkingStructure team2 = new CompositeWorkingStructure()
                .addInnerWorkingStructure(new ProjectManager())
                .addInnerWorkingStructure(new Developer())
                .addInnerWorkingStructure(new Tester())
                .addInnerWorkingStructure(new Analyst());
        WorkingStructure company2 = new CompositeWorkingStructure()
                .addInnerWorkingStructure(team2);

        WorkingStructure holding = new CompositeWorkingStructure()
                .addInnerWorkingStructure(company1)
                .addInnerWorkingStructure(company2);

        holding.work();
    }

}
