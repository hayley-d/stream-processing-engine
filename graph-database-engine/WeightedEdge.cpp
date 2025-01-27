#include "WeightedEdge.h"
#include <iostream>

void WeightedEdge::describe() const {
    wrappedEdge->describe();
    std::cout << " - weight: " << weight << std::endl;
}

