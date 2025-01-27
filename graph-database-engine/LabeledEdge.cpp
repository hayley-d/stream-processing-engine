#include "LabeledEdge.h"
#include <iostream>

void LabeledEdge::describe() const {
    wrappedEdge->describe();
    std::cout << " - label: " << label << std::endl;
}

