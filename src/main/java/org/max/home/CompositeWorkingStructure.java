package org.max.home;

import java.util.ArrayList;
import java.util.List;

public class CompositeWorkingStructure implements WorkingStructure {

    List<WorkingStructure> innerWorkingStructures = new ArrayList<>();

    public CompositeWorkingStructure(List<WorkingStructure> innerWorkingStructures) {
        this.innerWorkingStructures = innerWorkingStructures;
    }

    public CompositeWorkingStructure() {
    }

    public CompositeWorkingStructure addInnerWorkingStructure(WorkingStructure innerWorkingStructure) {
        this.innerWorkingStructures.add(innerWorkingStructure);
        return this;
    }

    public CompositeWorkingStructure removeInnerWorkingStructures(WorkingStructure innerWorkingStructure) {
        this.innerWorkingStructures.remove(innerWorkingStructure);
        return this;
    }

    @Override
    public void work() {
        this.innerWorkingStructures.stream().forEach(WorkingStructure::work);
    }
}
