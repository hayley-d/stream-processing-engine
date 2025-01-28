#include "TimestampedEdge.h"
#include <iostream>

void TimestampedEdge::describe() const {
    wrappedEdge->describe();
    std::cout << " - timestamp: " << timestamp << std::endl;
}

